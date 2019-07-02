package com.christianrubiales.dbrowser.databaseConnection;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DatabaseConnectionService {

    @Autowired
    private DatabaseConnectionRepository repository;

    public DatabaseConnection getConnection(Long id) {
        return repository.findOne(id);
    }

    public List<DatabaseConnection> getConnections() {
        List<DatabaseConnection> connections = new ArrayList<>();
        repository.findAll().forEach(connections::add);

        return connections;
    }

    public void addConnection(DatabaseConnection connection) {
        repository.save(connection);
    }

    public void updateConnection(DatabaseConnection connection) {
        repository.save(connection);
    }

    public void deleteConnectionById(Long id) {
        repository.delete(id);
    }
}
