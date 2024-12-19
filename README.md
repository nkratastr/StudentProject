# Student Management System

A comprehensive Spring Boot application for managing student information, courses, and educational activities.

## 🚀 Features

- User Authentication and Authorization
- Student Management
- Course/Lesson Management
- Education Term Management
- Meeting Management
- Student Information System
- Role-based Access Control

## 🛠 Tech Stack

- Java 11
- Spring Boot 2.5.14
- Spring Security with JWT
- Spring Data JPA
- PostgreSQL Database
- Maven
- Hibernate
- Lombok

## 📋 Prerequisites

- Java 11 or higher
- PostgreSQL 
- Maven (included via wrapper)

## 🔧 Configuration

1. **Database Setup**
   ```properties
   spring.datasource.url= jdbc:postgresql://localhost:5432/student_management_db
   spring.datasource.username= db_user
   spring.datasource.password= db_password
   ```

2. **JWT Configuration**
   ```properties
   backendapi.app.jwtExpirationMs=8640000
   backendapi.app.jwtSecret=schoolmanagementproject
   ```

## 🚀 Getting Started

1. Clone the repository
   ```bash
   git clone https://github.com/nkratastr/StudentProject.git
   ```

2. Create PostgreSQL database
   ```sql
   CREATE DATABASE student_management_db;
   ```

3. Run the application
   ```bash
   ./mvnw spring-boot:run
   ```

4. Access the application at `http://localhost:8080`

## 📁 Project Structure

```
src/main/java/com/project/
├── config/          # Configuration files
├── controller/      # REST API endpoints
├── entity/          # Database entities
│   ├── concretes/
│   │   ├── business/
│   │   └── user/
│   └── enums/
├── repository/      # Data access layer
├── service/         # Business logic
│   ├── business/
│   ├── helper/
│   ├── user/
│   └── validator/
├── security/        # Security configurations
└── payload/         # DTOs
```

## 🔐 Security

- JWT-based authentication
- Role-based authorization
- Secure password handling

## 📚 Main Components

1. **Education Management**
   - Education Terms
   - Lessons
   - Lesson Programs
   - Student Information

2. **User Management**
   - Authentication
   - User Roles
   - User Services

3. **Meeting System**
   - Schedule Meetings
   - Meeting Management

## 🤝 Contributing

1. Fork the repository
2. Create your feature branch
3. Commit your changes
4. Push to the branch
5. Open a Pull Request

## 📝 License

This project is licensed under the MIT License - see the LICENSE file for details.
