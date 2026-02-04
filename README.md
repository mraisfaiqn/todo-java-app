# Full-Stack Todo List

A robust, multi-user Task Management application featuring a **Spring Boot REST API** backend and a dynamic **Vanilla JavaScript** frontend. This project demonstrates secure user authentication, relational database management, and a seamless CRUD (Create, Read, Update, Delete) workflow.

## Project Description
Unlike basic todo lists, this application is built with a **User-Centric Architecture**. It allows individuals to register accounts and maintain private task lists. The system ensures that users only see and interact with their own data through a linked relational database model.

---

## System Architecture
The project follows the **3-Tier Architecture** pattern:
1.  **Presentation Tier:** HTML5, CSS3, and JavaScript (Fetch API).
2.  **Logic Tier:** Spring Boot Services and Controllers.
3.  **Data Tier:** PostgreSQL (Relational Database) with Spring Data JPA.

---

## Pre-requisites
### Code Tools
- Install Java Development Kit (https://adoptium.net/en-GB)
- Install Node (https://nodejs.org/en/download)
- Install PostgreSQL & pgAdmin (https://www.postgresql.org/download/)

### Database Preparation
- Modify code with your PostgreSQL username/passwords: 
  - File path: todo-java-app/backend/todo-app/src/main/resources/application.properties
- Create local database using pgAdmin UI
```
CREATE DATABASE todo_db;
```

### VS Code Extensions
- Install Extension Pack for Java (Microsoft)
- Install Spring Boot Extension Pack (VMWare)

### Spring Initialisation:
- Spring Initializr: Create Maven Project 4.0.2 Java 25 LTS
- Dependencies:
  - Spring Web (Tools to create RESTful API endpoints, frontend<>backend communications)
  - Spring Data JPA (Simplify CRUD operations, manage database data using Java Objects instead of SQL)
  - PostgreSQL Driver (Allow Java app to connect to PostgreSQL database)
  - Spring Security (Handles login/register logic, provides foundation for JWT Authentication)
  - Lombok (Reduces boilerplate code, auto generate "Getters" and "Setters")

---

## Getting Started

1.  **Backend:** 
- Run the `TodoApplication.java` file in your IDE. The server starts on `http://localhost:8080`.
- Run Java App via Windows bash terminal
```
./mvnw spring-boot:run
```
2.  **Frontend:** 
- Open `login.html` in any modern web browser.
3.  **Database:**
- Access live database tables via pgAdmin UI 

---

## API Endpoints

### **Authentication**
* `POST /api/auth/register` — Create a new user account.
* `POST /api/auth/login` — Validate credentials and retrieve `userId`.

### **Task Operations**
* `GET /api/todos/{userId}` — Fetch all tasks for a specific user.
* `POST /api/todos/{userId}` — Create a new task for a user.
* `PUT /api/todos/{id}` — Update an existing task's title.
* `DELETE /api/todos/{id}` — Delete a specific task.

---

## Key Features

### User Access & Security
* **Authentication:** Dedicated Register and Login flows.
* **Session Persistence:** Uses `localStorage` to keep users logged in across page refreshes.
* **Protected Routes:** Automatic redirection to login if a session is not detected.

### Task Management (CRUD)
* **Create:** Instantly add tasks linked to your unique User ID.
* **Read:** Dynamic fetching of user-specific tasks upon dashboard entry.
* **Update:** Inline editing of task titles.
* **Delete:** Permanent removal of tasks with real-time UI updates.

---

## Remarks
- When using `spring.jpa.hibernate.ddl-auto=update`, Hibernate will automatically create the `users` and `items` tables for you the first time you run the app. You don't need to write any `CREATE TABLE` scripts yourself!