# Student Manager API

A simple **Spring Boot REST API** designed for **Docker** and **Kubernetes** labs.

This project provides a complete in-memory **Student Management API** with sample data loaded at startup.  
The main goal is to let students:

- pull a Docker image
- run the container locally
- push and pull images from a registry
- deploy the application to Kubernetes
- test REST endpoints
- work with probes and service exposure

---

## Project goals

This project is intentionally simple and focuses on:

- **Spring Boot REST APIs**
- **containerization with Docker**
- **image distribution through a registry**
- **deployment on Kubernetes**
- **testing endpoints with sample in-memory data**

There is **no external database**.  
All student data is stored **in memory**, so every restart resets the dataset.

---

## Tech stack

- Java 17
- Spring Boot 3
- Spring Web
- Spring Boot Actuator
- Spring Validation
- Maven
- Docker
- Kubernetes

---

## Project structure

```text
student-manager-api/
├── pom.xml
├── Dockerfile
├── .dockerignore
├── k8s/
│   ├── deployment.yaml
│   └── service.yaml
└── src/
    └── main/
        ├── java/
        │   └── com/example/studentmanager/
        │       ├── StudentManagerApiApplication.java
        │       ├── config/
        │       │   └── DataSeeder.java
        │       ├── controller/
        │       │   └── StudentController.java
        │       ├── dto/
        │       ├── exception/
        │       ├── model/
        │       ├── repository/
        │       └── service/
        └── resources/
            └── application.properties
```

---

## Features

- Full CRUD for students
- Search students by course and status
- Sample data loaded automatically at startup
- In-memory repository
- Health endpoint for Kubernetes probes
- Version endpoint for rollout verification
- Ready-to-use Dockerfile
- Ready-to-use Kubernetes manifests

---

## YouTube channel

Educational content, labs, and developer-focused videos by Giovanni Pace:

https://www.youtube.com/@archetydev

---

## Base URL

When running locally:

```text
http://localhost:8080
```

Base API path:

```text
/api/students
```

---

# API Endpoints

## 1. Get all students

**Endpoint**

```http
GET /api/students
```

**Description**  
Returns the full list of students currently stored in memory.

**Example request**

```bash
curl http://localhost:8080/api/students
```

**Example response**

```json
[
  {
    "id": 1,
    "firstName": "John",
    "lastName": "Carter",
    "email": "john.carter@example.com",
    "course": "Computer Science",
    "age": 21,
    "status": "ACTIVE",
    "createdAt": "2026-03-19T10:00:00"
  },
  {
    "id": 2,
    "firstName": "Emma",
    "lastName": "Brown",
    "email": "emma.brown@example.com",
    "course": "Mathematics",
    "age": 22,
    "status": "ACTIVE",
    "createdAt": "2026-03-19T10:00:00"
  }
]
```

---

## 2. Get student by ID

**Endpoint**

```http
GET /api/students/{id}
```

**Description**  
Returns a single student by ID.

**Example request**

```bash
curl http://localhost:8080/api/students/1
```

**Example response**

```json
{
  "id": 1,
  "firstName": "John",
  "lastName": "Carter",
  "email": "john.carter@example.com",
  "course": "Computer Science",
  "age": 21,
  "status": "ACTIVE",
  "createdAt": "2026-03-19T10:00:00"
}
```

**Example not found response**

```json
{
  "timestamp": "2026-03-19T10:15:00",
  "status": 404,
  "error": "Not Found",
  "message": "Student with id 99 not found"
}
```

---

## 3. Create a new student

**Endpoint**

```http
POST /api/students
```

**Description**  
Creates a new student in memory.

**Example request body**

```json
{
  "firstName": "Daniel",
  "lastName": "White",
  "email": "daniel.white@example.com",
  "course": "Computer Science",
  "age": 23,
  "status": "ACTIVE"
}
```

**Example request**

```bash
curl -X POST http://localhost:8080/api/students \
  -H "Content-Type: application/json" \
  -d '{
    "firstName": "Daniel",
    "lastName": "White",
    "email": "daniel.white@example.com",
    "course": "Computer Science",
    "age": 23,
    "status": "ACTIVE"
  }'
```

**Example response**

```json
{
  "id": 11,
  "firstName": "Daniel",
  "lastName": "White",
  "email": "daniel.white@example.com",
  "course": "Computer Science",
  "age": 23,
  "status": "ACTIVE",
  "createdAt": "2026-03-19T10:20:00"
}
```

---

## 4. Update a student

**Endpoint**

```http
PUT /api/students/{id}
```

**Description**  
Replaces the full student resource.

**Example request body**

