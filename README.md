# bookAuthor-Manager

A REST API built with Spring Boot for managing books and authors. The application supports CRUD operations for both entities and allows assigning multiple authors to a book through a many-to-many relationship.

## Tech Stack

- **Language:** Java 21
- **Framework:** Spring Boot 4.0.6
- **Database:** MySQL 8+
- **ORM:** Hibernate / Spring Data JPA
- **Validation:** Jakarta Validation
- **API Documentation:** Swagger OpenAPI
- **Build Tool:** Maven

---

## Project Structure

The application follows a layered architecture:

```text
src/main/java/com/iekakmi/bookAuthorManager/
├── api/
│   ├── configurations/       # Swagger and CORS configuration
│   └── controllers/          # REST controllers
│
├── business/
│   ├── DTOs/                 # Data Transfer Objects
│   └── Services/             # Business logic
│
├── domain/
│   ├── entities/             # JPA entities
│   └── repositories/         # Spring Data repositories
│
└── BookAuthorManagerApplication.java
```

---

## Domain Model

### Author

Represents a book author.

| Field | Type |
|---------|---------|
| id | Integer |
| name | String |
| nationality | String |
| birthDate | LocalDate |

### Book

Represents a book.

| Field | Type |
|---------|---------|
| isbn | String |
| title | String |
| category | String |
| publicationYear | Integer |

### Relationship

```text
Book
  ↕ Many-To-Many
Author
```

A book can have multiple authors and an author can write multiple books.

The relationship is stored in the join table:

```text
book_author
```

---


## Features

### Author Management

- Create Author
- Get All Authors
- Get Author By ID
- Update Author
- Delete Author
- Get All Books Written By An Author

### Book Management

- Create Book
- Get All Books
- Get Book By ISBN
- Update Book
- Delete Book
- Assign Authors To A Book

---

## Prerequisites

Before running the project make sure you have:

- JDK 21
- Maven 3.9+
- MySQL 8+

---

## Database Configuration

Configuration is located in:

```text
src/main/resources/application.properties
```

Example:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/library_db
spring.datasource.username=root
spring.datasource.password=
spring.jpa.hibernate.ddl-auto=update
```

Create a database named:

```sql
CREATE DATABASE library_db;
```

---

## Running The Application

Build the project:

```bash
mvn clean install
```

Run the application:

```bash
mvn spring-boot:run
```

The API will start on:

```text
http://localhost:8080
```

---

## API Documentation

Swagger UI:

```text
http://localhost:8080/swagger-ui.html
```

OpenAPI JSON:

```text
http://localhost:8080/v3/api-docs
```

---

## REST Endpoints

### Authors

| Method | Endpoint | Description |
|----------|------------|-------------|
| GET | /authors | Get all authors |
| GET | /authors/{id} | Get author by ID |
| POST | /authors | Create author |
| PUT | /authors | Update author |
| DELETE | /authors/{id} | Delete author |
| GET | /authors/{id}/books | Get books written by author |

---

### Books

| Method | Endpoint | Description |
|----------|------------|-------------|
| GET | /books | Get all books |
| GET | /books/{isbn} | Get book by ISBN |
| POST | /books | Create book |
| PUT | /books | Update book |
| DELETE | /books/{isbn} | Delete book |
| POST | /books/{isbn}/authors | Assign authors to book |

---

## Example Request

Create a Book:

```http
POST /books
```

```json
{
  "isbn": "9780134685991",
  "title": "Effective Java",
  "category": "Programming",
  "publicationYear": 2018
}
```


Assign Authors To Book:

```http
POST /books/9780134685991/authors
```

```json
[1, 2]
```

---

## Future Improvements

- Global Exception Handling
- Custom Exceptions
- Pagination
- Search Endpoints
- Unit Tests
- Integration Tests
