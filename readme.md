# SpringBoot Employee Management System Assignment

## Description
The Employee Management System is a Spring Boot-based application designed to manage employee records, including features such as adding, updating, and retrieving employee information. The application uses a Postgress database to store data and runs within a Dockerized environment for ease of deployment and configuration.

## Requirements
- Java 17 or higher
- Maven 3.8.1 or higher
- Docker and Docker Compose

## Developer Instructions
To run the application, follow the instructions below:

1. Clone the repository:
   ```bash
   git clone https://github.com/Yashika1410/EMS.git
   cd EMS
   ```
2. Set Up the Database
The project uses Docker Compose to initialize and manage the database. Start the database container using the following command:
    ```bash
    docker-compose up -d
    ```
3.  Run the Application
After setting up the database, you can run the Spring Boot application using Maven. Execute the following command:
    ```bash
    mvn clean spring-boot:run
    ```
4. Once the application is running, you can access the swagger at:
    http://localhost:8080/swagger-ui/index.html
Check the API documentation and test the aplication.
5. You can access database on pgAdmin using 
http://localhost:5050/browser/
Password: admin
6. Then add a new server using given below details:
    - address: postgres_container
    - port: 5432
    - database: emsDb
    - username: root
    - password: root