# To-Do List Web Application

## Description
A full-stack Task Management application built to demonstrate the integration between a Java REST API and a JavaScript (HTML/CSS) frontend. The application allows users to perform full CRUD operations on tasks, with data persisted in a local PostgreSQL database.

---

## Pre-requisites
### Code Tools
- Install Java Development Kit (https://adoptium.net/en-GB)
- Install Node (https://nodejs.org/en/download)
- Install PostgreSQL & pgAdmin (https://www.postgresql.org/download/)



### Database Preparation
- Modify code with your PostgreSQL username/passwords
- Create local database using pgAdmin UI
```
CREATE DATABASE todo_db;
```

---

### VS Code Extensions
- Install Extension Pack for Java (Microsoft)
- Install Spring Boot Extension Pack (VMWare)

---

## Java Backend
The backend build:
The Spring Boot application handling the logic, security, and database connection
### Features:
  - Organized code into Controller (Web), Service (Logic), and Repository (Data) layers to improve maintainability.
  - Centralized business rules, implementing checks to ensure tasks cannot have empty titles.
  - Introduced Data Transfer Objects to decouple the API from database entities, improving security and flexibility.
  - Enhanced the Repository with findAllByOrderByIdAsc() to ensure consistent task ordering from the database.

### Initialisation:
- Spring Initializr: Create Maven Project 4.0.2 Java 25 LTS
- Dependencies:
  - Spring Web (Tools to create RESTful API endpoints, frontend<>backend communications)
  - Spring Data JPA (Simplify CRUD operations, manage database data using Java Objects instead of SQL)
  - PostgreSQL Driver (Allow Java app to connect to PostgreSQL database)
  - Spring Security (Handles login/register logic, provides foundation for JWT Authentication)
  - Lombok (Reduces boilerplate code, auto generate "Getters" and "Setters")

---

## JavaScript Frontend
The frontend build:
The user interface built with HTML, CSS, and JS that communicates with the backend via a RESTful API

### Features:
- Separated concerns by moving logic into script.js, keeping index.html focused strictly on structure.
- Built a Vanilla JavaScript client that communicates asynchronously with the Spring Boot API using the Fetch API.
- Developed logic to transform JSON data from the backend into interactive HTML list items.
- Utilized JavaScript closures to lock each button to its specific task ID for accurate Delete and Edit operations.

---

## Run Commands
- Run Java App 
```
./mvnw spring-boot:run
```
- Test Database
```
Create Data
curl -i -X POST http://localhost:8080/api/todos \-H "Content-Type: application/json" \-d "{\"title\": \"Final security test\"}"

Read Data
http://localhost:8080/api/todos

Update Data
curl -i -X PUT http://localhost:8080/api/todos/2 \-H "Content-Type: application/json" \-d "{\"title\": \"NEW_TITLE_HERE\"}"

Delete Data
curl -i -X DELETE http://localhost:8080/api/todos/2
```

---

## Remarks