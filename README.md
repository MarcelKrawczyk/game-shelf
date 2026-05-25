# Games App

A web application for managing a video game collection, built with Spring Boot.

## Requirements

- Java 17+
- Maven 3.8+

Check your installation:
```
java -version
mvn -version
```

## Running

```
mvn clean package -DskipTests
java -jar target/games-app-1.0.jar
```

Once started:

| Address | Description |
|---|---|
| `http://localhost:8080` | Web UI |
| `http://localhost:8080/data` | REST API |
| `http://localhost:8080/h2-console` | Database console |

## REST API

| Method | Endpoint | Description |
|---|---|---|
| GET | `/data` | Get all games |
| GET | `/data?sort=rating&order=desc` | Get games sorted |
| GET | `/data/{id}` | Get game by ID |
| POST | `/data` | Add a game |
| PUT | `/data/{id}` | Update a game |
| DELETE | `/data/{id}` | Delete a game |

## Database

Uses H2 file-based database stored in `games.mv.db`.
Data is persistent across restarts.

DB Console credentials:
- **JDBC URL:** `jdbc:h2:file:./games`
- **User:** `sa`
- **Password:** *(leave empty)*

## Tech Stack

| Technology | Purpose |
|---|---|
| Java 17 | Language |
| Spring Boot 3.2 | Backend framework |
| Spring Data JPA | Database access |
| H2 Database | File-based database |
| Thymeleaf | HTML templates |
| Bootstrap 5 | UI styling |
| Maven | Build tool |
