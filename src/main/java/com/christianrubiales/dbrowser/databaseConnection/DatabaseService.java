package com.christianrubiales.dbrowser.databaseConnection;

import java.util.List;

public interface DatabaseService {

	List<String> getSchemas(DatabaseConnection connection) throws DatabaseServiceException;
	
	List<String> getTables(DatabaseConnection connection, String schema) throws DatabaseServiceException;
	
	List<String> getColumns(DatabaseConnection connection, String schema, String database) throws DatabaseServiceException;
}