```json
{
  "firstName": "Daniel",
  "lastName": "White",
  "email": "daniel.white@university.com",
  "course": "Cyber Security",
  "age": 24,
  "status": "SUSPENDED"
}
```

**Example request**

```bash
curl -X PUT http://localhost:8080/api/students/11 \
  -H "Content-Type: application/json" \
  -d '{
    "firstName": "Daniel",
    "lastName": "White",
    "email": "daniel.white@university.com",
    "course": "Cyber Security",
    "age": 24,
    "status": "SUSPENDED"
  }'
```

**Example response**

```json
{
  "id": 11,
  "firstName": "Daniel",
  "lastName": "White",
  "email": "daniel.white@university.com",
  "course": "Cyber Security",
  "age": 24,
  "status": "SUSPENDED",
  "createdAt": "2026-03-19T10:20:00"
}
```

---

## 5. Update only student status

**Endpoint**

```http
PATCH /api/students/{id}/status
```

**Description**  
Updates only the `status` field of a student.

**Example request body**

```json
{
  "status": "GRADUATED"
}
```

**Example request**

```bash
curl -X PATCH http://localhost:8080/api/students/11/status \
  -H "Content-Type: application/json" \
  -d '{
    "status": "GRADUATED"
  }'
```

**Example response**

```json
{
  "id": 11,
  "firstName": "Daniel",
  "lastName": "White",
  "email": "daniel.white@university.com",
  "course": "Cyber Security",
  "age": 24,
  "status": "GRADUATED",
  "createdAt": "2026-03-19T10:20:00"
}
```

---

## 6. Delete a student

**Endpoint**

```http
DELETE /api/students/{id}
```

**Description**  
Deletes a student by ID.

**Example request**

```bash
curl -X DELETE http://localhost:8080/api/students/11
```

**Example response**

```json
{
  "message": "Student with id 11 deleted successfully"
}
```

---

## 7. Search students

**Endpoint**

```http
GET /api/students/search
```

**Description**  
Searches students by optional query parameters:

- `course`
- `status`

You can use one of them or both together.

### 7.1 Search by course

**Example request**

```bash
curl "http://localhost:8080/api/students/search?course=Computer%20Science"
```

**Example response**

```json
[
  {
    "id": 1,
    "firstName": "John",
    "lastName": "Carter",
    "email": "john.carter@example.com",
    "course": "Computer Science",
    "age": 21,
    "status": "ACTIVE",
    "createdAt": "2026-03-19T10:00:00"
  },
  {
    "id": 5,
    "firstName": "Noah",
    "lastName": "Davis",
    "email": "noah.davis@example.com",
    "course": "Computer Science",
    "age": 20,
    "status": "ACTIVE",
    "createdAt": "2026-03-19T10:00:00"
  }
]
```

### 7.2 Search by status

**Example request**

```bash
curl "http://localhost:8080/api/students/search?status=ACTIVE"
```

**Example response**

```json
[
  {
    "id": 1,
    "firstName": "John",
    "lastName": "Carter",
    "email": "john.carter@example.com",
    "course": "Computer Science",
    "age": 21,
    "status": "ACTIVE",
    "createdAt": "2026-03-19T10:00:00"
  }
]
```

### 7.3 Search by course and status

**Example request**

```bash
curl "http://localhost:8080/api/students/search?course=Engineering&status=ACTIVE"
```

**Example response**

```json
[
  {
    "id": 10,
    "firstName": "Mia",
    "lastName": "Anderson",
    "email": "mia.anderson@example.com",
    "course": "Engineering",
    "age": 24,
    "status": "ACTIVE",
    "createdAt": "2026-03-19T10:00:00"
  }
]
```

---

## 8. Count students

**Endpoint**

```http
GET /api/students/count
```

**Description**  
Returns the total number of students currently in memory.

**Example request**

```bash
curl http://localhost:8080/api/students/count
```

**Example response**

```json
{
  "count": 10
}
```

---

## 9. Seed info

**Endpoint**

```http
GET /api/students/seed-info
```

**Description**  
Returns information about the preloaded sample dataset.

**Example request**

```bash
curl http://localhost:8080/api/students/seed-info
```

**Example response**

```json
{
  "message": "Sample student data loaded in memory",
  "count": 10
}
```

---

## 10. Ping endpoint

**Endpoint**

```http
GET /api/students/ping
```

**Description**  
Simple test endpoint to verify that the API is running.

**Example request**

```bash
curl http://localhost:8080/api/students/ping
```

**Example response**

```json
{
  "message": "Student Manager API is running"
}
```

---

## 11. Version endpoint

**Endpoint**

```http
GET /api/students/version
```

**Description**  
Returns application metadata useful for Kubernetes rollout checks.

**Example request**

```bash
curl http://localhost:8080/api/students/version
```

