# Smart Logix Backend API Documentation

**Base URL:** All endpoints are relative to your deployment domain.  
**Format:** All requests and responses use JSON.

---

## Table of Contents

- [Users API](#users-api)
- [Orders API](#orders-api)
- [Transporters API](#transporters-api)
- [Common DTOs](#common-dtos)
- [Response & Error Structure](#response--error-structure)

---

## Users API

### Create User
- **POST** `/users/create`
- **Body:** [`UserDto`](#userdto)
- **Response:** `201 Created`, [`UserDto`](#userdto)

### Update User
- **PUT** `/users/update-user/{userId}`
- **Body:** [`UserDto`](#userdto)
- **Path Variable:** `userId` (Long)
- **Response:** `200 OK`, [`UserDto`](#userdto)

### Get All Users
- **GET** `/users/getallusers`
- **Response:** `200 OK`, `List<UserDto>`

### Get User by ID
- **GET** `/users/getuserbyid/{id}`
- **Path Variable:** `id` (Long)
- **Response:** `200 OK`, [`UserDto`](#userdto)

### Get User by Email
- **GET** `/users/getuserbyemail/{emailId}`
- **Path Variable:** `emailId` (String)
- **Response:** `200 OK`, [`UserDto`](#userdto)

### Delete User
- **DELETE** `/users/delete-user/{userId}`
- **Path Variable:** `userId` (Long)
- **Response:** `200 OK`, [`DeleteResponse`](#deleteresponse)

---

## Orders API

### Create Order
- **POST** `/order/create`
- **Body:** [`OrderRequest`](#orderrequest)
- **Response:** `201 Created`, [`OrderDto`](#orderdto)

### Update Order
- **PUT** `/order/update-order/{orderId}`
- **Body:** [`OrderDto`](#orderdto)
- **Path Variable:** `orderId` (Long)
- **Response:** `200 OK`, [`OrderDto`](#orderdto)

### Get All Orders (by User)
- **GET** `/order/get-all-orders/{userId}`
- **Path Variable:** `userId` (Long)
- **Response:** `200 OK`, `List<OrderDto>`

### Get Order by ID
- **GET** `/order/getorderbyid/{orderId}`
- **Path Variable:** `orderId` (Long)
- **Response:** `200 OK`, [`OrderDto`](#orderdto)

### Get Orders by City and Status
- **GET** `/order/get-orders-by-city/{city}/{status}`
- **Path Variables:** `city` (String), `status` (String)
- **Response:** `200 OK`, `List<OrderDto>`

### Delete Order
- **DELETE** `/order/delete-order/{orderId}`
- **Path Variable:** `orderId` (Long)
- **Response:** `200 OK`, [`DeleteResponse`](#deleteresponse)

---

## Transporters API

### Register Transporter
- **POST** `/transporters/register`
- **Body:** [`TransporterDto`](#transporterdto)
- **Response:** `201 Created`, [`TransporterDto`](#transporterdto)

### Update Transporter
- **PUT** `/transporters/update-transporter/{transporterId}`
- **Body:** [`TransporterDto`](#transporterdto)
- **Path Variable:** `transporterId` (Long)
- **Response:** `200 OK`, [`TransporterDto`](#transporterdto)

### Get Transporter by ID
- **GET** `/transporters/gettransporterbyid/{id}`
- **Path Variable:** `id` (Long)
- **Response:** `200 OK`, [`TransporterDto`](#transporterdto)

### Get Transporter by Email
- **GET** `/transporters/gettransporterbyemail/{emailId}`
- **Path Variable:** `emailId` (String)
- **Response:** `200 OK`, [`TransporterDto`](#transporterdto)

### Delete Transporter
- **DELETE** `/transporters/delete-transporter/{transporterId}`
- **Path Variable:** `transporterId` (Long)
- **Response:** `200 OK`, [`DeleteResponse`](#deleteresponse)

#### Transporter Addresses

- **Add Address:**  
  **POST** `/transporters/{transporterId}/address/add`  
  **Body:** [`TransporterAddressDto`](#transporteraddressdto)

- **Update Address:**  
  **PUT** `/transporters/address/update/{addressId}`  
  **Body:** [`TransporterAddressDto`](#transporteraddressdto)

- **Get Address by ID:**  
  **GET** `/transporters/address/getaddressbyid/{addressId}`

- **Get Address by Transporter Email:**  
  **GET** `/transporters/address/getaddressbyemail/{emailId}`

#### Vehicles

- **Add Vehicle:**  
  **POST** `/transporters/{transporterId}/vehicle/add`  
  **Body:** [`VehicleDetailsDto`](#vehicledetailsdto)

- **Update Vehicle:**  
  **PUT** `/transporters/{transporterId}/vehicle/update`  
  **Body:** [`VehicleDetailsDto`](#vehicledetailsdto)

- **Get Vehicle by ID:**  
  **GET** `/transporters/vehicle/getvehiclebyid/{vehicleId}`

- **Get Vehicle by Transporter:**  
  **GET** `/transporters/vehicle/getvehiclebytransporter/{transporterId}`

---

## Common DTOs

### UserDto
```json
{
  "userId": 1,
  "name": "string",
  "email": "user@example.com",
  "password": "string"
}
```

### OrderRequest
```json
{
  "id": 1,
  "userID": 1,
  "pickupLocation": { /* PickupLocationDto */ },
  "dropLocation": { /* DropLocationDto */ },
  "orderDetails": { /* OrderDetailsDto */ },
  "deliveryType": "string",
  "scheduledPickupTime": "2025-06-28T12:00:00",
  "createdAt": "2025-06-28T12:00:00",
  "updatedAt": "2025-06-28T12:00:00"
}
```

### OrderDto
- Same as `OrderRequest`, plus:
    - `transporterId`, `price`, `status`

### PickupLocationDto / DropLocationDto
```json
{
  "address": "string",
  "pincode": 123456,
  "latitude": 28.6,
  "longitude": 77.2,
  "contactPerson": "string",
  "contactPhone": 9876543210,
  "instructions": "string"
}
```

### OrderDetailsDto
```json
{
  "itemName": "string",
  "description": "string",
  "category": "string",
  "isFragile": true,
  "notes": "string",
  "dimensions": { /* DimensionsDto */ }
}
```

### DimensionsDto
```json
{
  "length": 10.5,
  "width": 5.5,
  "height": 2.0
}
```

### TransporterDto
```json
{
  "transporterId": 1,
  "name": "string",
  "email": "string",
  "password": "string",
  "phone": "string",
  "createdAt": "2025-06-28T12:00:00",
  "updatedAt": "2025-06-28T12:00:00"
}
```

### TransporterAddressDto
```json
{
  "addressId": 1,
  "transporterId": 1,
  "street": "string",
  "city": "string",
  "state": "string",
  "zipCode": "string",
  "country": "string"
}
```

### VehicleDetailsDto
```json
{
  "vehicleId": 1,
  "transporterId": 1,
  "vehicleType": "truck",
  "vehicleNumber": "string",
  "vehicleMake": "string",
  "vehicleModel": "string",
  "owner": "self"
}
```

### DeleteResponse
```json
{
  "message": "user deleted successfully",
  "Status": true
}
```

---

## Response & Error Structure

- **Success:**  
  - HTTP status codes (`200`, `201`, etc.)  
  - JSON body as per endpoint above

- **Validation Errors:**  
  - HTTP `400 Bad Request`
  - Body: `{ "field": "error message", ... }`

- **DeleteResponse:**  
  - On successful delete, returns the message and status as shown above.

- **Other Errors:**  
  - Standard Spring Boot error body or custom error handlers (not shown in provided files).

---

## Notes

- All DTOs use validation annotations; invalid payloads will result in `400 Bad Request`.
- All endpoints expect/return JSON.
- Field names in requests/responses are case-sensitive as shown in DTOs.

---
