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

---

## JavaScript Frontend
The frontend build:
- Port: 3000
- Dependencies
  - Nodemon (Automatically restartS app upon changes made to code)
  - EJS (Node template engine to generate HTML markup with JavaScript)
  - Express (Node web application framework) 


---

## Run Commands

---

## Remarks