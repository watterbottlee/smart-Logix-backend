# Transport Booking Platform API

A comprehensive logistics and shipment transport booking platform backend built with Spring Boot. Connects users who need shipment services with transporters who provide delivery services.

## Overview

RESTful API service that facilitates logistics operations between users and transporters. Provides order management, real-time tracking, location services, and authentication.

**Base URL**: `https://api.transportbooking.com/api/v1`

## Quick Start

### Authentication
All protected endpoints require JWT token:
```
Authorization: Bearer <jwt_token>
```

### Register User
```http
POST /auth/users/register
Content-Type: application/json

{
  "name": "John Doe",
  "email": "john.doe@example.com",
  "password": "securePassword123",
  "phone": "+1234567890",
  "address": {
    "street": "123 Main Street",
    "city": "New York",
    "state": "NY",
    "zipCode": "10001",
    "country": "USA"
  }
}
```

### Login
```http
POST /auth/login
Content-Type: application/json

{
  "email": "john.doe@example.com",
  "password": "securePassword123"
}
```

## User APIs

### Create Shipment Order
```http
POST /users/orders
Authorization: Bearer <token>
Content-Type: application/json

{
  "pickupLocation": {
    "address": "123 Main Street, New York, NY 10001",
    "latitude": 40.7128,
    "longitude": -74.0060,
    "contactPerson": "John Doe",
    "contactPhone": "+1234567890",
    "instructions": "Ring doorbell twice"
  },
  "dropLocation": {
    "address": "456 Oak Avenue, Boston, MA 02101",
    "latitude": 42.3601,
    "longitude": -71.0589,
    "contactPerson": "Jane Smith",
    "contactPhone": "+0987654321",
    "instructions": "Leave at reception"
  },
  "orderDetails": {
    "itemName": "Electronics Package",
    "description": "Laptop and accessories",
    "weight": 5.5,
    "dimensions": {
      "length": 40,
      "width": 30,
      "height": 15
    },
    "value": 1200.00,
    "category": "ELECTRONICS",
    "isFragile": true,
    "notes": "Handle with care"
  },
  "deliveryType": "STANDARD",
  "scheduledPickupTime": "2024-01-16T09:00:00Z"
}
```

### Get User Orders
```http
GET /users/orders?status=ACTIVE&page=0&size=10&sort=createdAt,desc
Authorization: Bearer <token>
```

## Transporter APIs

### Register Transporter
```http
POST /auth/transporters/register
Content-Type: application/json

{
  "name": "Mike Transport Services",
  "email": "mike@transport.com",
  "password": "securePassword123",
  "phone": "+1234567890",
  "licenseNumber": "TRP123456789",
  "vehicleDetails": {
    "vehicleType": "TRUCK",
    "vehicleNumber": "ABC-1234",
    "capacity": 1000,
    "dimensions": {
      "length": 6.0,
      "width": 2.5,
      "height": 2.8
    }
  },
  "address": {
    "street": "456 Transport Ave",
    "city": "Chicago",
    "state": "IL",
    "zipCode": "60601",
    "country": "USA"
  }
}
```

### Get Nearby Orders
```http
GET /transporters/orders/nearby?latitude=41.8781&longitude=-87.6298&radius=50&page=0&size=10
Authorization: Bearer <token>
```

### Accept Order
```http
POST /transporters/orders/{orderId}/accept
Authorization: Bearer <token>
Content-Type: application/json

{
  "proposedCost": 115.00,
  "estimatedPickupTime": "2024-01-16T09:30:00Z",
  "estimatedDeliveryTime": "2024-01-16T16:00:00Z",
  "notes": "Can deliver earlier if needed"
}
```

### Update Order Status
```http
PUT /transporters/orders/{orderId}/status
Authorization: Bearer <token>
Content-Type: application/json

{
  "status": "PICKED_UP",
  "notes": "Package collected successfully",
  "location": {
    "latitude": 41.8781,
    "longitude": -87.6298
  },
  "timestamp": "2024-01-16T09:45:00Z"
}
```

### Transporter Dashboard
```http
GET /transporters/dashboard
Authorization: Bearer <token>
```

Response:
```json
{
  "success": true,
  "data": {
    "summary": {
      "totalEarnings": 2450.75,
      "monthlyEarnings": 850.25,
      "totalDeliveries": 324,
      "monthlyDeliveries": 45,
      "averageRating": 4.7,
      "activeOrders": 3,
      "completionRate": 98.5
    },
    "earnings": {
      "today": 230.50,
      "thisWeek": 1150.75,
      "thisMonth": 850.25
    }
  }
}
```

## Order Management

### Get Order Details
```http
GET /orders/{orderId}
Authorization: Bearer <token>
```

