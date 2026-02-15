# API Endpoints & Testing Guide

This document lists all available API endpoints for the University Portal. The application uses **JWT Authentication**.

## 🔐 Authentication & Headers
**All protected endpoints require the following HTTP Header:**
- **Key**: `Authorization`
- **Value**: `Bearer <your_token>`

*Get the token by logging in via the `/auth` endpoints below.*

## Base URL
`http://localhost:8080`

---

## 1. Authentication (Login)
All login endpoints return a **JWT Token** upon success.

### 1.1 Student Login
| Method | Endpoint | Description | Public |
| :--- | :--- | :--- | :--- |
| `POST` | `/student/login` | Login as Student | ✅ |

**Request Body:**
```json
{
  "id": 1,
  "regNo": "CS-001",
  "password": "password"
}
```

**Success Response (200 OK):**
```json
{
  "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ...",
  "email": "student@university.edu",
  "role": "STUDENT"
}
```

### 1.2 Teacher Login
| Method | Endpoint | Description | Public |
| :--- | :--- | :--- | :--- |
| `POST` | `/teacher/login` | Login as Teacher | ✅ |

**Request Body:**
```json
{
  "id": 1,
  "password": "password"
}
```

**Success Response:** Same structure as Student.

### 1.3 Admin Login
| Method | Endpoint | Description | Public |
| :--- | :--- | :--- | :--- |
| `POST` | `/admin/login` | Login as Admin | ✅ |

**Request Body:**
```json
{
  "id": 1,
  "password": "admin123"
}
```

**Success Response:** Same structure as Student.

---

## 2. Student Portal (`/student`)
**Requires `Authorization` Header.**

| Method | Endpoint | Description |
| :--- | :--- | :--- |
| `GET` | `/student/{studentId}` | Get student profile |
| `GET` | `/student/{studentId}/courses` | Get enrolled courses (includes assignments) |
| `GET` | `/student/{studentId}/marks` | Get marks for all courses |
| `GET` | `/student/{studentId}/attendance` | Get attendance summary |
| `POST` | `/student/assignment/submit` | Submit an assignment |

**Submit Assignment Body:**
```json
{
  "studentId": 1,
  "assignmentId": 5,
  "fileUrl": "https://url-to-file.pdf"
}
```

---

## 3. Teacher Portal (`/teacher`)
**Requires `Authorization` Header.**

| Method | Endpoint | Description |
| :--- | :--- | :--- |
| `GET` | `/teacher/{teacherId}` | Get teacher profile |
| `GET` | `/teacher/{teacherId}/courses` | Get courses taught by teacher |
| `GET` | `/teacher/courses/{courseCode}/{batchName}` | Get students in a course & batch |
| `POST` | `/teacher/course/{courseId}/attendance` | Mark attendance for students |
| `POST` | `/teacher/announcement` | Create an announcement |
| `POST` | `/teacher/assignment` | Create an assignment |
| `GET` | `/teacher/assignment/{assignmentId}/submissions` | Get student submissions |
| `POST` | `/teacher/marks` | Record marks |

**Create Assignment Body:**
```json
{
  "title": "Lab 1",
  "description": "Lab Manual 1",
  "dueDate": "2024-12-31",
  "teacherId": 1,
  "courseId": 1,
  "fileUrl": "http://resources..."
}
```

---

## 4. Admin Portal (`/admin`)
**Requires `Authorization` Header.**

| Method | Endpoint | Description |
| :--- | :--- | :--- |
| `GET` | `/admin/teachers` | Get all teachers |
| `GET` | `/admin/students` | Get all students |
| `GET` | `/admin/courses` | Get all courses |
| `GET` | `/admin/batches` | Get all batches |
| `POST` | `/admin/student` | Create a new student |
| `POST` | `/admin/teacher` | Create a new teacher |
| `POST` | `/admin/course` | Create a new course |
| `POST` | `/admin/batch/assign` | Assign students to batch |

---

## 5. File Management (`/files`)
**Public Access (No Header Required)**

| Method | Endpoint | Description |
| :--- | :--- | :--- |
| `POST` | `/files/upload` | Upload a file (multipart) |
| `GET` | `/files/download?path={filename}` | Download a file |
