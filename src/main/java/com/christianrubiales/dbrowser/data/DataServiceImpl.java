package com.christianrubiales.dbrowser.data;

import com.christianrubiales.dbrowser.databaseConnection.DatabaseConnection;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DataServiceImpl implements DataService {

    private static final String PREFIX = "jdbc:hsqldb:file:";

    public List<List<Map<String, String>>> getData(DatabaseConnection connection, String table)
            throws DataException {
        List<List<Map<String, String>>> data = new ArrayList<>();
        String username = connection.getUsername();
        String password = connection.getPassword();
        String url = PREFIX + connection.getDatabaseName();

        String query = String.format("SELECT * FROM %s", table);
        ResultSet rs = null;
        try (
                Connection conn =  DriverManager.getConnection(url, username, password);
                PreparedStatement statement = conn.prepareStatement(query)
        )
        {
            rs = statement.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            while (rs.next()) {
                List<Map<String, String>> row = new ArrayList<>();

                int cols = rsmd.getColumnCount();
                for (int i = 1; i <= cols; i++) {
                    Map<String, String> map = new HashMap<>();
                    map.put(rsmd.getColumnName(i), rs.getString(i));
                    row.add(map);
                }
                data.add(row);
            }
        } catch (SQLException e) {
            throw new DataException(e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                throw new DataException(e);
            }
        }
        return data;
    }

    public ColumnStatistics getColumnStatistics(DatabaseConnection connection,
            String table, String column) throws DataException {
        ColumnStatistics statistics = new ColumnStatistics();
        String username = connection.getUsername();
        String password = connection.getPassword();
        String url = PREFIX + connection.getDatabaseName();

        String query = String.format("SELECT MIN(%s), MAX(%s), AVG(%s), MEDIAN(%s) FROM %s",
                column, column, column, column, table);
        ResultSet rs = null;
        try (
                Connection conn =  DriverManager.getConnection(url, username, password);
                PreparedStatement statement = conn.prepareStatement(query)
        )
        {
            rs = statement.executeQuery();
            while (rs.next()) {
                statistics.setMin(rs.getDouble(1));
                statistics.setMax(rs.getDouble(2));
                statistics.setAverage(rs.getDouble(3));
                statistics.setMedian(rs.getDouble(4));
            }
        } catch (SQLException e) {
            throw new DataException(e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                throw new DataException(e);
            }
        }
        return statistics;
    }

    public TableStatistics getTableStatistics(
            DatabaseConnection connection, String table) throws DataException {
        TableStatistics statistics = new TableStatistics();
        String username = connection.getUsername();
        String password = connection.getPassword();
        String url = PREFIX + connection.getDatabaseName();

        String query = String.format("SELECT * FROM %s", table);
        ResultSet rs = null;
        try (
                Connection conn =  DriverManager.getConnection(url, username, password);
                PreparedStatement statement = conn.prepareStatement(query)
        )
        {
            rs = statement.executeQuery();
            long rows = 0;
            while (rs.next()) {
                rows++;
            }
            statistics.setRows(rows);
            statistics.setColumns((long) rs.getMetaData().getColumnCount());
        } catch (SQLException e) {
            throw new DataException(e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                throw new DataException(e);
            }
        }
        return statistics;
    }
}
