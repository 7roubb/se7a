# 🩺 Se7a Healthcare Backend

This is the backend service for the **Se7a Healthcare System**, built with **Spring Boot** and **PostgreSQL**. It handles patient visits, prescriptions, diagnoses, and more.

![image](https://github.com/user-attachments/assets/aa56d565-71e1-48c1-8ee1-9157904774be)

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

## Database Diagram:
![image](https://github.com/user-attachments/assets/cd99282c-87fb-4d51-bd46-df84d53cc6e5)

## 🌟 Key Features

### 🔐 Flexible Role-Based Access Control
- **Admin**: Full control over system settings and configurations.
- **Doctor**: Access to patient records and diagnosis tools.
- **Nurse**: Can monitor vital signs and add medical notes.
- **Secretary**: Manages appointment scheduling and visit organization.

### 📊 Powerful Statistics Module
- Patient statistics by **gender**, **age**, **chronic diseases**, and **smoking status**.
- Track number of visits **per day or month**.
- Export statistics and reports to **PDF** with a single click.

### 🧾 Patient Medical Record Access
- Patients can view their **medical history** using only their **national ID number**.
- No full login required for patients.
- Designed for quick and smooth sharing of records with patients.

### 📱 OTP-Based Login System
- Login using **phone number** and **OTP (One-Time Password)**.
- OTP is sent via **SMS**.
- **Rate limiting** is enforced to protect against abuse and brute-force attempts.

### ✅ Additional Features
- Clean, scalable, and well-documented **REST API**.
- Support for **multi-clinic** and **multi-user** environments.
- Highly **flexible data model** for editing and expanding medical fields.
****
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
