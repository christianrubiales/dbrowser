package com.christianrubiales.dbrowser.column;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ColumnService {

    @Autowired
    private ColumnRepository columnRepository;


    public List<Column> getColumns(String tableSchema, String tableName) {
        return columnRepository.findByTableSchemaAndTableName(tableSchema, tableName);
    }
}
