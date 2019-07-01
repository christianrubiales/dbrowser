package com.christianrubiales.dbrowser.column;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ColumnRepository extends CrudRepository<Column, String> {

    List<Column> findByTableSchemaAndTableName(String tableSchema, String tableName);

}
