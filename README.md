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
- Create local database usin pgAdmin UI
- Create database table
```
CREATE TABLE items (
  id SERIAL PRIMARY KEY,
  title VARCHAR(100) NOT NULL
);
```

### VS Code Extensions
- Install Extension Pack for Java (Microsoft)
- Install Spring Boot Extension Pack (VMWare)

---

## Java Backend
The backend build:
The Spring Boot application handling the logic, security, and database connection

- Spring Initializr: Create Maven Project 4.0.2 Java 25
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

---

## Run Commands

---

## Remarks