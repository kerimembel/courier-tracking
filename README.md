# Courier Tracking

This is a Web Application developed by Kerim Embel to track couriers' geolocations.

## Requirements

For building and running the application you need:

- [JDK 17](https://www.oracle.com/java/technologies/downloads/#java17)
- [Maven 3](https://maven.apache.org)
- [Docker](https://www.docker.com)
- [Intellij IDEA](https://www.jetbrains.com/idea/)
- [PostgreSQL](https://www.postgresql.org/)
- [Postgis](https://postgis.net/)

## Live Demo

If you would like to explore and test the Courier Tracking Application, you can access the live demo at [https://courier-tracking.keonsys.com](https://courier-tracking.keonsys.com)

## Postman Collection

A Postman collection has been added to the repository for your convenience. You can find the collection in the `Postman Collection` directory. Use this collection to easily import the API requests into Postman for testing and exploration.


## Running the Application

The project folder downloaded from Github as zip or cloned is opened on Intellij IDEA.

### Running with Docker

> **Note:** To run the application with Docker, Docker must be installed on your computer.

After running Docker, go to the directory where the project is located in the Command Line.

Build the project with Docker using the following command:

```sh
docker-compose up -d
```

### Running Locally


> **Note:** To run the application on the local computer, the tools in the Requirements list must be installed.

1. **Download and Install PostgreSQL with PostGIS:**
    - Download PostgreSQL from [PostgreSQL official website](https://www.postgresql.org/download/) and install it.
    - During installation, make sure to select the PostGIS extension.

2. **Create a Database:**
    - Create a PostgreSQL database (e.g., `courier_tracking_db`).

3. **Configure the Application:**
    - Update the connection details in the `application.properties` file.
      Example `application.properties` file:

      ```properties
      spring.datasource.url=jdbc:postgresql://localhost:5432/courier_tracking_db
      spring.datasource.username=your_username
      spring.datasource.password=your_password
      ```
      Replace `your_username` and `your_password` with your PostgreSQL username and password.

4. **Add PostGIS Functions:**
    - Execute the following SQL query to enable PostGIS functions in your database:

      ```sql
      CREATE EXTENSION IF NOT EXISTS postgis;
      ```

5. **Start the Application:**
    - Open the project in IntelliJ IDEA.
    - Run the `CourierTrackingApplication` class to start the application.


## API Details

### Test Users

The application comes with pre-defined test users for different roles. These users can be used for testing purposes:

- **Admin User:**
    - **Email:** admin@migrosone.com
    - **Password:** admin

- **Courier User:**
    - **Email:** courier@migrosone.com
    - **Password:** courier

Feel free to use these test users to explore and test the features of the Courier Tracking Application.

### Endpoints

#### Register User

- **Endpoint:** `/auth/register`
- **HTTP Method:** `POST`
- **Description:** Registers a new user.
- **Request Body:**
    - `email` (String): The email address of the user.
    - `password` (String): The password for the user.
    - `firstName` (String): The first name for the user.
    - `lastName` (String): The last name for the user.
- **Response:**
    - Success: 200 OK
    - Response Body: 
```json
{
    "success": true,
    "message": "success",
    "data": {
        "token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhbm90aGVyY291cmllckBtaWdyb3NvbmUuY29tIiwicm9sZSI6IkNPVVJJRVIiLCJjb3VyaWVySWQiOiIyYTgxNmU4MS00MjU1LTQ3YTgtODY2My03MTA3YTZjZjI2ZTciLCJ1c2VySWQiOiJhYjY0N2UzZS01ZTlhLTQwZWMtYmNlMy03MjBlNjNkOGM1MjkiLCJpYXQiOjE3MDQ3NDQzODEsImV4cCI6MTcwNDc4MDM4MX0.9_bVcXwlWlgZ9Ev8goPV9KhELmSwNmFlylBSkNqNFwSgD8KTeLgV2jP1d2dTi6MLE-X90jmN-5iwebQPaelSFw",
        "tokenType": "Bearer",
        "id": "ab647e3e-5e9a-40ec-bce3-720e63d8c529",
        "courierId": "2a816e81-4255-47a8-8663-7107a6cf26e7",
        "email": "anothercourier@migrosone.com",
        "role": "COURIER"
    }
}
```
#### Login User

- **Endpoint:** `/auth/login`
- **HTTP Method:** `POST`
- **Description:** Authenticates a user and returns a JWT token.
- **Request Body:**
    - `email` (String): The email address of the user.
    - `password` (String): The password for the user.
- **Response:**
    - Success: 200 OK
    - Response Body: 
```json
{
    "success": true,
    "message": "success",
    "data": {
        "token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJjb3VyaWVyQG1pZ3Jvc29uZS5jb20iLCJyb2xlIjoiQ09VUklFUiIsImNvdXJpZXJJZCI6IjgzMjZjNGUwLWI1YjUtNDNmYi1iODczLTM3ZTRjZDRmN2ZmNCIsInVzZXJJZCI6IjY5MWMyZmNhLThlYWMtNDM5Zi05YjhlLTA0N2ZmODljMjMyZiIsImlhdCI6MTcwNDc0NDQwOCwiZXhwIjoxNzA0NzgwNDA4fQ.x7NNTwYnrKSQ2FHGhZQlb4J4oKQfxMqy-PQFQXzZdpckJNkxcOYaxrPGunyyvyoqIUW0FXx5mKzb_QOmJfPmJg",
        "tokenType": "Bearer",
        "id": "691c2fca-8eac-439f-9b8e-047ff89c232f",
        "courierId": "8326c4e0-b5b5-43fb-b873-37e4cd4f7ff4",
        "email": "courier@migrosone.com",
        "role": "COURIER"
    }
}
```
#### Process Courier Location

- **Endpoint:** `/v0/couriers/{courierId}/location`
- **HTTP Method:** `POST`
- **Description:** Processes the courier's location update.
- **Request Parameters:**
    - `courierId` (UUID): The unique identifier of the courier.
- **Request Body:**
    - `latitude` (Double): The latitude coordinate of the courier's location.
    - `longitude` (Double): The longitude coordinate of the courier's location.
    - `timestamp` (String): The timestamp of the location update.
- **Response:**
    - Success: 200 OK
    - Response Body: 
```json
{
  "success": true,
  "message": "success",
  "data": "Courier location processed successfully."
}
```
#### Get Total Travel Distance

- **Endpoint:** `/v0/couriers/{courierId}/total-travel-distance`
- **HTTP Method:** `GET`
- **Description:** Retrieves the total travel distance of the courier.
- **Request Parameters:**
    - `courierId` (UUID): The unique identifier of the courier.
- **Response:**
    - Success: 200 OK
    - Response Body:
```json
{
  "success": true,
  "message": "success",
  "data": {
    "totalTravelDistance": 111.19492664455873,
    "distanceUnit": "KM"
  }
}
```
#### Get All Store Entries for Courier

- **Endpoint:** `/v0/couriers/{courierId}/entries`
- **HTTP Method:** `GET`
- **Description:** Retrieves all store entries for a specific courier.
- **Request Parameters:**
    - `courierId` (UUID): The unique identifier of the courier.
- **Response:**
    - Success: 200 OK
    - Response Body:
```json
{
  "success": true,
  "message": "success",
  "data": {
    "courierEntries": [
      {
        "id": "a6a7a719-6c8c-4358-b894-2bbdb80a44f3",
        "storeId": "c57ff0f9-98b4-421d-85f0-60dbf3f389d5",
        "courierId": "8326c4e0-b5b5-43fb-b873-37e4cd4f7ff4",
        "entryTime": "2022-01-04T14:33:57"
      }
    ]
  }
}
```
#### Get Store Entry for Courier

- **Endpoint:** `/v0/couriers/{courierId}/entries/stores/{storeId}`
- **HTTP Method:** `GET`
- **Description:** Retrieves store entries for a specific courier and store.
- **Request Parameters:**
    - `courierId` (UUID): The unique identifier of the courier.
    - `storeId` (UUID): The unique identifier of the store.
- **Response:**
    - Success: 200 OK
    - Response Body:
```json
{
  "success": true,
  "message": "success",
  "data": {
    "courierEntries": [
      {
        "id": "a6a7a719-6c8c-4358-b894-2bbdb80a44f3",
        "storeId": "c57ff0f9-98b4-421d-85f0-60dbf3f389d5",
        "courierId": "8326c4e0-b5b5-43fb-b873-37e4cd4f7ff4",
        "entryTime": "2022-01-04T14:33:57"
      }
    ]
  }
}
```
#### Get All Stores

- **Endpoint:** `/v0/stores`
- **HTTP Method:** `GET`
- **Description:** Retrieves all stores.
- **Response:**
    - Success: 200 OK
    - Response Body:
```json
{
  "success": true,
  "message": "success",
  "data": {
    "stores": [
      {
        "id": "e17cfd2c-8d89-405b-bd5e-ab0e83be4f1a",
        "name": "Ataşehir MMM Migros",
        "location": "POINT (40.9923307 29.1244229)"
      },
      {
        "id": "c57ff0f9-98b4-421d-85f0-60dbf3f389d5",
        "name": "Novada MMM Migros",
        "location": "POINT (40.986106 29.1161293)"
      },
      {
        "id": "57bba7fa-d612-4c16-ba4f-bcc5e17ffa23",
        "name": "Beylikdüzü 5M Migros",
        "location": "POINT (41.0066851 28.6552262)"
      },
      {
        "id": "c6fce7a4-6b03-47b4-a98b-8387278bd6ad",
        "name": "Ortaköy MMM Migros",
        "location": "POINT (41.055783 29.0210292)"
      },
      {
        "id": "a61540a6-48d2-4ff0-973f-08cfd8173bf7",
        "name": "Caddebostan MMM Migros",
        "location": "POINT (40.9632463 29.0630908)"
      }
    ]
  }
}
```