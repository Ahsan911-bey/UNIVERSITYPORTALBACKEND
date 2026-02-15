# Post-Migration Documentation: Database & Authentication

This document outlines the complete migration process from an H2/In-Memory database with mock authentication to a production-ready PostgreSQL database with robust Spring Security and JWT-based authentication.

## 1. Database Migration (H2 -> PostgreSQL)

### Motivation
The project previously used an in-memory H2 database, which lost data on every restart. We migrated to PostgreSQL for persistence, scalability, and better production readiness.

### Configuration Changes
1.  **Dependencies (`pom.xml`)**:
    - Removed `h2` database dependency.
    - Added `postgresql` driver dependency.

2.  **Properties (`application.properties`)**:
    - Updated `spring.datasource.url` to `jdbc:postgresql://localhost:5432/university_portal`.
    - Updated `spring.datasource.username` to `postgres`.
    - Updated `spring.datasource.password` to `123`.
    - Set `spring.jpa.database-platform` to `org.hibernate.dialect.PostgreSQLDialect`.
    - Set `spring.jpa.hibernate.ddl-auto` to `update` (preserves data across restarts).

3.  **Data Seeding (`DatabaseSeeder.java`)**:
    - Refactored to be **idempotent**: It now checks if users exist before running. This prevents duplicate data entry on subsequent restarts.
    - **Password Hashing**: All seeded users (Admins, Teachers, Students) now have their passwords hashed using `BCryptPasswordEncoder` (strength 10). Plain text passwords are no longer stored.

## 2. Authentication Migration (Fake -> Spring Security + JWT)

### Motivation
The previous authentication system was insecure, storing passwords in plain text and using simple string comparisons without any session management or token-based security. We implemented Spring Security with JSON Web Tokens (JWT) for stateless, secure authentication.

### Key Components Implemented

1.  **Dependencies**:
    - Added `spring-boot-starter-security` and `jjwt` (Java JWT) libraries.

2.  **Security Configuration (`SecurityConfig.java`)**:
    - **CSRF Disabled**: As we are using stateless JWT authentication.
    - **Session Management**: Set to `STATELESS`.
    - **Public Endpoints**: `/student/login`, `/teacher/login`, `/admin/login`, and `/files/**` are open.
    - **Protected Endpoints**: All other endpoints require authentication.

3.  **JWT Implementation**:
    - **`JwtUtils.java`**: Handles token generation (HMAC/SHA256 signature), validation, and extraction of usernames.
    - **`JwtAuthenticationFilter.java`**: A filter that intercepts every request, checks for the `Authorization: Bearer <token>` header, validates the token, and sets the Spring Security `authentication` context.
    - **`AuthEntryPointJwt.java`**: Handles unauthorized access attempts by returning a 401 Unauthorized error.

4.  **User Details Service (`CustomUserDetailsService.java`)**:
    - Loads user data from the `UserRepository` based on email.
    - Converts our `User` entity into Spring Security's `UserDetails` format.

5.  **Authentication Flow Refactoring**:
    - **`AuthService.java`**:
        - Now uses `PasswordEncoder.matches(rawPassword, encodedPassword)` to verify credentials securely.
        - Generates a JWT token upon successful verification using `JwtUtils`.
        - Returns a `JwtResponse` object containing the token.
    - **`AuthController.java`**:
        - Updated login endpoints (`/student/login`, etc.) to return `ResponseEntity<JwtResponse>`.

## 3. How to Verify & Run

1.  **Prerequisites**:
    - Ensure PostgreSQL is running on port `5432`.
    - Create a database named `university_portal`.

2.  **Build & Run**:
    - Run `mvn clean install` to build the project.
    - Start the application. The `DatabaseSeeder` will populate the database if it's empty.

3.  **Testing Login**:
    - Send a POST request to `http://localhost:8080/student/login` (or teacher/admin).
    - Body: `{"email": "student@example.com", "password": "password"}` (or seeded credentials).
    - Response: You will receive a JSON object with a `token`.

4.  **Accessing Protected Routes**:
    - Copy the `token` from the login response.
    - For subsequent requests (e.g., getting student profile), add a header:
        - Key: `Authorization`
        - Value: `Bearer <your_token_here>`

## 4. Future Improvements
- **Role-Based Access Control (RBAC)**: While roles are assigned, specific endpoint authorization (e.g., `@PreAuthorize("hasRole('ADMIN')")`) can now be easily added to controllers.
- **Frontend Integration**: The frontend needs to be updated to store the JWT and send it with every request.
