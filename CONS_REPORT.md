# University Portal Backend Analysis Report

I have performed a deep analysis of your Spring Boot project. Below is a detailed breakdown of the project structure, functionality, and a list of critical **Cons** (Issues) that need addressing.

## Project Overview
*   **Framework**: Spring Boot 3.5.7 (Java 17)
*   **Database**: H2 In-Memory Database (Volatile)
*   **Architecture**: Standard Controller-Service-Repository patterns with JPA Entities.
*   **Frontend Compat**: Configured for `localhost:3000` (Next.js/React).

## 🚨 Critical Cons & Security Vulnerabilities

### 1. Security Flaws (High Risk)
*   **Plain Text Passwords**: Passwords are stored and compared in plain text.
    *   *Files*: `User.java`, `AuthService.java`, `DataInitializer.java`
    *   *Risk*: If the DB is compromised, all user accounts are stolen instantly.
*   **No Spring Security**: The project lacks the `spring-boot-starter-security` dependency and configuration.
    *   *Risk*: There is no actual authentication or authorization. Anyone can call any endpoint if they guess the URL.
    *   *Impact*: Endpoints like `/student/{id}` are effectively public.
*   **Insecure Direct Object References (IDOR)**:
    *   *Example*: `StudentController.getStudent(@PathVariable Long studentId)` allows getting **ANY** student's data by just changing the ID in the URL. There is no check to see if the logged-in user *is* that student.
*   **No Auth Token (JWT/Session)**:
    *   The `login` endpoints return a success message but do not issue a token (JWT) or set a secure session cookie. Subsequent requests are effectively unauthenticated.
*   **Hardcoded Credentials**:
    *   `DataInitializer.java` contains hardcoded passwords (`admin123`, `password`) and logic.

### 2. Infrastructure & Stability (Medium Risk)
*   **Data Loss on Restart**:
    *   Uses H2 In-Memory DB with `spring.jpa.hibernate.ddl-auto=create-drop`.
    *   *Impact*: Every time you stop the server, **ALL DATA IS DELETED**.
*   **Hardcoded File Paths**:
    *   `DataInitializer.java` line 287: `C:/Users/Ahsan/Desktop/lms-main/...`
    *   *Impact*: This code **will crash** on any computer that is not yours (or if you move the folder).
*   **Database Scalability**:
    *   H2 is for testing/dev. For a real backend, you need PostgreSQL or MySQL.

### 3. Code Quality & Best Practices (Low/Medium Risk)
*   **Logic in Initializer**:
    *   `DataInitializer` clears the DB and reseeds it on every run. This is slow and risky for anything beyond a toy demo.
*   **Lack of Testing**:
    *   Only `UniversityPortalApplicationTests.java` (context load) exists. No unit tests for services or controllers.
*   **Logging**:
    *   Uses `System.out.println` instead of a proper Logger (SLF4J).
*   **Error Handling**:
    *   `GlobalExceptionHandler` exists (Good!), but without proper security validation, it handles fewer implementation errors than it should.

## Recommendations for Next Steps
1.  **Add Spring Security**: Implement `SecurityFilterChain`, `BCryptPasswordEncoder`, and JWT authentication.
2.  **Switch to Real DB**: Configure PostgreSQL or MySQL in `application.properties`.
3.  **Fix File Paths**: Use relative paths or a configured property for file storage, not absolute C: drive paths.
4.  **Implement Access Control**: Ensure users can only access their own data (`@PreAuthorize` or service-level checks).
