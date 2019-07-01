package com.christianrubiales.dbrowser.connection;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConnectionController {

	@Autowired
	private ConnectionService service;
	
	public ConnectionController(ConnectionService service) {
		this.service = service;
	}

	@RequestMapping("/connections")
	public List<Connection> getConnections() {
		return service.getConnections();
	}

	@RequestMapping(method = RequestMethod.POST, value ="/connections")
	public void addConnection(@RequestBody Connection connection) {
		service.addConnection(connection);
	}

	@RequestMapping(method = RequestMethod.PUT, value ="/connections/{id}")
	public void updateConnection(@PathVariable String id, @RequestBody Connection connection) {
		connection.setId(id);
		service.updateConnection(connection);
	}

	@RequestMapping(method = RequestMethod.DELETE, value ="/connections/{id}")
	public void deleteConnection(@PathVariable String id) {
		service.deleteConnectionById(id);
	}
}
