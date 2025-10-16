# Backend API Documentation

A comprehensive REST API for managing transportation orders, users, vehicles, and addresses.

## Base URL

```
http://localhost:5631/
```
##Video
swagger api documentation sneak-peak


https://github.com/user-attachments/assets/2a4bf650-8a40-414f-95dd-1accb2c543c2


## Table of Contents

- [Overview](#overview)
- [Authentication](#authentication)
- [API Endpoints](#api-endpoints)
  - [Authentication](#authentication-endpoints)
  - [Users](#user-endpoints)
  - [Vehicles](#vehicle-endpoints)
  - [Addresses](#address-endpoints)
  - [Orders](#order-endpoints)
- [Data Models](#data-models)
- [Response Format](#response-format)
- [Error Handling](#error-handling)

## Overview

This API provides endpoints for a transportation management system that supports user management, vehicle tracking, address management, and order processing. The system handles both customers and transporters with role-based functionality.

## Authentication

### Login

**Endpoint:** `POST /auth/login`

Authenticate a user and receive a JWT token.

**Request Body:**
```json
{
  "username": "string",
  "password": "string"
}
```

**Response:**
```json
{
  "jwtToken": "string"
}
```

**Usage:**
Include the JWT token in subsequent requests using the Authorization header:
```
Authorization: Bearer <your-jwt-token>
```

## API Endpoints

### User Endpoints

#### Create User
- **POST** `/users/create`
- Creates a new user account
- **Request Body:** [UserDto](#userdto)
- **Response:** [ApiResponse](#apiresponse)<[UserDto](#userdto)>

#### Get All Users
- **GET** `/users/getallusers`
- Retrieves all registered users
- **Response:** [ApiResponse](#apiresponse)<List<[UserDto](#userdto)>>

#### Get User by ID
- **GET** `/users/getuserbyid/{id}`
- Retrieves a specific user by their ID
- **Path Parameters:**
  - `id` (integer, required) - User ID
- **Response:** [ApiResponse](#apiresponse)<[UserDto](#userdto)>

#### Get User by Email
- **GET** `/users/getuserbyemail/{emailId}`
- Retrieves a user by their email address
- **Path Parameters:**
  - `emailId` (string, required) - User email
- **Response:** [ApiResponse](#apiresponse)<[UserDto](#userdto)>

#### Update User
- **PUT** `/users/update-user/{userId}`
- Updates user information
- **Path Parameters:**
  - `userId` (integer, required) - User ID
- **Request Body:** [UserDto](#userdto)
- **Response:** [ApiResponse](#apiresponse)<[UserDto](#userdto)>

#### Delete User
- **DELETE** `/users/delete-user/{userId}`
- Deletes a user account
- **Path Parameters:**
  - `userId` (integer, required) - User ID
- **Response:** [ApiResponse](#apiresponse)<String>

### Vehicle Endpoints

#### Add Vehicle
- **POST** `/users/{transporterId}/vehicle/add`
- Adds a vehicle for a transporter
- **Path Parameters:**
  - `transporterId` (integer, required) - Transporter user ID
- **Request Body:** [VehicleDetailsDto](#vehicledetailsdto)
- **Response:** [ApiResponse](#apiresponse)<[VehicleDetailsDto](#vehicledetailsdto)>

#### Update Vehicle
- **PUT** `/users/{transporterId}/vehicle/update`
- Updates vehicle details
- **Path Parameters:**
  - `transporterId` (integer, required) - Transporter user ID
- **Request Body:** [VehicleDetailsDto](#vehicledetailsdto)
- **Response:** [ApiResponse](#apiresponse)<[VehicleDetailsDto](#vehicledetailsdto)>

#### Get Vehicle by ID
- **GET** `/users/vehicle/getvehiclebyid/{vehicleId}`
- Retrieves vehicle details by vehicle ID
- **Path Parameters:**
  - `vehicleId` (integer, required) - Vehicle ID

- **Response:** [ApiResponse](#apiresponse)<[VehicleDetailsDto](#vehicledetailsdto)>

#### Get Vehicles by Transporter
- **GET** `/users/vehicle/getvehiclebytransporter/{transporterId}`
- Retrieves all vehicles for a specific transporter
- **Path Parameters:**
  - `transporterId` (integer, required) - Transporter user ID
- **Response:** [ApiResponse](#apiresponse)<[VehicleDetailsDto](#vehicledetailsdto)>

### Address Endpoints

#### Add Address
- **POST** `/users/{transporterId}/address/add`
- Adds an address for a transporter
- **Path Parameters:**
  - `transporterId` (integer, required) - Transporter user ID
- **Request Body:** [TransporterAddressDto](#transporteraddressdto)
- **Response:** [ApiResponse](#apiresponse)<[TransporterAddressDto](#transporteraddressdto)>

#### Update Address
- **PUT** `/users/address/update/{addressId}`
- Updates address information
- **Path Parameters:**
  - `addressId` (integer, required) - Address ID
- **Request Body:** [TransporterAddressDto](#transporteraddressdto)
- **Response:** [ApiResponse](#apiresponse)<[TransporterAddressDto](#transporteraddressdto)>

#### Get Address by ID
- **GET** `/users/address/getaddressbyid/{addressId}`
- Retrieves address by ID
- **Path Parameters:**
  - `addressId` (integer, required) - Address ID
- **Response:** [ApiResponse](#apiresponse)<[TransporterAddressDto](#transporteraddressdto)>

#### Get Address by Email
- **GET** `/users/address/getaddressbyemail/{emailId}`
- Retrieves transporter address by email
- **Path Parameters:**
  - `emailId` (string, required) - Transporter email
- **Response:** [ApiResponse](#apiresponse)<[TransporterAddressDto](#transporteraddressdto)>

### Order Endpoints

#### Create Order
- **POST** `/order/create`
- Creates a new delivery order
- **Request Body:** [OrderRequest](#orderrequest)
- **Response:** [ApiResponse](#apiresponse)<[OrderDto](#orderdto)>

#### Update Order
- **PUT** `/order/update-order/{orderId}`
- Updates order information
- **Path Parameters:**
  - `orderId` (integer, required) - Order ID
- **Request Body:** [OrderDto](#orderdto)
- **Response:** [ApiResponse](#apiresponse)<[OrderDto](#orderdto)>

#### Get Order by ID
- **GET** `/order/getorderbyid/{orderId}`
- Retrieves order details by ID
- **Path Parameters:**
  - `orderId` (integer, required) - Order ID
- **Response:** [ApiResponse](#apiresponse)<[OrderDto](#orderdto)>

#### Get All Orders by User
- **GET** `/order/get-all-orders/{userId}`
- Retrieves all orders for a specific user
- **Path Parameters:**
  - `userId` (integer, required) - User ID
- **Response:** [ApiResponse](#apiresponse)<List<[OrderDto](#orderdto)>>

#### Get Orders by City and Status
- **GET** `/order/get-orders-by-city/{city}/{status}`
- Retrieves orders filtered by city and status
- **Path Parameters:**
  - `city` (string, required) - City name
  - `status` (string, required) - Order status
- **Response:** [ApiResponse](#apiresponse)<List<[OrderDto](#orderdto)>>

#### Delete Order
- **DELETE** `/order/delete-order/{orderId}`
- Deletes an order
- **Path Parameters:**
  - `orderId` (integer, required) - Order ID
- **Response:** [ApiResponse](#apiresponse)<String>

## Data Models

### UserDto

```json
{
  "userId": 0,
  "name": "string (2-100 chars, required)",
  "email": "string (required)",
  "password": "string (8-24 chars)",
  "phone": "string (10-15 chars)",
  "role": "string",
  "createdAt": "2025-10-16T10:30:00Z (read-only)",
  "updatedAt": "2025-10-16T10:30:00Z (read-only)"
}
```

### VehicleDetailsDto

```json
{
  "vehicleId": 0,
  "userId": 0 (required),
  "vehicleType": "string (required)",
  "vehicleNumber": "string (required)",
  "vehicleMake": "string (required)",
  "vehicleModel": "string (required)",
  "owner": "string (required)"
}
```

### TransporterAddressDto

```json
{
  "addressId": 0,
  "street": "string (required)",
  "city": "string (required)",
  "state": "string (required)",
  "zipCode": "string (required)",
  "country": "string (required)",
  "userId": 0
}
```

### OrderRequest

```json
{
  "id": 0,
  "userID": 0 (required),
  "pickupLocation": {
    "address": "string (required)",
    "pincode": 0 (required),
    "latitude": 0.0 (required),
    "longitude": 0.0 (required),
    "contactPerson": "string (required)",
    "contactPhone": 0 (required),
    "instructions": "string"
  },
  "dropLocation": {
    "address": "string (required)",
    "pincode": 0 (required),
    "latitude": 0.0 (required),
    "longitude": 0.0 (required)",
    "contactPerson": "string (required)",
    "contactPhone": 0 (required)",
    "instructions": "string"
  },
  "orderDetails": {
    "itemName": "string (required)",
    "description": "string",
    "category": "string (required)",
    "isFragile": false (required),
    "notes": "string",
    "dimensions": {
      "length": 0.0,
      "width": 0.0,
      "height": 0.0
    }
  },
  "deliveryType": "string (required)",
  "scheduledPickupTime": "2025-10-16T10:30:00Z (required)",
  "createdAt": "2025-10-16T10:30:00Z",
  "updatedAt": "2025-10-16T10:30:00Z"
}
```

### OrderDto

Extends OrderRequest with additional fields:

```json
{
  ...OrderRequest fields,
  "transporterId": 0,
  "price": 0.0,
  "status": "string"
}
```

## Response Format

### ApiResponse

All endpoints return a standardized response format:

```json
{
  "success": true,
  "status": 200,
  "message": "Operation successful",
  "data": {}, // Response data (varies by endpoint)
  "timestamp": "2025-10-16T10:30:00Z"
}
```

### Success Response Example

```json
{
  "success": true,
  "status": 200,
  "message": "User created successfully",
  "data": {
    "userId": 1,
    "name": "John Doe",
    "email": "john@example.com",
    "phone": "1234567890",
    "role": "CUSTOMER",
    "createdAt": "2025-10-16T10:30:00Z",
    "updatedAt": "2025-10-16T10:30:00Z"
  },
  "timestamp": "2025-10-16T10:30:00Z"
}
```

### Error Response Example

```json
{
  "success": false,
  "status": 400,
  "message": "Invalid request data",
  "data": null,
  "timestamp": "2025-10-16T10:30:00Z"
}
```

## Error Handling

### Common HTTP Status Codes

- **200 OK** - Request successful
- **400 Bad Request** - Invalid request data or parameters
- **401 Unauthorized** - Missing or invalid authentication token
- **404 Not Found** - Resource not found
- **500 Internal Server Error** - Server error

## Example Usage

### Creating a User

```bash
curl -X POST "http://localhost:5631/users/create" \
  -H "Content-Type: application/json" \
  -d '{
    "name": "John Doe",
    "email": "john@example.com",
    "password": "securepass123",
    "phone": "1234567890",
    "role": "CUSTOMER"
  }'
```

### Creating an Order

```bash
curl -X POST "http://localhost:5631/order/create" \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer <your-jwt-token>" \
  -d '{
    "userID": 1,
    "pickupLocation": {
      "address": "123 Main St",
      "pincode": 400001,
      "latitude": 19.0760,
      "longitude": 72.8777,
      "contactPerson": "John Doe",
      "contactPhone": 1234567890,
      "instructions": "Gate code: 1234"
    },
    "dropLocation": {
      "address": "456 Park Ave",
      "pincode": 400002,
      "latitude": 19.0896,
      "longitude": 72.8656,
      "contactPerson": "Jane Smith",
      "contactPhone": 9876543210,
      "instructions": "Ring doorbell"
    },
    "orderDetails": {
      "itemName": "Electronics Package",
      "description": "Laptop and accessories",
      "category": "Electronics",
      "isFragile": true,
      "notes": "Handle with care",
      "dimensions": {
        "length": 50.0,
        "width": 40.0,
        "height": 10.0
      }
    },
    "deliveryType": "EXPRESS",
    "scheduledPickupTime": "2025-10-17T14:00:00Z"
  }'
```

## Notes

- All timestamps are in ISO 8601 format (UTC)
- Phone numbers should be provided as integers
- Coordinates (latitude/longitude) are decimal numbers
- All required fields must be provided in requests
- The API uses JWT for authentication after initial login

## Support

For issues or questions, please contact the development team or refer to the API documentation at the Swagger UI endpoint.
