# Products, Carts, and Sales Management Project using Java, Spring, MySQL and Hibernate

## Description

This project is a system for managing products, carts, and sales, implemented using **Spring cloud**, **Spring boot**, **Maven** and **Hibernate**.

It was developed as part of the "Microservicios con Spring Cloud" course by TodoCode.

## Features

- **Product CRUD:** Create, read, update, and delete products in the system.
- **Endpoints:**
  - Create a product: `POST /products`
  - Get all products: `GET /products`
  - Get product by ID: `GET /products/{id}`
  - Update a product: `PUT /products/{id}`
  - Delete a product: `DELETE /products/{id}`
- **Extra:**
  - Get low-stock products: `GET /products/low_stock`

- **Cart CRUD:** Create a cart, retrieve a cart, add and remove products from it, and empty it.
- **Endpoints:**
  - Create a cart: `POST /carts`
  - Add a product to a cart: `POST /carts/{cart_id}/addproduct/{product_id}`
  - Remove a product from a cart: `DELETE /carts/{cart_id}/product/{product_id}`
  - Get a cart: `GET /carts/{cart_id}`
  - Empty a cart: `PUT /carts/{cart_id}`

- **Sales CRUD:** Create and retrieve sales.
- **Endpoints:**
  - Create a sale: `POST /sales/{cart_id}`
  - Get all sales: `GET /sales`
  - Get a sale by ID: `GET /sales/{sale_id}`
- **Extras:**
  - Get the product list from a specific sale: `GET /sales/products/{sale_id}`
  - Get details of the highest-value sale: `GET /sales/biggest_sale`
  - Get the total amount and number of sales for a specific day `GET /sales/date/{sale_date}`

## Technologies

[![My Skills](https://skillicons.dev/icons?i=spring,java,hibernate,mysql,docker,maven,idea,postman)](https://skillicons.de)

- **Spring boot:** Used as the framework for creating RESTful APIs.
- **Spring cloud:** Used for building distributed systems and microservices.
- **Maven:** For dependency management.
- **Lombok:** For cleaner code.
- **Eureka:** Service registry and discovery.
- **Config server:** Centralized configuration management for microservices.
- **OpenFeign:** HTTP client creation to enable communication between microservices.
- **Resilience4J:** Fault tolerance in the face of errors or high latencies.
- **Gateway:** Request routing.
- **Cloud LoadBalancer:** Incoming traffic distribution.
- **MySQL:** For data persistence.
- **Hibernate:** To interact with the database from the backend.
- **Postman:** For testing HTTP requests.
- **Docker:** To build images and run containers.