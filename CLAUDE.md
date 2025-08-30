ㅋ# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

This is a Spring Boot web application called "hello-james" for managing campus facilities, transportation routes, and user authentication. The application provides both web views (using Thymeleaf) and API endpoints for campus navigation and facility information.

## Common Commands

### Build and Run
- `./gradlew build` - Build the application
- `./gradlew bootRun` - Run the application (starts on port 80)
- `./gradlew test` - Run tests
- `./gradlew clean` - Clean build artifacts

### Development
- `./gradlew bootRun --args='--spring.profiles.active=dev'` - Run with dev profile
- `./gradlew test --tests HelloJamesApplicationTests` - Run specific test class

## Architecture

### Core Structure
- **Package**: `edu.yonsei.hello_james`
- **Database**: MySQL with JPA/Hibernate (DDL auto-create enabled)
- **Security**: Spring Security with BCrypt password encoding
- **Frontend**: Thymeleaf templates with static resources (CSS/JS)

### Key Components
- **Entities**: Route, Stop, RouteStop, Facility, Internal, User
- **Controllers**: 
  - `ApiController` - REST endpoints under `/api`
  - `ViewController` - Thymeleaf view mappings
  - `AuthController` - Authentication/registration
- **Services**: Business logic for each entity type
- **Repositories**: JPA repositories for data access

### Data Model
- Transportation system with Routes containing multiple Stops via RouteStop junction table
- Facilities with image upload capabilities
- User management with role-based access (ADMIN role for `/admin/**` paths)
- Internal content management

### File Upload System
- Upload directory: `src/main/resources/static/uploads`
- Accessible via `/uploads/**` URL pattern
- ImageService handles file operations

### Security Configuration
- Most endpoints publicly accessible (`permitAll()`)
- Admin endpoints require ADMIN role
- Form-based authentication with custom login page
- CSRF disabled for API usage

### Database Configuration
- Uses MySQL connector
- Hibernate DDL auto-create mode (recreates schema on startup)
- SQL initialization from `data.sql` with sample stop data
- Connection details in `application.properties`