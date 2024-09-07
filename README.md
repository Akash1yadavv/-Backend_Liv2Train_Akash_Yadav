# Liv2Train MVP: API for Training Center Management

This Liv2Train MVP project is a Spring Boot-based application designed to manage government-funded training centers through two APIs: a POST API for creating and registering new centers with fields like center name, code, address, student capacity, and contact details, and a GET API for retrieving all registered centers with optional filtering. The project employs field validation using annotations, global exception handling with @ExceptionHandler, and JWT-based role-based authentication, allowing ADMIN users to create centers and both USER and ADMIN users to view them. Built with Spring Data JPA for MySQL database interaction, Hibernate Validator for data integrity, and Spring Security with JWT for authentication, the setup includes clear instructions for running and securing the application.


## Getting Started

## Technologies Used

- **Backend**: Spring Boot, Java
- **Database**: MySQL 
- **Authentication**: JWT (JSON Web Tokens), Spring Security
  
### Prerequisites

- Java 17 or later
- Maven
- MySQL
- Postman (for API testing)
- Swagger
- STS OR Intellij

### Setup

1. **Clone the Repository:**

    ```bash
    git clone https://github.com/Akash1yadavv/Backend_Liv2Train_Akash_Yadav
    cd Backend_Liv2Train_Akash_Yadav
    ```

2. **Create and Configure MySQL Database:**

    - Create a new MySQL database.
    - Update the `src/main/resources/application.properties` file with your database credentials:

      ```properties
      spring.datasource.url=jdbc:mysql://${Host_Name:localhost}:${DB_Port:3306}/${DB_Name:senpiper}
      spring.datasource.username=${DB_UserNname:root}
      spring.datasource.password=${DB_Password:root}
      spring.jpa.hibernate.ddl-auto=update
      
      ```

3. **Build and Run the Application:**

    ```bash
    mvn clean install
    mvn spring-boot:run
    ```

## API Endpoints

### Authentication

#### 1. Sign Up

- **Endpoint:** `POST http://localhost:8080/api/senpiper/register-user`
- **Description:** Registers a new user.
- **Request Body:**

    ```json
    {
      "email": "user@example.com",
      "password": "your_password",
      "name": "John Doe",
      "phone": "9234567890"
    }
    ```

#### 2. Log In

- **Endpoint:** `POST http://localhost:8080/api/prospecta/auth/login`
- **Description:** Authenticates a user and returns a JWT token.
- **Request Body:**

    ```json
    {
      "email": "user@example.com",
      "password": "your_password"
    }
    ```

- **Response:**

    ```json
    {
      "token": "your_jwt_token",
      "expiresIn": "time_in_milli_second"
    }
    ```

### Training Center Management

#### 1. Get All Traning Center Or By Center name or center code or city

- **Endpoint:** `GET http://localhost:8080/api/senpiper/training-centers`
- **Description:** Retrieves a list of centers.
- **Parameters: Not Required**
  - `centerName=` or `centerCode=` or `city=` : The List of Training center according to the parameters if not anything then return all Exist Traning center in the database.
- **Response:**

    ```json
    [
      {
        "id": 1,
        "centerName": "Tech Training Center",
        "centerCode": "TC1234567890",
        "address": {
          "detailedAddress": "123 Main Street",
          "city": "New York",
          "state": "NY",
          "pincode": "100012"
        },
        "studentCapacity": 100,
        "coursesOffered": [
          "Java",
          "Spring Boot"
        ],
        "contactEmail": "contact@techcenter.com",
        "contactPhone": "6234567890",
        "createdOn": "2024-09-07T18:51:14.139718Z"
      }
      // More centers
    ]
    ```

#### 2. Add New Traning Center

- **Endpoint:** `POST http://localhost:8080/api/senpiper/register-training-center`
- **Description:** Adds a new Traing center.
- **Request Body:**

    ```json
      {
        "centerName": "Tech Training Center",
        "centerCode": "TC1234567899",
        "address": {
          "detailedAddress": "123 Main Street",
          "city": "New York",
          "state": "NY",
          "pincode": "100071"
        },
        "studentCapacity": 100,
        "coursesOffered": ["Java", "Spring Boot"],
        "contactEmail": "contact@techcenter.com",
        "contactPhone": "9234567890"
      }
    ```

- **Response:**

    ```json
      {
        "id": 5,
        "centerName": "Tech Training Center",
        "centerCode": "TC1234567899",
        "address": {
          "detailedAddress": "123 Main Street",
          "city": "New York",
          "state": "NY",
          "pincode": "100071"
        },
        "studentCapacity": 100,
        "coursesOffered": [
          "Java",
          "Spring Boot"
        ],
        "contactEmail": "contact@techcenter.com",
        "contactPhone": "9234567890",
        "createdOn": "2024-09-07T19:48:01.358829Z"
      }
    ```

## How to Test

**Open Browser And paste this URL `http://localhost:8080/swagger-ui/index.html`  **

**OR**

1. **Sign Up:**

    Use Postman to send a `POST` request to `http://localhost:8080/api/senpiper/register-user` with the required body parameters.

2. **Log In:**

    Send a `POST` request to `http://localhost:8080/api/senpiper/auth/login` to receive the JWT token anmd pass this token in header for authentication.

3. **Get List of Training center:**

    Send a `GET` request to `http://localhost:8080/api/senpiper/training-centers` with the some parameter for sorting and filtering and jwt token in headers.

4. **Add New Traing Center:**

    Send a `POST` request to `http://localhost:8080/api/senpiper/register-training-center` with the Training center details in the request body and jwt token in headers.
   
## Security and Reliability

To ensure the security and reliability of the APIs:

- **Authentication:** Use JWT tokens for secure access.
- **Data Validation:** Validate all input data to prevent invalid or harmful data from being processed.
- **Error Handling:** Implement proper error handling to provide meaningful error messages.
- **Logging:** Use logging to track application behavior and debug issues.
- **Rate Limiting:** Consider implementing rate limiting to prevent abuse of the APIs.


## Contributing

Feel free to submit issues or pull requests. Please make sure your contributions adhere to the project's coding standards.

Replace placeholders such as `your-username`, `your-repository`, `your_database_name`, `your_database_username`, and `your_database_password` with your actual values.
