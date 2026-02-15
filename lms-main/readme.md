

# **University Student Portal – Backend API (Under Process)**

Welcome to the backend API repository for the **University Student Management Portal** project.
This project is a **RESTful API built with Spring Boot**, designed to manage **student records** and **course enrollments**.
It follows a **decoupled architecture**, intended to be consumed by a separate frontend (e.g., **Next.js**).

---

## 📌 **Technologies Used**

* **Java 25**
* **Spring Boot 3.5.7**
* **Maven** (dependency management)
* **PostgreSQL** (Production Database)
* **Spring Security & JWT** (Authentication & Authorization)
* **JPA / Hibernate** (ORM)
* **Lombok** (optional – for getter/setter generation)

---

## 🚀 **Getting Started (Local Development)**

### **Prerequisites**

Make sure you have the following installed:

* **JDK 25+**
* **Maven**
* **PostgreSQL** (running on port 5432)

---

## 🔧 **Installation & Run**

### **1. Clone the repository**

```bash
git clone <your-repo-url>
cd lms
```

### **2. Database Setup**
Ensure you have a PostgreSQL database named `university_portal`.
The application is configured with `ddl-auto=update`, so tables will be created automatically.

### **3. Build the project**

```bash
mvn clean install
```

### **4. Run the application**

```bash
mvn spring-boot:run
```

The API will be running at:

➡️ **[http://localhost:8080](http://localhost:8080)**

---

## 🔐 **Authentication**

The API uses **JWT (JSON Web Token)** for security.
1. **Login** using `/student/login`, `/teacher/login`, or `/admin/login` to receive a `token`.
2. **Access Protected Routes** by adding the following header to your requests:
   `Authorization: Bearer <your_token>`

---

## 📡 **API Endpoints**

| Method | Endpoint         | Description                | Access |
| ------ | ---------------- | -------------------------- | ------ |
| POST   | `/student/login` | Login and get JWT          | Public |
| GET    | `/student/{id}`  | Get student profile        | Auth   |
| GET    | `/students`      | Retrieve all students      | Admin  |


---

## 🧪 **API Testing**

Use any of the following tools:

* **Thunder Client** (VS Code)
* **Postman**
* **Insomnia**

---

## ⚠️ **Error Handling**

A global exception handler (`@ControllerAdvice`) ensures consistent responses:

* **Validation errors → 400 Bad Request**

  * Includes field-specific JSON messages
* **Not Found errors → 404 Not Found**

  * Example: “Student not found”

---

## 📌 Reminder

Update your `git clone` URL with your actual GitHub repository link before publishing!

---
