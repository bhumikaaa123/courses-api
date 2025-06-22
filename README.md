# Courses API Backend

This is the backend service for the IIT Bombay Internship Application Assignment. It provides a REST API for managing courses and course instances using **Spring Boot**.

---

## 📌 Features

- Create, list, and delete **Courses**
- Add **Prerequisites** to courses
- Create, list, and delete **Course Instances**
- Validations for prerequisites and dependencies
- Integrated with **MySQL** (can switch to H2 or others if needed)

---

## 🛠️ Tech Stack

- Java 17
- Spring Boot 3.x
- Spring Data JPA
- MySQL (or in-memory H2)
- Docker

---

## 🔗 API Endpoints

### Courses

- `POST /api/courses` – Create a new course
- `GET /api/courses` – List all courses with prerequisites
- `GET /api/courses/{id}` – Get course by ID
- `DELETE /api/courses/{id}` – Delete course (only if not a prerequisite)

### Course Instances

- `POST /api/instances` – Create a course instance
- `GET /api/instances/{year}/{semester}` – Get all instances in given year/semester
- `GET /api/instances/{year}/{semester}/{courseId}` – Get details of one instance
- `DELETE /api/instances/{year}/{semester}/{courseId}` – Delete a course instance

---

## 🐳 Docker

### Build Docker Image
```bash
docker build -t bhumikaaa123/courses-api-backend .
