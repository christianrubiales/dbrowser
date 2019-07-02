package com.christianrubiales.dbrowser.database;

import java.util.List;
import java.util.Map;

import com.christianrubiales.dbrowser.column.Column;
import com.christianrubiales.dbrowser.column.ColumnService;
import com.christianrubiales.dbrowser.data.ColumnStatistics;
import com.christianrubiales.dbrowser.data.DataException;
import com.christianrubiales.dbrowser.data.DataService;
import com.christianrubiales.dbrowser.data.TableStatistics;
import com.christianrubiales.dbrowser.databaseConnection.DatabaseConnection;
import com.christianrubiales.dbrowser.databaseConnection.DatabaseConnectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost", maxAge = 3600, allowedHeaders = "*",
        methods = {
            RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE
        })
public class DatabaseController {

    @Autowired
    private DatabaseConnectionService connectionService;

    @Autowired
    private DatabaseService databaseService;

    @Autowired
    private ColumnService columnService;

    @Autowired
    private DataService dataService;

    public DatabaseController(DatabaseConnectionService connectionService,
                              DatabaseService databaseService, ColumnService columnService,
                              DataService dataService) {
        this.connectionService = connectionService;
        this.databaseService = databaseService;
        this.columnService = columnService;
        this.dataService = dataService;
    }

    @GetMapping("/connections")
    public List<DatabaseConnection> getConnections() {
        return connectionService.getConnections();
    }

    @GetMapping("/connections/{id}")
    public DatabaseConnection getConnection(@PathVariable Long id) {
        return connectionService.getConnection(id);
    }

    @PostMapping("/connections")
    public void addConnection(@RequestBody DatabaseConnection connection) {
        connectionService.addConnection(connection);
    }

    @PutMapping("/connections/{id}")
    public void updateConnection(@PathVariable Long id,
                                 @RequestBody DatabaseConnection connection) {
        connection.setId(id);
        connectionService.updateConnection(connection);
    }

    @DeleteMapping("/connections/{id}")
    public void deleteConnection(@PathVariable Long id) {
        connectionService.deleteConnectionById(id);
    }

    @GetMapping("/connections/{connectionId}/schemas")
    public List<String> getSchemas(@PathVariable Long connectionId)
            throws DatabaseServiceException {
        return databaseService.getSchemas(connectionService.getConnection(connectionId));
    }

    @GetMapping("/connections/{connectionId}/schemas/{schema}/tables")
    public List<String> getSchemas(@PathVariable Long connectionId, @PathVariable String schema)
            throws DatabaseServiceException {
        return databaseService.getTables(connectionService.getConnection(connectionId), schema);
    }

    @GetMapping("/connections/{connectionId}/schemas/{schema}/tables/{table}/columns")
    public List<Column> getColumns(@PathVariable String connectionId, @PathVariable String schema,
                                   @PathVariable String table) {
        return columnService.getColumns(schema, table);
    }

    @GetMapping("/connections/{connectionId}/schemas/{schema}/tables/{table}/data")
    public List<List<Map<String, String>>> getData(@PathVariable Long connectionId,
            @PathVariable String table) throws DataException {
        return dataService.getData(connectionService.getConnection(connectionId), table);
    }

    @GetMapping("/connections/{connectionId}/schemas/{schema}/tables/{table}/statistics")
    public TableStatistics getTableStatistics(
            @PathVariable Long connectionId, @PathVariable String table) throws DataException {
        return dataService.getTableStatistics(connectionService.getConnection(connectionId), table);
    }

    @GetMapping(
        "/connections/{connectionId}/schemas/{schema}/tables/{table}/column/{column}/statistics")
    public ColumnStatistics getColumnStatistics(@PathVariable Long connectionId,
                                                @PathVariable String table, @PathVariable String column) throws DataException {
        return dataService.getColumnStatistics(
                connectionService.getConnection(connectionId), table, column);
    }
}
