# Final Project

This is a Spring Boot application for managing users, including functionalities for user authentication, profile image upload, and more. This project is part of an inventory management system and is not yet complete. Future functionalities will include searching by QR code and live chat between users.
## Table of Contents

- [Technologies Used](#technologies-used)
- [Getting Started](#getting-started)
    - [Prerequisites](#prerequisites)
    - [Installation](#installation)
- [API Endpoints](#api-endpoints)
    - [User Endpoints](#user-endpoints)
- [Swagger Documentation](#swagger-documentation)
- [Project Structure](#project-structure)
- [Frontend](#frontend)
- [Security Configuration](#security-configuration)
- [Build and Dependency Management](#build-and-dependency-management)
- [License](#license)

## Technologies Used

- Java 17
- Spring Boot
- Spring Security
- Spring Data JPA
- PostgreSQL
- Gradle
- Lombok
- Springdoc OpenAPI

## Project Short Demo
https://github.com/user-attachments/assets/154f9528-231d-4e5b-82ad-263b3e5bf122

## Getting Started

### Prerequisites

- Java 17
- Gradle
- PostgreSQL

### Installation

1. Clone the repository:
    ```sh
    git clone https://github.com/muz2002/final_project.git
    cd final_project
    ```

2. Update the database configuration in `src/main/resources/application.properties`:
    ```properties
    spring.datasource.url=jdbc:postgresql://localhost:5432/your_database
    spring.datasource.username=your_username
    spring.datasource.password=your_password
    ```

3. Build the project:
    ```sh
    ./gradlew build
    ```

4. Run the application:
    ```sh
    ./gradlew bootRun
    ```

## API Endpoints

### User Endpoints

#### Get User by ID

- **URL:** `/users/{userId}`
- **Method:** `GET`
- **Description:** Retrieve a user by their ID.
- **Response:**
    ```json
    {
        "userId": 1,
        "name": "John Doe",
        "username": "johndoe",
        "email": "john.doe@example.com",
        "country": "USA"
    }
    ```

#### List All Users

- **URL:** `/users/list-users`
- **Method:** `GET`
- **Description:** Retrieve a list of all users.
- **Response:**
    ```json
    [
        {
            "userId": 1,
            "name": "John Doe",
            "username": "johndoe",
            "email": "john.doe@example.com",
            "country": "USA"
        },
        ...
    ]
    ```

#### Upload Profile Image

- **URL:** `/users/uploadProfileImage/{userId}`
- **Method:** `POST`
- **Description:** Upload a profile image for a user.
- **Request:**
    - `file`: Multipart file
- **Response:**
    ```json
    {
        "message": "Profile image uploaded successfully: 1_profile.jpg",
        "imageUrl": "/users/profileImage/1"
    }
    ```

#### Get Profile Image

- **URL:** `/users/profileImage/{userId}`
- **Method:** `GET`
- **Description:** Retrieve the profile image of a user.
- **Response:** Binary image data

#### Update User

- **URL:** `/users/update-user/{userId}`
- **Method:** `PUT`
- **Description:** Update user details.
- **Request:**
    ```json
    {
        "name": "John Doe",
        "username": "johndoe",
        "email": "john.doe@example.com",
        "country": "USA"
    }
    ```
- **Response:** `200 OK`

#### Delete User

- **URL:** `/users/{userId}`
- **Method:** `DELETE`
- **Description:** Delete a user by their ID.
- **Response:** `200 OK`

## Swagger Documentation

The application includes Swagger for API documentation. You can access the Swagger UI at the following endpoint:

- **URL:** `/swagger-ui.html`
- **Description:** Access the Swagger UI to explore and test the API endpoints.

## Project Structure
```
├─ src
│  ├─ main
│  │  ├─ java
│  │  │  └─ com
│  │  │     └─ example
│  │  │        └─ final_project
│  │  │           ├─ config
│  │  │           │  ├─ ApplicationConfig.java
│  │  │           │  └─ SecurityConfiguration.java
│  │  │           ├─ controller
│  │  │           │  ├─ AuthController.java
│  │  │           │  ├─ CountryController.java
│  │  │           │  ├─ ForgotPasswordController.java
│  │  │           │  ├─ ItemController.java
│  │  │           │  └─ UserController.java
│  │  │           ├─ dto
│  │  │           │  ├─ MailBody.java
│  │  │           │  └─ UserDTO.java
│  │  │           ├─ entity
│  │  │           │  ├─ Country.java
│  │  │           │  ├─ ForgotPassword.java
│  │  │           │  ├─ Item.java
│  │  │           │  ├─ RefreshToken.java
│  │  │           │  ├─ User.java
│  │  │           │  └─ UserRole.java
│  │  │           ├─ FinalProjectApplication.java
│  │  │           ├─ repository
│  │  │           │  ├─ CountryRepository.java
│  │  │           │  ├─ ForgotPasswordRepository.java
│  │  │           │  ├─ ItemRepository.java
│  │  │           │  ├─ RefreshTokenRepository.java
│  │  │           │  └─ UserRepository.java
│  │  │           ├─ service
│  │  │           │  ├─ AuthFilterService.java
│  │  │           │  ├─ AuthService.java
│  │  │           │  ├─ CountryService.java
│  │  │           │  ├─ EmailService.java
│  │  │           │  ├─ ItemService.java
│  │  │           │  ├─ JwtService.java
│  │  │           │  ├─ RefreshTokenService.java
│  │  │           │  └─ UserService.java
│  │  │           └─ utils
│  │  │              ├─ AuthResponse.java
│  │  │              ├─ ChangePassword.java
│  │  │              ├─ LoginRequest.java
│  │  │              ├─ RefreshTokenRequest.java
│  │  │              └─ RegisterRequest.java
│  │  └─ resources
│  │     └─ application.properties
│  └─ test
│     └─ java
│        └─ com
│           └─ example
│              └─ final_project
│                 └─ FinalProjectApplicationTests.java
└─ uploads
   └─ profile_images

```
## Frontend

The frontend part of this project is available in a separate repository. You can find it at the following link:

[Frontend Repository](https://github.com/muz2002/Inventory-Management-Frontend.git)

## Security Configuration

The application uses JWT for authentication and includes the following security configurations:

- **CORS:** Configured to allow requests from `http://localhost:5173` and `http://localhost:8080`.
- **CSRF:** Disabled.
- **Session Management:** Stateless.
- **Authentication Provider:** Custom authentication provider.
- **Filters:** Custom authentication filter.

## Build and Dependency Management

The project uses Gradle for build and dependency management. Key dependencies include:

- `spring-boot-starter-security`
- `spring-security-crypto`
- `springdoc-openapi-starter-webmvc-ui`
- `spring-boot-starter-mail`
- `jjwt-api`
- `spring-boot-starter-validation`
- `spring-boot-starter-data-jpa`
- `postgresql`
- `spring-boot-starter-web`

## License

This project is licensed under the MIT License.

