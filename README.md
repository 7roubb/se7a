# 🩺 Se7a Healthcare Backend

This is the backend service for the **Se7a Healthcare System**, built with **Spring Boot** and **PostgreSQL**. It handles patient visits, prescriptions, diagnoses, and more.

---

## 🚀 Getting Started

### Prerequisites
- Java 17+
- Maven
- Docker & Docker Compose

---

## Technologies Used
- **Spring Boot 6**: Backend framework
- **Spring Data JPA**: For ORM with PostgreSQL
- **PostgreSQL**: For storing user and task data
- **Docker Compose**: To run PostgreSQL and Redis as services
## Spring Security 6 Diagram:
![image](https://github.com/user-attachments/assets/985357c9-a5c3-42a0-9263-aa4cfa2a1f37)

## 🔧 Build & Run

### 1. Build the Project

```bash
mvn clean install -DskipTests
```

This will compile the project and generate the .jar file in the target directory.

2. Run with Docker
```bash
docker-compose up --build
```
This will:

Start the PostgreSQL database

Build and run your Spring Boot app inside a Docker container

📦 App Structure
/src/main/java – Your Spring Boot source code

/src/main/resources – App configuration (application.yml)

Dockerfile – Docker image setup for the backend

docker-compose.yml – Multi-container setup (App + DB)

🔌 Default Services

### Service	Port
| Service       | Port  |
|---------------|-------|
| App (Spring Boot) | 8080  |
| PostgreSQL    | 5432  |



🧠 Useful Commands 
Rebuild everything

```bash

docker-compose down -v
mvn clean install -DskipTests
docker-compose up --build
```

🛑 Stopping the Containers

```bash
docker-compose down
```
