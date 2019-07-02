# dbrowser

Database Browser as a RESTful Web Service.

Uses HSQLDB that persists to a file.


## Running the Web Service

`mvn spring-boot:run`


## Running the Interactive Documentation

1. `docker pull swaggerapi/swagger-editor`
2. `docker run -d -p 80:8080 swaggerapi/swagger-editor`
3. Go to [http://localhost](http://localhost)
4. Import the file `dbrowser.yml` using `File > Import file`
5. Try some requests using the button named `Try it out`


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

`GET http://localhost:8080/connections/123/schemas/PUBLIC/tables/DATABASE_CONNECTION/data`

`GET http://localhost:8080/connections/123/schemas/PUBLIC/tables/DATABASE_CONNECTION/statistics`

`GET http://localhost:8080/connections/123/schemas/PUBLIC/tables/DATABASE_CONNECTION/column/ID/statistics`
