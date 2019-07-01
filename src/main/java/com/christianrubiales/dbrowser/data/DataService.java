package com.christianrubiales.dbrowser.data;

import com.christianrubiales.dbrowser.databaseConnection.DatabaseConnection;

import java.util.List;
import java.util.Map;

public interface DataService {

    List<List<Map<String, String>>> getData(DatabaseConnection connection, String table)
            throws DataException;
}
