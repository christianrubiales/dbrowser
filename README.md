# dbrowser
Database Browser as a RESTful Web Service


## Example Requests
`GET http://localhost:8080/connections`

`POST http://localhost:8080/connections`
```javascript
{
	"id": "123",
	"name": "test",
	"databaseName": "connection",
	"userName": "SA",
	"password": "",
}
```

`PUT http://localhost:8080/connections/123`
```javascript
{
	"id": "123",
	"name": "test",
	"databaseName": "connection",
	"userName": "SA",
	"password": "",
}
```

`DELETE http://localhost:8080/connections/123`
