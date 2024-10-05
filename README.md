# Shoppers Application - Microservices Architecture

The **Shoppers Application** is a cloud-native, microservices-based e-commerce system built using **Spring Boot** and **Spring Cloud**. The architecture is designed for flexibility, scalability, and ease of maintenance. It leverages multiple independent services, each responsible for a core business function, and communicates through **OpenFeign** for seamless inter-service interaction.

## Overview

This system comprises several microservices responsible for key aspects of a shopping system, such as managing orders, inventories, and API routing. The architecture follows best practices for modern microservices, including service discovery via **Eureka**, centralized routing with **Spring Cloud Gateway**, and inter-service communication through **OpenFeign**.

## Key Features

- **API Gateway**: Centralized routing, request filtering, and load balancing.
- **Service Discovery**: Dynamic service registration and discovery using **Eureka**.
- **Inter-Service Communication**: Leveraging **OpenFeign** for declarative REST client communication between microservices.
- **Modular Services**: Each service is independently deployable and scalable.
  
## Architecture

```
.
├── api-gateway
├── order-management-service
├── inventory-management-service
├── eureka-server
└── .idea
```

### 1. API Gateway

The **API Gateway** serves as the central entry point for client requests and is responsible for routing these requests to the appropriate backend microservices. It also applies global filters such as logging and authentication.

- **Request Routing**: Routes client requests to backend services based on predefined routes configured in `application.yml`.
- **Filters**: Custom filters for authentication and request/response logging.

---

### 2. Order Management Service

The **Order Management Service** handles all operations related to customer orders. It is responsible for creating new orders, updating existing ones, and retrieving order information. This service interacts with the **Inventory Management Service** via **OpenFeign** to validate stock levels before confirming an order.

- **Core Functionality**: Manages the lifecycle of customer orders, including order creation, status updates, and retrieval.
- **Stock Validation**: Communicates with the Inventory Management Service to check product availability.

---

### 3. Inventory Management Service

The **Inventory Management Service** is responsible for managing product inventory levels. It handles the stock availability, updates stock levels when an order is confirmed, and provides real-time inventory data to the Order Management Service.

- **Core Functionality**: Tracks and manages inventory levels for products.
- **Order Integration**: Works with the Order Management Service to ensure accurate stock validation during order processing.

---

### 4. Eureka Server

The **Eureka Server** serves as the service registry for the microservices architecture. Each service registers itself with Eureka upon startup, enabling dynamic discovery and load balancing across service instances. This removes the need for hardcoded service URLs and supports service resilience and failover.

- **Service Discovery**: Manages registration and discovery of services for dynamic scaling and load balancing.

---

## Inter-Service Communication

Inter-service communication is handled through **OpenFeign**, a declarative REST client provided by Spring Cloud. This allows services to call each other’s APIs without directly handling HTTP requests, making the code cleaner and easier to maintain.

- **Order Management Service ↔ Inventory Management Service**: The Order Management Service uses OpenFeign to call the Inventory Management Service for stock validation.

## Running the Application

### Prerequisites

- **Java 11** or later.
- **Maven** for building the project.

### Running Locally

1. **Clone the repository**:
   ```bash
   git clone <repository-url>
   cd shoppers-application
   ```

2. **Start Eureka Server**:
   Navigate to the `eureka-server` directory and run:
   ```bash
   ./mvnw spring-boot:run
   ```

3. **Start API Gateway**:
   Navigate to the `api-gateway` directory and run:
   ```bash
   ./mvnw spring-boot:run
   ```

4. **Start Order Management Service**:
   Navigate to the `order-management-service` directory and run:
   ```bash
   ./mvnw spring-boot:run
   ```

5. **Start Inventory Management Service**:
   Navigate to the `inventory-management-service` directory and run:
   ```bash
   ./mvnw spring-boot:run
   ```

Ensure the **Eureka Server** is started first, as all services will attempt to register with Eureka during their startup.

---

## Future Enhancements

1. **Centralized Logging Service**: Introduce a centralized logging solution using **ELK Stack (Elasticsearch, Logstash, Kibana)** or **Spring Cloud Sleuth** to aggregate logs from all microservices and provide traceability across requests.
2. **Security**: Implement OAuth2 and JWT for secure authentication and authorization at the API Gateway.
3. **Dockerization**: Add Docker support for containerizing all microservices, simplifying deployment and scaling.
4. **Kubernetes**: Deploy the application on a Kubernetes cluster for orchestration and scaling.
5. **Circuit Breakers**: Implement circuit breakers using **Resilience4J** to handle service failures gracefully.

---

This README serves as a high-level overview of the **Shoppers Application** microservices architecture. By leveraging **Spring Cloud Gateway**, **Eureka**, and **OpenFeign**, the system ensures scalable, flexible, and maintainable services.
