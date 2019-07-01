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

    public List<List<Map<String, String>>> getData(DatabaseConnection connection, String table) throws DataException {
        List<List<Map<String, String>>> data = new ArrayList<>();
        String username = connection.getUsername();
        String password = connection.getPassword();
        String url = PREFIX + connection.getDatabaseName();

        String query = "SELECT * FROM " + table;
        ResultSet rs = null;
        try (
                Connection conn =  DriverManager.getConnection(url, username, password);
                PreparedStatement statement = conn.prepareStatement(query);
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
}
