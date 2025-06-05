# 🚚 Transport Vehicle Booking Platform

> A comprehensive logistics and shipment transport booking platform - Backend APIs (User Module)

[![Development Status](https://img.shields.io/badge/Status-In%20Development-yellow)](https://github.com/yourusername/transport-booking-platform)
[![API Version](https://img.shields.io/badge/API%20Version-v1.0-blue)](https://github.com/yourusername/transport-booking-platform)
[![License](https://img.shields.io/badge/License-MIT-green)](LICENSE)

## 📋 Table of Contents

- [Overview](#overview)
- [Problem Statement](#problem-statement)
- [Features](#features)
- [API Documentation](#api-documentation)
- [Tech Stack](#tech-stack)
- [Getting Started](#getting-started)
- [Project Structure](#project-structure)
- [Development Status](#development-status)
- [Contributing](#contributing)
- [License](#license)

## 🎯 Overview

Transport Vehicle Booking Platform is a modern logistics solution designed to connect users who need shipment services with transporters who provide delivery services. Similar to ride-hailing apps like Ola or Uber, but specifically tailored for **logistics and freight transportation**.

This repository contains the backend API services, currently focusing on the **User Module** functionality.

## 🧾 Problem Statement

We are building comprehensive backend services for a transport vehicle booking platform that addresses the growing need for efficient logistics and shipment transport solutions. The platform serves two primary user types:

- **👤 Users**: Individuals or businesses who need to book shipment services
- **🚛 Transporters**: Service providers who accept and fulfill shipment orders *(Coming Soon)*

**Current Scope**: This phase focuses exclusively on the User-side functionality, providing a solid foundation for shipment booking operations.

## ✨ Features

### 🔐 User Authentication
- **User Registration**: Secure user account creation
- **User Login**: Authentication with credential validation
- **Session Management**: Secure user session handling

### 📦 Shipment Management
- **Order Creation**: Users can create detailed shipment orders
- **Location Services**: Comprehensive pickup and drop location specification
- **Order Details**: Item descriptions, weight specifications, and additional notes
- **Order Tracking**: View and manage personal shipment orders
- **Status Management**: Real-time order status updates

### 🎯 Key Capabilities
- Full address specification for pickup and delivery locations
- Detailed item information including weight and descriptions
- Order status tracking with `ACTIVE` status for transporter visibility
- Personal order history and management

## 🔌 API Documentation

### Base URL
```
https://api.transportbooking.com
```

### 🔐 Authentication Endpoints

#### Register User
```http
POST /api/users/register
```

**Request Body:**
```json
{
  "name": "John Doe",
  "email": "john.doe@example.com",
  "password": "securePassword123",
  "phone": "+1234567890"
}
```

**Response:**
```json
{
  "success": true,
  "message": "User registered successfully",
  "data": {
    "userId": "user_123456",
    "name": "John Doe",
    "email": "john.doe@example.com",
    "phone": "+1234567890",
    "createdAt": "2024-01-15T10:30:00Z"
  }
}
```

#### User Login
```http
POST /api/users/login
```

**Request Body:**
```json
{
  "email": "john.doe@example.com",
  "password": "securePassword123"
}
```

**Response:**
```json
{
  "success": true,
  "message": "Login successful",
  "data": {
    "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
    "user": {
      "userId": "user_123456",
      "name": "John Doe",
      "email": "john.doe@example.com"
    }
  }
}
```

### 📦 Shipment Endpoints

#### Create Shipment Order
```http
POST /api/shipments/create
```

**Headers:**
```
Authorization: Bearer <token>
```

**Request Body:**
```json
{
  "pickupLocation": {
    "address": "123 Main Street, City, State, ZIP",
    "latitude": 40.7128,
    "longitude": -74.0060,
    "contactPerson": "John Doe",
    "contactPhone": "+1234567890"
  },
  "dropLocation": {
    "address": "456 Oak Avenue, City, State, ZIP",
    "latitude": 40.7589,
    "longitude": -73.9851,
    "contactPerson": "Jane Smith",
    "contactPhone": "+0987654321"
  },
  "orderDetails": {
    "itemName": "Electronics Package",
    "weight": 15.5,
    "dimensions": {
      "length": 30,
      "width": 20,
      "height": 10
    },
    "notes": "Handle with care - fragile items"
  }
}
```

**Response:**
```json
{
  "success": true,
  "message": "Shipment order created successfully",
  "data": {
    "orderId": "order_789012",
    "status": "ACTIVE",
    "pickupLocation": { /* pickup details */ },
    "dropLocation": { /* drop details */ },
    "orderDetails": { /* order details */ },
    "createdAt": "2024-01-15T14:45:00Z",
    "estimatedDelivery": "2024-01-16T18:00:00Z"
  }
}
```

#### Get User Orders
```http
GET /api/users/orders
```

**Headers:**
```
Authorization: Bearer <token>
```

**Response:**
```json
{
  "success": true,
  "data": {
    "orders": [
      {
        "orderId": "order_789012",
        "status": "ACTIVE",
        "pickupLocation": { /* pickup details */ },
        "dropLocation": { /* drop details */ },
        "createdAt": "2024-01-15T14:45:00Z"
      }
    ],
    "totalOrders": 1
  }
}
```

## 🛠️ Tech Stack

- **Framework**: Spring Boot
- **Language**: Java
- **Database**: MySQL / PostgreSQL
- **Authentication**: JWT (JSON Web Tokens)
- **Security**: Spring Security
- **Validation**: Bean Validation (Hibernate Validator)
- **Documentation**: Swagger/OpenAPI (SpringDoc)
- **Testing**: JUnit 5, Mockito
- **Build Tool**: Maven / Gradle

## 🚀 Getting Started

### Prerequisites

- Java 8 or higher (Java 11+ recommended)
- Maven or Gradle
- MySQL/PostgreSQL database
- Git

### Installation

1. **Clone the repository**
   ```bash
   git clone https://github.com/yourusername/transport-booking-platform.git
   cd transport-booking-platform
   ```

2. **Install dependencies**
   ```bash
   # If using Maven
   mvn clean install
   
   # If using Gradle
   ./gradlew build
   ```

3. **Environment Setup**
   ```bash
   cp application.properties.example src/main/resources/application.properties
   ```

   Configure your application properties:
   ```properties
   server.port=8080
   spring.datasource.url=jdbc:mysql://localhost:3306/transport_booking
   spring.datasource.username=your_db_username
   spring.datasource.password=your_db_password
   spring.jpa.hibernate.ddl-auto=update
   jwt.secret=your_jwt_secret
   ```

4. **Database Setup**
   ```bash
   # Create database
   mysql -u root -p -e "CREATE DATABASE transport_booking;"
   
   # Run the application (it will auto-create tables)
   mvn spring-boot:run
   ```

5. **Start the application**
   ```bash
   # Using Maven
   mvn spring-boot:run
   
   # Using Gradle
   ./gradlew bootRun
   
   # Or run the JAR file
   java -jar target/transport-booking-platform-0.0.1-SNAPSHOT.jar
   ```

The API will be available at `http://localhost:8080`

### Testing

```bash
# Run all tests (Maven)
mvn test

# Run tests with coverage (Maven)
mvn test jacoco:report

# Run all tests (Gradle)
./gradlew test

# Run tests with coverage (Gradle)
./gradlew test jacocoTestReport
```

## 📁 Project Structure

```
transport-booking-platform/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/yourcompany/transport/
│   │   │       ├── controller/       # REST Controllers
│   │   │       ├── service/          # Business Logic
│   │   │       ├── repository/       # Data Access Layer
│   │   │       ├── model/            # Entity Classes
│   │   │       ├── dto/              # Data Transfer Objects
│   │   │       ├── config/           # Configuration Classes
│   │   │       └── security/         # Security Configuration
│   │   └── resources/
│   │       ├── application.properties
│   │       └── static/
│   └── test/
│       └── java/                     # Test Classes
├── target/                           # Build Output (Maven)
├── pom.xml                           # Maven Dependencies
└── README.md
```

## 🔄 Development Status

### ✅ Completed Features
- [x] User registration and authentication
- [x] JWT-based session management
- [x] Shipment order creation
- [x] Order management and viewing
- [x] Location services integration
- [x] Basic API documentation

### 🔄 In Progress
- [ ] Enhanced order status tracking
- [ ] User profile management
- [ ] Order modification capabilities

### 📋 Planned Features
- [ ] Transporter module integration
- [ ] Real-time order tracking
- [ ] Payment gateway integration
- [ ] Push notifications
- [ ] Admin dashboard
- [ ] Analytics and reporting

## 🤝 Contributing

We welcome contributions! Please see our [Contributing Guidelines](CONTRIBUTING.md) for details.

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 🔗 Links

- [API Documentation](https://api.transportbooking.com/docs)
- [Project Roadmap](https://github.com/yourusername/transport-booking-platform/projects)
- [Issues](https://github.com/yourusername/transport-booking-platform/issues)
- [Discussions](https://github.com/yourusername/transport-booking-platform/discussions)

## 📞 Support

For support and questions, please:
- Create an [issue](https://github.com/yourusername/transport-booking-platform/issues)
- Join our [Discord community](https://discord.gg/transport-booking)
- Email us at support@transportbooking.com

---

**Made with ❤️ by [Your Name/Team]**