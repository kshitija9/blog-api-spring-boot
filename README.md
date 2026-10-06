# Blog API

A production-style REST API built with Spring Boot 3, JWT authentication, and MySQL.

## Tech Stack

- Java 17
- Spring Boot 3.3.5
- Spring Data JPA + Hibernate
- Spring Security + JWT (JJWT 0.12.5)
- MySQL 8
- Lombok
- Jakarta Bean Validation
- Maven

## Features

- User registration with BCrypt password hashing
- JWT-based login and authentication
- Full CRUD operations for blog posts
- Input validation with custom error messages
- Global exception handling with structured JSON responses
- Audit trail (createdAt, updatedAt) via JPA lifecycle callbacks
- DTOs for API contract separation
- Layered architecture (Controller -> Service -> Repository)

## API Endpoints

| Method | Endpoint | Auth Required | Description |
|--------|----------|---------------|-------------|
| POST | /auth/register | No | Register a new user |
| POST | /auth/login | No | Login and receive JWT |
| GET | /api/posts | Yes (JWT) | Get all posts |
| POST | /api/posts | Yes (JWT) | Create a new post |
| GET | /api/posts/{id} | Yes (JWT) | Get a post by ID |
| PUT | /api/posts/{id} | Yes (JWT) | Update a post |
| DELETE | /api/posts/{id} | Yes (JWT) | Delete a post |

## Sample Requests

### Register