**Example response**

```json
{
  "app": "student-manager-api",
  "version": "1.0.0",
  "profile": "k8s-lab"
}
```

---

# Actuator Endpoints

## 12. Health endpoint

**Endpoint**

```http
GET /actuator/health
```

**Description**  
Used by Kubernetes for readiness and liveness probes.

**Example request**

```bash
curl http://localhost:8080/actuator/health
```

**Example response**

```json
{
  "status": "UP"
}
```

---

## 13. Info endpoint

**Endpoint**

```http
GET /actuator/info
```

**Description**  
Returns application metadata.

**Example request**

```bash
curl http://localhost:8080/actuator/info
```

**Example response**

```json
{
  "app": {
    "name": "student-manager-api",
    "version": "1.0.0",
    "profile": "k8s-lab"
  }
}
```

---

# Validation rules

The API validates incoming request bodies.

## Student creation and update rules

- `firstName`: required
- `lastName`: required
- `email`: required and must be valid
- `course`: required
- `age`: required, min 16, max 100
- `status`: required

**Example validation error**

```json
{
  "timestamp": "2026-03-19T10:30:00",
  "status": 400,
  "error": "Bad Request",
  "validationErrors": {
    "firstName": "First name is required",
    "email": "Email must be valid"
  }
}
```

---

# Sample statuses

Allowed values for `status`:

- `ACTIVE`
- `SUSPENDED`
- `GRADUATED`

---

# Sample data loaded at startup

The application automatically loads sample students when it starts.

Example records include:

- John Carter — Computer Science — ACTIVE
- Emma Brown — Mathematics — ACTIVE
- Liam Smith — Physics — SUSPENDED
- Olivia Johnson — Engineering — GRADUATED
- Noah Davis — Computer Science — ACTIVE
- Sophia Miller — Biology — ACTIVE
- James Wilson — Chemistry — SUSPENDED
- Ava Moore — Literature — GRADUATED
- William Taylor — Computer Science — ACTIVE
- Mia Anderson — Engineering — ACTIVE

Since everything is stored in memory, restarting the application restores the original dataset.

---

# Running locally

## Start the application

```bash
mvn spring-boot:run
```

or build and run the jar:

```bash
mvn clean package
java -jar target/student-manager-api.jar
```

---

# Docker

## Build image

```bash
docker build -t YOUR_DOCKERHUB_USERNAME/student-manager-api:1.0.0 .
```

## Run container locally

```bash
docker run -p 8080:8080 YOUR_DOCKERHUB_USERNAME/student-manager-api:1.0.0
```

## Test container

```bash
curl http://localhost:8080/api/students
curl http://localhost:8080/actuator/health
```

## Push image

```bash
docker push YOUR_DOCKERHUB_USERNAME/student-manager-api:1.0.0
```

Optional `latest` tag:

```bash
docker tag YOUR_DOCKERHUB_USERNAME/student-manager-api:1.0.0 YOUR_DOCKERHUB_USERNAME/student-manager-api:latest
docker push YOUR_DOCKERHUB_USERNAME/student-manager-api:latest
```

---

# Kubernetes

## Apply manifests

```bash
kubectl apply -f k8s/deployment.yaml
kubectl apply -f k8s/service.yaml
```

## Check resources

```bash
kubectl get pods
kubectl get deployments
kubectl get svc
```

## Describe deployment

```bash
kubectl describe deployment student-manager-api
```

## View logs

```bash
kubectl logs -l app=student-manager-api
```

---

# Kubernetes probes

The deployment uses:

- **readinessProbe** on `/actuator/health`
- **livenessProbe** on `/actuator/health`

This makes the application suitable for basic Kubernetes health checking demonstrations.

---

# Useful lab activities

Students can use this project to practice:

- pulling a Docker image
- running a container
- testing REST endpoints
- pushing a new image version
- updating deployment tags
- scaling replicas
- exposing the service
- observing liveness/readiness probes
- checking rollout results through `/api/students/version`

---

# Notes

- No database is used
- Data is reset on every restart
- The project is intentionally simple for infrastructure-focused labs
- It is ideal for Docker and Kubernetes exercises

---

# Authoring idea for classroom usage

A good exercise flow could be:

1. pull the image
2. run it with Docker
3. test the endpoints
4. push a new version with a different tag
5. update the Kubernetes deployment
6. verify the new version from `/api/students/version`

---

# Coffeeware License

## Coffeeware

You can use, modify, share, and distribute this project freely.  
If this project helps you, and we ever meet in person, you can buy me a coffee.

Author: Giovanni Pace  
YouTube: https://www.youtube.com/@archetydev

---

## Disclaimer

This project is intended for educational purposes.
