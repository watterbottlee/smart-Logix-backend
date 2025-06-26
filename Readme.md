# Mover API Documentation

A comprehensive REST API for a logistics and moving service platform that manages users, transporters, orders, and related operations.

## Base URL
```
http://localhost:5631
```

## Authentication
*Authentication details to be added based on your implementation*

---

## 📋 Table of Contents
- [User Management](#user-management)
- [Transporter Management](#transporter-management)
- [Order Management](#order-management)
- [Data Models](#data-models)
- [Error Responses](#error-responses)

---

## 👤 User Management

### Create User
**POST** `/users/create`

Creates a new user account.

**Request Body:**
```json
{
  "name": "John Doe",
  "email": "john.doe@example.com",
  "password": "securePassword123"
}
```

**Response:** `201 Created`
```json
{
  "userId": 1,
  "name": "John Doe",
  "email": "john.doe@example.com",
  "password": "securePassword123"
}
```

### Update User
**PUT** `/users/update-user/{userId}`

Updates an existing user's information.

**Path Parameters:**
- `userId` (Long) - User ID

**Request Body:**
```json
{
  "name": "John Smith",
  "email": "john.smith@example.com",
  "password": "newSecurePassword123"
}
```

**Response:** `200 OK`
```json
{
  "userId": 1,
  "name": "John Smith",
  "email": "john.smith@example.com",
  "password": "newSecurePassword123"
}
```

### Get All Users
**GET** `/users/getallusers`

Retrieves all users in the system.

**Response:** `200 OK`
```json
[
  {
    "userId": 1,
    "name": "John Doe",
    "email": "john.doe@example.com",
    "password": "securePassword123"
  }
]
```

### Get User by ID
**GET** `/users/getuserbyid/{id}`

Retrieves a specific user by their ID.

**Path Parameters:**
- `id` (Long) - User ID

**Response:** `200 OK`
```json
{
  "userId": 1,
  "name": "John Doe",
  "email": "john.doe@example.com",
  "password": "securePassword123"
}
```

### Get User by Email
**GET** `/users/getuserbyemail/{emailId}`

Retrieves a user by their email address.

**Path Parameters:**
- `emailId` (String) - User email

**Response:** `200 OK`
```json
{
  "userId": 1,
  "name": "John Doe",
  "email": "john.doe@example.com",
  "password": "securePassword123"
}
```

### Delete User
**DELETE** `/users/delete-user/{userId}`

Deletes a user account.

**Path Parameters:**
- `userId` (Long) - User ID

**Response:** `200 OK`
```json
{
  "message": "user deleted successfully",
  "status": true
}
```

---

## 🚛 Transporter Management

### Register Transporter
**POST** `/transporters/register`

Registers a new transporter.

**Request Body:**
```json
  {
  "name":"ronaldo",
  "email":"ronaldo@gamil.com",
  "password":"fijiguqef",
  "phone":"12345678",
  "createdAt":"2025-06-26T11:26:00.000",
  "updatedAt":"2025-06-26T11:26:00.000"
}
```

**Response:** `201 Created`
```json
{
  "transporterId": 5,
  "name": "ronaldo",
  "email": "ronaldo@gamil.com",
  "password": "fijiguqef",
  "phone": "12345678",
  "createdAt": "2025-06-26T22:01:42.463610318",
  "updatedAt": "2025-06-26T22:01:42.463634142"
}
```

### Update Transporter
**PUT** `/transporters/update-transporter/{transporterId}`

Updates transporter information.

**Path Parameters:**
- `transporterId` (Long) - Transporter ID

**Request Body:**
```json
{
  "transporterId": 1,
  "name": "narendra modi",
  "email": "kaniskaranjanbarman@gamil.com",
  "password": "fijiguqef",
  "phone": "12345678",
  "createdAt": "2025-06-26T11:27:20.918046006",
  "updatedAt": "2025-06-26T11:27:20.918102562"
}
```

**Response:** `200 OK`
```json
{
  "transporterId": 1,
  "name": "narendra modi",
  "email": "kaniskaranjanbarman@gamil.com",
  "password": "fijiguqef",
  "phone": "12345678",
  "createdAt": "2025-06-26T11:27:20.918046",
  "updatedAt": "2025-06-26T22:02:33.839100412"
}
```

### Get Transporter by ID
**GET** `/transporters/gettransporterbyid/{id}`

Retrieves transporter information by ID.

**Path Parameters:**
- `id` (Long) - Transporter ID

**Response:** `200 OK`

```json
{
  "transporterId": 1,
  "name": "narendra modi",
  "email": "kaniskaranjanbarman@gamil.com",
  "password": "fijiguqef",
  "phone": "12345678",
  "createdAt": "2025-06-26T11:27:20.918046",
  "updatedAt": "2025-06-26T22:02:33.8391"
}
```

### Get Transporter by Email
**GET** `/transporters/gettransporterbyemail/{emailId}`

Retrieves transporter information by email.

**Path Parameters:**
- `emailId` (String) - Transporter email

**Response:** `200 OK`
**Response:** `200 OK`

```json
{
  "transporterId": 1,
  "name": "narendra modi",
  "email": "kaniskaranjanbarman@gamil.com",
  "password": "fijiguqef",
  "phone": "12345678",
  "createdAt": "2025-06-26T11:27:20.918046",
  "updatedAt": "2025-06-26T22:02:33.8391"
}
```

### Delete Transporter
**DELETE** `/transporters/delete-transporter/{transporterId}`

Deletes a transporter account.

**Path Parameters:**
- `transporterId` (Long) - Transporter ID

**Response:** `200 OK`
```json
{
  "message": "transporter deleted successfully",
  "status": true
}
```

---

## 🏠 Transporter Address Management

### Add Address
**POST** `/transporters/{transporterId}/address/add`

Adds an address for a transporter.

**Path Parameters:**
- `transporterId` (Long) - Transporter ID

**Request Body:**
```json
{
  "transporterId": 1,
  "street": "123 Main Street",
  "city": "New York",
  "state": "NY",
  "zipCode": "10001",
  "country": "USA"
}
```

**Response:** `201 Created`

### Update Address
**PUT** `/transporters/address/update/{addressId}`

Updates an existing address.

**Path Parameters:**
- `addressId` (Long) - Address ID

**Request Body:**
```json
{
  "transporterId": 1,
  "street": "456 Updated Street",
  "city": "New York",
  "state": "NY",
  "zipCode": "10002",
  "country": "USA"
}
```

**Response:** `200 OK`

### Get Address by ID
**GET** `/transporters/address/getaddressbyid/{addressId}`

Retrieves address information by ID.

**Path Parameters:**
- `addressId` (Long) - Address ID

**Response:** `200 OK`

### Get Address by Transporter Email
**GET** `/transporters/address/getaddressbyemail/{emailId}`

Retrieves address information by transporter email.

**Path Parameters:**
- `emailId` (String) - Transporter email

**Response:** `200 OK`

---

## 🚗 Vehicle Management

### Add Vehicle
**POST** `/transporters/{transporterId}/vehicle/add`

Adds a vehicle for a transporter.

**Path Parameters:**
- `transporterId` (Long) - Transporter ID

**Request Body:**
```json
{
  "transporterId": 1,
  "vehicleType": "truck",
  "vehicleNumber": "ABC123",
  "vehicleMake": "Ford",
  "vehicleModel": "Transit",
  "owner": "self"
}
```

**Response:** `201 Created`

### Update Vehicle
**PUT** `/transporters/{transporterId}/vehicle/update`

Updates vehicle information.

**Path Parameters:**
- `transporterId` (Long) - Transporter ID

**Request Body:**
```json
{
  "transporterId": 1,
  "vehicleType": "pickup",
  "vehicleNumber": "XYZ789",
  "vehicleMake": "Toyota",
  "vehicleModel": "Hilux",
  "owner": "rental"
}
```

**Response:** `200 OK`

### Get Vehicle by ID
**GET** `/transporters/vehicle/getvehiclebyid/{vehicleId}`

Retrieves vehicle information by ID.

**Path Parameters:**
- `vehicleId` (Long) - Vehicle ID

**Response:** `200 OK`

### Get Vehicle by Transporter
**GET** `/transporters/vehicle/getvehiclebytransporter/{transporterId}`

Retrieves vehicle information by transporter ID.

**Path Parameters:**
- `transporterId` (Long) - Transporter ID

**Response:** `200 OK`

---

## 📦 Order Management

### Create Order
**POST** `/order/create`

Creates a new order.

**Request Body:**
```json
{
  "userID": 1,
  "pickupLocation": {
    "address": "123 Pickup Street",
    "pincode": 12345,
    "latitude": 40.7128,
    "longitude": -74.0060,
    "contactPerson": "John Doe",
    "contactPhone": 1234567890,
    "instructions": "Ring the bell"
  },
  "dropLocation": {
    "address": "456 Drop Avenue",
    "pincode": 54321,
    "latitude": 40.7589,
    "longitude": -73.9851,
    "contactPerson": "Jane Smith",
    "contactPhone": 0987654321,
    "instructions": "Leave at reception"
  },
  "orderDetails": {
    "itemName": "Furniture",
    "description": "Living room sofa",
    "category": "Furniture",
    "isFragile": false,
    "notes": "Handle with care",
    "dimensions": {
      "length": 200.0,
      "width": 90.0,
      "height": 80.0
    }
  },
  "deliveryType": "standard",
  "scheduledPickupTime": "2024-01-20T14:00:00"
}
```

**Response:** `201 Created`
```json
{
  "id": 1,
  "userID": 1,
  "transporterId": null,
  "pickupLocation": { /* pickup location object */ },
  "dropLocation": { /* drop location object */ },
  "orderDetails": { /* order details object */ },
  "deliveryType": "standard",
  "scheduledPickupTime": "2024-01-20T14:00:00",
  "createdAt": "2024-01-15T10:30:00",
  "updatedAt": "2024-01-15T10:30:00",
  "price": 150.00,
  "status": "pending"
}
```

### Update Order
**PUT** `/order/update-order/{orderId}`

Updates an existing order.

**Path Parameters:**
- `orderId` (Long) - Order ID

**Request Body:** Same as create order

**Response:** `200 OK`

### Get All Orders by User
**GET** `/order/get-all-orders/{userId}`

Retrieves all orders for a specific user.

**Path Parameters:**
- `userId` (Long) - User ID

**Response:** `200 OK`
```json
[
  {
    "id": 1,
    "userID": 1,
    /* ... order details ... */
  }
]
```

### Get Order by ID
**GET** `/order/getorderbyid/{orderId}`

Retrieves a specific order by ID.

**Path Parameters:**
- `orderId` (Long) - Order ID

**Response:** `200 OK`

### Get Orders by City and Status
**GET** `/order/get-orders-by-city/{city}/{status}`

Retrieves orders filtered by city and status.

**Path Parameters:**
- `city` (String) - City name
- `status` (String) - Order status

**Response:** `200 OK`
```json
[
  {
    "id": 1,
    "userID": 1,
    /* ... order details ... */
  }
]
```

### Delete Order
**DELETE** `/order/delete-order/{orderId}`

Deletes an order.

**Path Parameters:**
- `orderId` (Long) - Order ID

**Response:** `200 OK`
```json
{
  "message": "order deleted successfully",
  "status": true
}
```

---

## 📊 Data Models

### UserDto
```json
{
  "userId": "Long",
  "name": "String (required)",
  "email": "String (required, valid email format)",
  "password": "String (required, 8-24 characters)"
}
```

### TransporterDto
```json
{
  "transporterId": "Long (nullable)",
  "name": "String (required)",
  "email": "String (required)",
  "password": "String (required)",
  "phone": "String (required)",
  "createdAt": "LocalDateTime",
  "updatedAt": "LocalDateTime"
}
```

### TransporterAddressDto
```json
{
  "addressId": "Long",
  "transporterId": "Long (required)",
  "street": "String (required)",
  "city": "String (required)",
  "state": "String (required)",
  "zipCode": "String (required)",
  "country": "String (required)"
}
```

### VehicleDetailsDto
```json
{
  "vehicleId": "Long",
  "transporterId": "Long (required)",
  "vehicleType": "String (required, e.g., 'truck', 'pickup')",
  "vehicleNumber": "String (required)",
  "vehicleMake": "String (required)",
  "vehicleModel": "String (required)",
  "owner": "String (required, 'self' or 'rental')"
}
```

### OrderDto
```json
{
  "id": "Long",
  "userID": "Long (required)",
  "transporterId": "Long (nullable)",
  "pickupLocation": "PickupLocationDto (required)",
  "dropLocation": "DropLocationDto (required)",
  "orderDetails": "OrderDetailsDto (required)",
  "deliveryType": "String (required)",
  "scheduledPickupTime": "LocalDateTime (required)",
  "createdAt": "LocalDateTime",
  "updatedAt": "LocalDateTime",
  "price": "BigDecimal",
  "status": "String"
}
```

### PickupLocationDto / DropLocationDto
```json
{
  "address": "String (required)",
  "pincode": "Long (required)",
  "latitude": "BigDecimal (required)",
  "longitude": "BigDecimal (required)",
  "contactPerson": "String (required)",
  "contactPhone": "Long (required)",
  "instructions": "String (optional)"
}
```

### OrderDetailsDto
```json
{
  "itemName": "String (required)",
  "description": "String (optional)",
  "category": "String (required)",
  "isFragile": "Boolean (required)",
  "notes": "String (optional)",
  "dimensions": "DimensionsDto (optional)"
}
```

### DimensionsDto
```json
{
  "length": "Double (positive)",
  "width": "Double (positive)",
  "height": "Double (positive)"
}
```

### OrderRequest
```json
{
  "id": "Long",
  "userID": "Long (required)",
  "pickupLocation": "PickupLocationDto (required)",
  "dropLocation": "DropLocationDto (required)",
  "orderDetails": "OrderDetailsDto (required)",
  "deliveryType": "String (required)",
  "scheduledPickupTime": "LocalDateTime (required)",
  "createdAt": "LocalDateTime",
  "updatedAt": "LocalDateTime"
}
```

---

## ❌ Error Responses

### Validation Errors
**400 Bad Request**
```json
{
  "message": "Validation failed",
  "errors": [
    {
      "field": "email",
      "message": "must be a well-formed email address"
    }
  ]
}
```

### Not Found
**404 Not Found**
```json
{
  "message": "Resource not found",
  "timestamp": "2024-01-15T10:30:00"
}
```

### Internal Server Error
**500 Internal Server Error**
```json
{
  "message": "Internal server error occurred",
  "timestamp": "2024-01-15T10:30:00"
}
```

---

## 🔧 Development Setup

### Prerequisites
- Java 17 or higher
- Maven 3.6 or higher
- Spring Boot 3.x

### Running the Application
```bash
mvn spring-boot:run
```

The application will be available at `http://localhost:8080`

---

## 📝 Notes

- All endpoints require proper request validation
- Date/time fields use ISO 8601 format
- Coordinates use decimal degrees format
- Phone numbers are stored as Long values
- Passwords should be properly hashed in production
- Consider implementing proper authentication and authorization

---

## 🤝 Contributing

Please follow the existing code structure and naming conventions when contributing to this project.

## 📄 License

*License information to be added*
