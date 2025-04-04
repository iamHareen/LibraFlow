# LibraFlow - Library Management System

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-F2F4F9?style=for-the-badge&logo=spring-boot)
![MySQL](https://img.shields.io/badge/mysql-%2300f.svg?style=for-the-badge&logo=mysql&logoColor=white)

## Introduction
LibraFlow is a modern library management system built with Java Spring Boot, designed to streamline book and user management efficiently. It provides a secure and scalable solution for handling library operations.

## Features
- 📚 **Book Management** (Add, Update, Delete, Search)
- 🔐 **JWT Authentication** (Secure user login)
- 👥 **Role-based Access Control** (Admin/User)
- 🗃️ **MySQL Database Integration**
- 🚀 **REST API Endpoints**

## Tech Stack
| Category       | Technologies                          |
|----------------|---------------------------------------|
| **Backend**    | Java 17, Spring Boot 3, Spring Security |
| **Database**   | MySQL 8                               |
| **Auth**       | JWT Tokens                            |
| **Build Tool** | Maven                                 |

## Prerequisites
- Java 17+
- Maven 3.6+
- MySQL 8.0+
- Git

## Installation

### 1. Clone the Repository
```bash
git clone https://github.com/iamHareen/LibraFlow.git
cd LibraFlow
```

### 2. Configure the database
- Create a MySQL database.
- Update database credentials in application-local.properties:
```bash
spring.datasource.url=jdbc:mysql://localhost:3306/[database_name]?createDatabaseIfNotExist=true
spring.datasource.username=[username]
spring.datasource.password=[password]
```

### 3. Build and run the project
```bash
mvn clean install
mvn spring-boot:run
```

### 4. Access the application
- The application will start on http://localhost:8080/



