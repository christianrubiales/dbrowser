package com.christianrubiales.dbrowser.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.christianrubiales.dbrowser.databaseConnection.DatabaseConnection;
import org.springframework.stereotype.Service;

@Service
public class DatabaseServiceImpl implements DatabaseService {

    private static final String PREFIX = "jdbc:hsqldb:file:";

    @Override
    public List<String> getSchemas(DatabaseConnection connection) throws DatabaseServiceException {
        String username = connection.getUsername();
        String password = connection.getPassword();
        String url = PREFIX + connection.getDatabaseName();

        List<String> schemas = new ArrayList<>();
        try (
                Connection conn =  DriverManager.getConnection(url, username, password);
                Statement statement = conn.createStatement();
                ResultSet rs = statement.executeQuery(
                        "SELECT DISTINCT TABLE_SCHEMA FROM INFORMATION_SCHEMA.TABLES"))
        {
            while (rs.next()) {
                schemas.add(rs.getString(1));
            }
        } catch (SQLException e) {
            throw new DatabaseServiceException(e);
        }

        return schemas;
    }

    @Override
    public List<String> getTables(DatabaseConnection connection, String schema)
            throws DatabaseServiceException {
        String username = connection.getUsername();
        String password = connection.getPassword();
        String url = PREFIX + connection.getDatabaseName();

        List<String> tables = new ArrayList<>();
        String query =
                "SELECT DISTINCT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = ?";
        ResultSet rs = null;
        try (
                Connection conn =  DriverManager.getConnection(url, username, password);
                PreparedStatement statement = conn.prepareStatement(query);
        )
        {
            statement.setString(1, schema);
            rs = statement.executeQuery();
            while (rs.next()) {
                tables.add(rs.getString(1));
            }
        } catch (SQLException e) {
            throw new DatabaseServiceException(e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                throw new DatabaseServiceException(e);
            }
        }

        return tables;
    }

}
