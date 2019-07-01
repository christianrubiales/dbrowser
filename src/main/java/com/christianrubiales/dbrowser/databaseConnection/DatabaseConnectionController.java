package com.christianrubiales.dbrowser.databaseConnection;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DatabaseConnectionController {

	@Autowired
	private DatabaseConnectionService connectionService;

	@Autowired
	private DatabaseService databaseService;

	public DatabaseConnectionController(DatabaseConnectionService connectionService, DatabaseService databaseService) {
		this.connectionService = connectionService;
		this.databaseService = databaseService;
	}

	@GetMapping("/connections")
	public List<DatabaseConnection> getConnections() {
		return connectionService.getConnections();
	}

	@GetMapping("/connections/{id}")
	public DatabaseConnection getConnection(@PathVariable String id) {
		return connectionService.getConnection(id);
	}

	@PostMapping("/connections")
	public void addConnection(@RequestBody DatabaseConnection connection) {
		connectionService.addConnection(connection);
	}

	@PutMapping("/connections/{id}")
	public void updateConnection(@PathVariable String id, @RequestBody DatabaseConnection connection) {
		connection.setId(id);
		connectionService.updateConnection(connection);
	}

	@DeleteMapping("/connections/{id}")
	public void deleteConnection(@PathVariable String id) {
		connectionService.deleteConnectionById(id);
	}

	@GetMapping("/connections/{connectionId}/schemas")
	public List<String> getSchemas(@PathVariable String connectionId) throws DatabaseServiceException {
		return databaseService.getSchemas(connectionService.getConnection(connectionId));
	}

	@GetMapping("/connections/{connectionId}/schemas/{schema}/tables")
	public List<String> getSchemas(@PathVariable String connectionId, @PathVariable String schema) throws DatabaseServiceException {
		return databaseService.getTables(connectionService.getConnection(connectionId), schema);
	}
}
