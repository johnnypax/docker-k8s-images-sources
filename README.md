# Docker & Kubernetes Lab Workspace - by Giovanni Pace

This repository contains a multi-project workspace designed for learning:

- Docker
- Containerization
- Image registries
- Kubernetes deployments

The goal is to provide ready-to-use backend services that students can:

- build as Docker images
- push to a registry
- deploy on Kubernetes
- test through REST APIs

---

## Learning Objectives

This workspace is built for hands-on labs focused on:

- building Docker images
- understanding build context and `.dockerignore`
- tagging and pushing images
- deploying applications in Kubernetes
- working with Pods, Deployments, and Services
- testing APIs in a distributed environment

---

## Workspace Structure

workspace/
├── .gitignore
├── README.md
├── img01_sms/
│   ├── Dockerfile
│   ├── .dockerignore
│   ├── pom.xml
│   ├── src/
│   ├── k8s/
│   │   ├── deployment.yaml
│   │   └── service.yaml
│   └── target/ (ignored)
├── ...

---

## Available Projects

### img01_sms - Student Manager API

A Spring Boot REST API with:

- in-memory data
- full CRUD
- search endpoints
- health checks (Actuator)

Path:
img01_sms/

---

## Docker Workflow

### Build the image

cd img01_sms
docker build -t  archety/student-api-in-memory:1.0.0 .

### Run locally

docker run -p 8080:8080  archety/student-api-in-memory:1.0.0

Test:
curl http://localhost:8080/api/students

---

## Health Checks

GET /actuator/health

---

## Example API Test

curl http://localhost:8080/api/students

---

## Versioning Strategy

img01_sms:1.0.0
img01_sms:1.0.1
img01_sms:2.0.0

Avoid relying only on latest.

---

## Suggested Lab Flow

1. Clone the repository
2. Build the Docker image
3. Run the container locally
4. Test the API endpoints
5. Push the image
6. Deploy to Kubernetes
7. Scale the deployment
8. Update the image version
9. Verify rollout

---

## Best Practices Included

- multi-stage Docker build
- `.dockerignore` optimization
- in-memory architecture
- Kubernetes-ready manifests

---

## YouTube Channel

https://www.youtube.com/@archetydev

---

## Coffeeware License

You can use, modify, and share this project freely.

If this project helps you and we meet in person, you can buy me a coffee.

Author: Giovanni Pace
Channel: https://www.youtube.com/@archetydev

---

## Notes

- No database is used
- Data resets on restart
- Designed for learning purposes
