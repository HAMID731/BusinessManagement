# Small Business Record Keeper Backend

This is a Spring Boot application that serves as a backend for a small business management system. It includes features for daily sales tracking, inventory management with low-stock alerts, and employee attendance logging.

## Features

1.  **JWT Authentication**: Secure user registration and login.
2.  **Daily Sales Tracker**: Record daily sales and export them to CSV.
3.  **Inventory Management**: Track product stock and get alerts for low stock.
4.  **Attendance Logger**: Log employee shifts (start/end time) and calculate hours worked.

## Prerequisites

-   Java 17 or higher
-   Maven
-   MongoDB (running locally on default port 27017)

## Setup and Run

1.  **Start MongoDB**: Ensure your MongoDB instance is running.
2.  **Build the Project**:
    ```bash
    mvn clean package
    ```
3.  **Run the Application**:
    ```bash
    mvn spring-boot:run
    ```

## API Endpoints

### Authentication
-   `POST /api/auth/signup`: Register a new user.
    ```json
    {
      "username": "user",
      "email": "user@example.com",
      "password": "password",
      "roles": ["user"]
    }
    ```
-   `POST /api/auth/signin`: Login to get a JWT token.
    ```json
    {
      "username": "user",
      "password": "password"
    }
    ```

### Sales
-   `POST /api/sales`: Log a new sale.
    ```json
    {
      "itemName": "Coffee",
      "quantity": 2,
      "price": 5.0
    }
    ```
-   `GET /api/sales/today`: Get all sales for the current day.
-   `GET /api/sales/export`: Export today's sales to a CSV file (Admin only).

### Inventory
-   `POST /api/inventory`: Add a new product (Admin only).
    ```json
    {
      "name": "Milk",
      "quantity": 10,
      "minThreshold": 5
    }
    ```
-   `GET /api/inventory`: Get all products.
-   `GET /api/inventory/low-stock`: Get products with stock below threshold.
-   `PUT /api/inventory/{id}/stock?change=5`: Update stock quantity (positive to add, negative to remove).

### Attendance
-   `POST /api/attendance/start`: Start a shift.
-   `POST /api/attendance/end`: End the current shift.
-   `GET /api/attendance/my-shifts`: Get shift history for the logged-in user.

## Security

-   Most endpoints require a valid JWT token in the `Authorization` header: `Bearer <token>`.
-   Some endpoints are restricted to users with the `ADMIN` role.
