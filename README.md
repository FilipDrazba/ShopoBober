## Authors
- Drażba Filip
- Zajączkowski Piotr
- Ciereszyński Bartosz

## Starting the Program
```zsh
    ./buildAndRun.sh
```

# WithApiGateway – API Documentation

A set of services communicating through an API Gateway. The collection includes user authentication, product management, shopping cart operations, and report generation.

The `postman.json` file contains all the endpoints in the collections.

---

## Postman Environment Variables

Below are the environment variables used in the Postman collection:

(Below are tokens that have been crafted without an expiration time specifically for the key contained in .env.demo)

| Variable                 | Type     | Default Value                                                                                                                                                                                       |
|--------------------------|----------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| `API_GATEWAY_URL`        | default  | `http://localhost`                                                                                                                                                                                  |
| `SHOP_SERVICE_URL`       | default  | `http://localhost:8081`                                                                                                                                                                             |
| `AUTH_SERVICE_URL`       | default  | `http://localhost:8082`                                                                                                                                                                             |
| `REPORT_SERVICE_URL`     | default  | `http://localhost:8083`                                                                                                                                                                             |
| `JWT_EMPLOYEE`           | secret   | eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJlbXBsb3llZUBwYi5lZHUucGwiLCJyb2xlIjoiRU1QTE9ZRUUiLCJpYXQiOjE3NDgzNjQwNjB9.9UWEHOuakiBO-eAxLB5kLtdyEBuqRxY2FJUv4Lb2Dqc                               |
| `JWT_CUSTOMER`           | secret   | eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJjdXN0b21lckBwYi5lZHUucGwiLCJyb2xlIjoiQ1VTVE9NRVIiLCJpYXQiOjE3NDgzNjQxMDl9.kvyNd2NlFmJROSgoQg6DxjZufCCwFyRWwCDWV6TPEX8                               |
| `JWT_UNVERIFIED_EMPLOYEE`| secret   | eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJ1bnZlcmlmaWVkLmVtcGxveWVlQHBiLmVkdS5wbCIsInJvbGUiOiJVTlZFUklGSUVEX0VNUExPWUVFIiwiaWF0IjoxNzQ4MzY0MDI1fQ.g9rJBSHQEuYzQ46ttLl35RjX2f35CdO-jfnt5_ZQg2E |

---

To test the API locally, make sure all microservices are running on the specified ports:
- Auth Service – `8082`
- Shop Service – `8081`
- Report Service – `8083`

---

## Auth Service

### Register a User
**POST** `/auth/register`

#### Example (CUSTOMER):
```json
{
  "email": "customer@pb.edu.pl",
  "password": "Password123!",
  "role": "CUSTOMER"
}
```

#### Example (UNVERIFIED_EMPLOYEE):
```json
{
  "email": "unverified.employee@pb.edu.pl",
  "password": "Password123!",
  "role": "UNVERIFIED_EMPLOYEE"
}
```

---

### Login
**POST** `/auth/login`

```json
{
  "email": "customer@pb.edu.pl",
  "password": "Password123!"
}
```

---

### Validate JWT Token
**POST** `/auth/validate`

```json
{
  "token": "{{vault:json-web-token}}"
}
```

---

## Report Service

### Get Report
**GET** `/reports/get`

**Header:**  
`Authorization: Bearer {{JWT_CUSTOMER}}`

---

## Shop Service

### Products

#### Create Product
**POST** `/shop/product`  
`Authorization: Bearer {{JWT_EMPLOYEE}}`

```json
{
  "name": "a",
  "description": "b"
}
```

---

#### Get All Products
**GET** `/shop/product/all`  
`Authorization: Bearer {{JWT_CUSTOMER}}`

---

#### Get Product by ID
**GET** `/shop/product/{id}`  
`Authorization: Bearer {{JWT_CUSTOMER}}`

---

#### Update Product
**PUT** `/shop/product/{id}`  
`Authorization: Bearer {{JWT_EMPLOYEE}}`

```json
{
  "id": 1,
  "name": "c",
  "description": "d"
}
```

---

#### Delete Product
**DELETE** `/shop/product/{id}`  
`Authorization: Bearer {{JWT_EMPLOYEE}}`

---

### 🛒 Shopping Cart

#### Add Product to Cart
**POST** `/shop/cart`  
`Authorization: Bearer {{JWT_CUSTOMER}}`

```json
{
  "productId": 1,
  "quantity": 1
}
```

---

#### Remove Product from Cart
**DELETE** `/shop/cart/{productId}`  
`Authorization: Bearer {{JWT_CUSTOMER}}`

```json
{
  "productId": 1,
  "quantity": 1
}
```

---

#### Get All Products from Cart
**GET** `/shop/cart`  
`Authorization: Bearer {{JWT_CUSTOMER}}`

---