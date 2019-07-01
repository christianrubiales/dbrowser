package com.christianrubiales.dbrowser.database;

import com.christianrubiales.dbrowser.databaseConnection.DatabaseConnection;

import java.util.List;

public interface DatabaseService {

    List<String> getSchemas(DatabaseConnection connection) throws DatabaseServiceException;

    List<String> getTables(DatabaseConnection connection, String schema)
            throws DatabaseServiceException;
}
