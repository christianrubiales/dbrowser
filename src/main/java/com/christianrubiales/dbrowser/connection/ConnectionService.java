package com.christianrubiales.dbrowser.connection;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConnectionService {

	@Autowired
	private ConnectionRepository repository;
	
	public List<Connection> getConnections() {
		List<Connection> connections = new ArrayList<>();
		repository.findAll().forEach(connections::add);
		
		return connections;
	}
	
	public void addConnection(Connection connection) {
		repository.save(connection);
	}
	
	public void updateConnection(Connection connection) {
		repository.save(connection);
	}
	
	public void deleteConnectionById(String id) {
		repository.delete(id);
	}
}
