# dbrowser

Database Browser as a RESTful Web Service

Uses HSQLDB that persists to a file.

## Running

`mvn clean spring-boot:run`

## Example Requests

`GET http://localhost:8080/connections`

`GET http://localhost:8080/connections/123`

`POST http://localhost:8080/connections`
```javascript
{
	"id": "123",
	"name": "test",
	"databaseName": "target/db/testdb",
	"userName": "SA",
	"password": "",
}
```

`PUT http://localhost:8080/connections/123`
```javascript
{
	"id": "123",
	"name": "test",
	"databaseName": "target/db/testdb",
	"userName": "SA",
	"password": "",
}
```

`DELETE http://localhost:8080/connections/123`

`GET http://localhost:8080/connections/123/schemas`

`GET http://localhost:8080/connections/123/schemas/PUBLIC/tables`

`GET http://localhost:8080/connections/123/schemas/PUBLIC/tables/DATABASE_CONNECTION/columns`
