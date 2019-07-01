package com.christianrubiales.dbrowser.connection;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Connection {

	@Id
	private String id;
	private String name;
	private String databaseName;
	private String username;
	private String password;
	
	public Connection() {
	}

	public Connection(String id, String name, String databaseName, String username, String password) {
		this.id = id;
		this.name = name;
		this.databaseName = databaseName;
		this.username = username;
		this.password = password;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDatabaseName() {
		return databaseName;
	}

	public void setDatabaseName(String databaseName) {
		this.databaseName = databaseName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
