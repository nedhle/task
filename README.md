# Device Service API

This project is a RESTful API for managing devices. The API is built using Spring Boot, with support for JPA, H2 in-memory database, and Swagger for API documentation.

## Table of Contents

- [Prerequisites](#prerequisites)
- [Running the Application](#running-the-application)
- [API Documentation](#api-documentation)
- [Running with Docker](#running-with-docker)
- [Testing](#testing)

## Prerequisites

- Java 17
- Maven
- H2

## Running the Application

1. Clone the repository:

    ```bash
    git clone https://github.com/nedhle/task.git
    cd task
    ```

2. Build the project with Maven:

    ```bash
    mvn clean install
    ```

3. Run the application:

    ```bash
    mvn spring-boot:run
    ```

   The application will start and be accessible at `http://localhost:8080`.

## API Documentation

This project uses Swagger and OpenAPI for API documentation.

- **Swagger UI**: The Swagger UI is available at the following URL after starting the application:

- **OpenAPI Spec**:
- http://localhost:8080/swagger-ui.html
- JSON: `http://localhost:8080/v3/api-docs`
- YAML: `http://localhost:8080/v3/api-docs.yaml`

The Swagger UI allows you to explore the API endpoints, view request/response models, and test the API directly from your browser.

## Running with Docker

This project includes a `Dockerfile` and `docker-compose.yml` to make it easier to run the application in a Docker container.

### Building and Running with Docker

1. Build the Docker image:

  ```bash
  docker build -t device-service .
  ```

2. Run the Docker container:

  ```bash
  docker run -p 8080:8080 device-service
  ```

The application will be accessible at `http://localhost:8080`.

### Using Docker Compose

1. You can also run the application using Docker Compose, which is useful if you have multiple services to run:

  ```bash
  docker-compose up --build
  ```

This command will build the Docker image and start the container. The API will be available at `http://localhost:8080`.

2. To stop the container, run:

  ```bash
  docker-compose down
  ```

## Testing

The project includes unit and integration tests.

- To run the tests, use:

  ```bash
  mvn test
  ```