### Rate Order (Users only)
```http
POST /orders/{orderId}/rate
Authorization: Bearer <token>
Content-Type: application/json

{
  "rating": 5,
  "review": "Excellent service! Package delivered on time.",
  "tags": ["PUNCTUAL", "CAREFUL", "PROFESSIONAL"]
}
```

## Utility APIs

### Calculate Shipping Cost
```http
POST /common/calculate-cost
Content-Type: application/json

{
  "pickupLocation": {
    "latitude": 40.7128,
    "longitude": -74.0060
  },
  "dropLocation": {
    "latitude": 42.3601,
    "longitude": -71.0589
  },
  "weight": 15.5,
  "dimensions": {
    "length": 40,
    "width": 30,
    "height": 15
  },
  "deliveryType": "STANDARD",
  "vehicleType": "VAN"
}
```

### Get Vehicle Types
```http
GET /common/vehicle-types
```

Response:
```json
{
  "success": true,
  "data": [
    {
      "type": "BIKE",
      "name": "Motorcycle",
      "maxWeight": 20,
      "maxDimensions": {
        "length": 60,
        "width": 40,
        "height": 40
      }
    },
    {
      "type": "TRUCK",
      "name": "Truck",
      "maxWeight": 2000,
      "maxDimensions": {
        "length": 600,
        "width": 250,
        "height": 280
      }
    }
  ]
}
```

## Order Status Flow

```
PENDING → ACCEPTED → PICKED_UP → IN_TRANSIT → DELIVERED → COMPLETED
          ↓
        REJECTED
          ↓
       CANCELLED
```

### Order Statuses
- `PENDING` - Waiting for transporter acceptance
- `ACCEPTED` - Accepted by transporter
- `REJECTED` - Rejected by transporter
- `CANCELLED` - Cancelled by user
- `PICKED_UP` - Package collected
- `IN_TRANSIT` - Package in transit
- `DELIVERED` - Package delivered
- `COMPLETED` - Order completed and rated

### Vehicle Types
- `BIKE` - Motorcycle/Scooter (max 20kg)
- `CAR` - Personal car (max 100kg)
- `VAN` - Small commercial vehicle (max 500kg)
- `TRUCK` - Large commercial vehicle (max 2000kg)

### Delivery Types
- `STANDARD` - Regular delivery (1-2 days)
- `EXPRESS` - Fast delivery (same day)
- `SCHEDULED` - Delivery at specific time

## Error Handling

### Standard Error Response
```json
{
  "success": false,
  "error": {
    "code": "VALIDATION_ERROR",
    "message": "Invalid input data",
    "details": [
      {
        "field": "email",
        "message": "Email format is invalid"
      }
    ],
    "timestamp": "2024-01-15T14:45:00Z",
    "path": "/api/v1/auth/users/register"
  }
}
```

### Common Error Codes
- `VALIDATION_ERROR` - Invalid input data
- `AUTHENTICATION_ERROR` - Invalid credentials
- `AUTHORIZATION_ERROR` - Insufficient permissions
- `RESOURCE_NOT_FOUND` - Resource not found
- `BUSINESS_LOGIC_ERROR` - Business rule violation
- `INTERNAL_SERVER_ERROR` - Server error

## Rate Limiting

- Authentication endpoints: 5 requests/minute per IP
- Order creation: 10 requests/hour per user
- Location updates: 60 requests/minute per transporter
- General API calls: 1000 requests/hour per user

## Google Maps Integration

The API fully supports Google Maps integration:

### Location Storage
Both pickup and drop locations store:
- `latitude` and `longitude` from Google Maps
- Full address string
- Contact information

### Distance Calculation
The cost calculation API uses coordinates to:
- Calculate exact distances
- Determine shipping costs
- Estimate delivery times

### Real-time Tracking
Transporters can update their location in real-time:
```http
PUT /transporters/location
Authorization: Bearer <token>
Content-Type: application/json

{
  "latitude": 41.8781,
  "longitude": -87.6298,
  "isAvailable": true
}
```

## Tech Stack

- **Framework**: Spring Boot 3.x
- **Database**: PostgreSQL/MySQL
- **Authentication**: JWT with Spring Security
- **Documentation**: OpenAPI 3.0
- **Testing**: JUnit 5, Mockito

## Getting Started

1. Clone the repository
2. Configure database in `application.properties`
3. Run `mvn spring-boot:run`
4. Access API at `http://localhost:8080`
5. View documentation at `http://localhost:8080/swagger-ui.html`

## Environment Setup

```properties
# Database Configuration
spring.datasource.url=jdbc:postgresql://localhost:5432/transport_booking
spring.datasource.username=your_username
spring.datasource.password=your_password

# JWT Configuration
jwt.secret=your-256-bit-secret
jwt.expiration=3600

# Server Configuration
server.port=8080
```

---

**API Version**: 1.0  
**Last Updated**: June 2025