```
# GilaCodingChallenge Project

This project contains a backend (Spring Boot) and a frontend (React) Dockerized for easy setup using Docker Compose.

## Prerequisites

Make sure you have Docker and Docker Compose installed on your system:
- [Docker Desktop](https://www.docker.com/products/docker-desktop) (for Windows and macOS)
- [Docker Engine](https://docs.docker.com/engine/install/) and [Docker Compose](https://docs.docker.com/compose/install/) (for Linux)

## Running the Project

1. **Build and Start the Containers:**
   ```bash
   docker-compose up
   ```

2. Access the frontend at `http://localhost:3000` and the backend API at `http://localhost:8080`.

## Additional Notes

- Ensure ports 8080 and 3000 are not occupied by other applications on your system.
- If you need to customize configurations, refer to the `docker-compose.yml` file for adjustments.
```
