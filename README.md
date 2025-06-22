# Courses API Backend

This is the backend service for the IIT Bombay Internship Assignment. It provides REST APIs to manage courses and their instances.

---

## 🚀 Features

- Add new courses with prerequisites
- Get course list and individual course details
- Create course instances for specific semesters
- Delete courses (with validation if used as prerequisites)
- Delete course instances

---

## 🛠 Tech Stack

- Java 17
- Spring Boot 3.x
- Spring Data JPA
- MySQL or H2 (for dev/testing)
- Docker

---

## 📡 API Endpoints

### Courses
- `POST /api/courses` – Create a course
- `GET /api/courses` – Get all courses
- `GET /api/courses/{id}` – Get course by ID
- `DELETE /api/courses/{id}` – Delete a course (only if not a prerequisite)

### Course Instances
- `POST /api/instances` – Create a course instance
- `GET /api/instances/{year}/{semester}` – List all instances for a semester
- `GET /api/instances/{year}/{semester}/{courseId}` – View one instance
- `DELETE /api/instances/{year}/{semester}/{courseId}` – Delete an instance

---

## 🐳 Docker Instructions

### Build Image
```bash
docker build -t bhumikaaa123/courses-api-backend .
```

### Run Container
```bash
docker run -p 8080:8080 bhumikaaa123/courses-api-backend
```

---

## 📦 DockerHub Image

https://hub.docker.com/r/bhumikaaa123/courses-api-backend

---

## 🧩 Used in Docker Compose

This service is used alongside the frontend in a `docker-compose.yaml` setup.

```bash
docker-compose up --pull always
```

---

## ✅ Requirements Completed

- ✅ REST API in Spring Boot
- ✅ Validations for prerequisites and dependencies
- ✅ Docker image created and pushed
- ✅ Works with frontend via docker-compose
