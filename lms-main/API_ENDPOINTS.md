# API Endpoints & Testing Guide

This document lists all available API endpoints for the University Portal, along with example requests using the seeded data.

## Base URL
`http://localhost:8080`

## 1. Student Portal (`/student`)
Use these endpoints to view data for a specific student.
**Seeded Student IDs**: 1 to 50

| Method | Endpoint | Description | Example URL |
| :--- | :--- | :--- | :--- |
| `GET` | `/student/{studentId}` | Get student profile | `/student/1` |
| `GET` | `/student/{studentId}/courses` | Get enrolled courses | `/student/1/courses` |
| `GET` | `/student/{studentId}/marks` | Get marks for all courses | `/student/1/marks` |
| `GET` | `/student/{studentId}/attendance` | Get attendance summary | `/student/1/attendance` |
| `GET` | `/student/{studentId}/assignments` | Get assignment status | `/student/1/assignments` |

## 2. Teacher Portal (`/teacher`)
Use these endpoints for teacher operations.
**Seeded Teacher IDs**: 1 to 10
**Seeded Course Codes**: CS-101, CS-102, CS-201, etc.
**Seeded Batches**: Batch-A, Batch-B, Batch-C, Batch-D, Batch-E

| Method | Endpoint | Description | Example URL |
| :--- | :--- | :--- | :--- |
| `GET` | `/teacher/{teacherId}/courses` | Get courses taught by teacher | `/teacher/1/courses` |
| `GET` | `/teacher/courses/{courseCode}/{batchName}` | Get students in a course & batch | `/teacher/courses/CS-101/Batch-A` |
| `POST` | `/teacher/course/{courseId}/attendance` | Mark attendance for students | `/teacher/course/1/attendance` |
| `POST` | `/teacher/announcement` | Create an announcement | *(See JSON Body below)* |
| `POST` | `/teacher/assignment` | Create an assignment | *(See JSON Body below)* |
| `POST` | `/teacher/marks` | Record marks for a student | *(See JSON Body below)* |

### Example JSON Bodies for Teacher POST Requests

**Mark Attendance (`POST /teacher/course/1/attendance`)**
```json
[
  {
    "studentId": 1,
    "present": true
  },
  {
    "studentId": 2,
    "present": false
  }
]
```

**Create Announcement (`POST /teacher/announcement`)**
```json
{
  "courseId": 1,
  "message": "Class is cancelled tomorrow."
}
```

**Create Assignment (`POST /teacher/assignment`)**
```json
{
  "title": "Lab 1",
  "description": "Complete the lab manual.",
  "dueDate": "2024-12-31",
  "teacherId": 1,
  "courseId": 1
}
```

**Record Marks (`POST /teacher/marks`)**
```json
{
  "studentId": 1,
  "courseId": 1,
  "quizMarks": 8.5,
  "assignmentMarks": 9.0,
  "midsMarks": 22.0,
  "finalMarks": 45.0
}
```

## 3. Admin Portal (`/admin`)
Use these endpoints for administrative tasks.

| Method | Endpoint | Description | Example URL |
| :--- | :--- | :--- | :--- |
| `GET` | `/admin/teachers` | Get all teachers | `/admin/teachers` |
| `GET` | `/admin/courses/{courseCode}/{batchName}` | Get students (Admin view) | `/admin/courses/CS-101/Batch-A` |
| `POST` | `/admin/student` | Create a new student | *(See JSON Body below)* |
| `POST` | `/admin/teacher` | Create a new teacher | *(See JSON Body below)* |
| `POST` | `/admin/course` | Create a new course | *(See JSON Body below)* |
| `POST` | `/admin/batch/assign` | Assign students to a batch | *(See JSON Body below)* |
| `POST` | `/admin/course/assign-teacher` | Assign teacher to course | *(See JSON Body below)* |
| `POST` | `/admin/course/assign-students` | Assign students to course | *(See JSON Body below)* |

### Example JSON Bodies for Admin POST Requests

**Create Student (`POST /admin/student`)**
```json
{
  "name": "John Doe",
  "regNo": "CS-999",
  "emailAddress": "john.doe@student.com",
  "contactNumber": "0300-1234567",
  "program": "BSCS",
  "session": "2024-2028",
  "semester": "1",
  "campus": "Main",
  "className": "CS-1A",
  "dob": "2005-01-01",
  "password": "password123",
  "batch": "Batch-A"
}
```

**Assign Batch (`POST /admin/batch/assign`)**
```json
{
  "studentIds": [1, 2, 3],
  "batch": "Batch-B"
}
```
