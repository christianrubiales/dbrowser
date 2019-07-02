package com.christianrubiales.dbrowser.databaseConnection;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class DatabaseConnection {

    @Id
    private Long id;
    private String name;
    private String databaseName;
    private String username;
    private String password;
}
