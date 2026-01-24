# 💰 HisaabKitaab Backend

<div align="center">
  A robust Java-based backend powering an intuitive expense tracker application.
  
</div>

## 📖 Overview

HisaabKitaab is the backend service for a personal expense tracker application, designed to help users manage their finances effectively. This repository provides a powerful and scalable API built with Java, offering functionalities to record, categorize, and analyze expenses. It serves as the core data engine, enabling a frontend application (developed separately) to interact with user and expense data seamlessly.

## ✨ Features

-   🎯 **Expense Management**: Create, read, update, and delete expense records.
-   🏷️ **Category Management**: Organize expenses into custom categories for better tracking.
-   👤 **User Authentication & Authorization**: Secure user accounts with robust login and access control mechanisms.
-   📊 **Expense Summaries**: Retrieve aggregated expense data for reports and insights.
-   🔒 **Data Persistence**: Reliable storage of all user and expense information in a relational database.
-   ⚡ **RESTful API**: Clean and well-structured API endpoints for easy integration with any frontend client.

## 🛠️ Tech Stack

**Backend:**

![Java](https://img.shields.io/badge/Java-007396?style=for-the-badge&logo=java&logoColor=white)

![Spring Boot](https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=spring&logoColor=white)

![Maven](https://img.shields.io/badge/Maven-C71A36?style=for-the-badge&logo=apache-maven&logoColor=white)

![MySQL](https://img.shields.io/badge/MySQL-316192?style=for-the-badge&logo=mysql&logoColor=white)

![H2 Database](https://img.shields.io/badge/H2_Database-4479A1?style=for-the-badge&logo=h2&logoColor=white)

**Frontend:**
_To be developed in a separate repository, or integrated into this project's static assets._

**DevOps:**
_No specific CI/CD or containerization configurations detected yet._

## 🚀 Quick Start

Follow these steps to get the HisaabKitaab backend up and running on your local machine.

### Prerequisites
-   **Java Development Kit (JDK)**: Version 17 or higher (e.g., OpenJDK 17).
-   **Maven**: Version 3.8.x or higher.
-   **PostgreSQL**: A running instance of PostgreSQL for production data storage.
-   **_Optional:_** Your preferred IDE (IntelliJ IDEA, VS Code with Java extensions, Eclipse).

### Installation

1.  **Clone the repository**
    ```bash
    git clone https://github.com/mjunaid6/hisaabKitaab.git
    cd hisaabKitaab/backend
    ```

2.  **Build the project**
    Navigate into the `backend` directory and build the project using Maven:
    ```bash
    mvn clean install
    ```

3.  **Environment setup**
    Create an `application.properties` or `application.yml` file within `src/main/resources` (or a `.env` file at the root, depending on configuration strategy) to configure your database connection and other settings.

    _Example `application.properties`:_
    ```properties
    # Server configuration
    server.port=8080

    # Database configuration (PostgreSQL example)
    spring.datasource.url=jdbc:postgresql://localhost:5432/hisaab_kitaab_db
    spring.datasource.username=your_db_user
    spring.datasource.password=your_db_password
    spring.datasource.driver-class-name=org.postgresql.Driver

    # JPA/Hibernate configuration
    spring.jpa.hibernate.ddl-auto=update # Use 'validate' or 'none' for production
    spring.jpa.show-sql=true
    spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect

    # H2 Database (for development/testing - remove or comment out for production)
    # spring.h2.console.enabled=true
    # spring.h2.console.path=/h2-console
    # spring.datasource.url=jdbc:h2:mem:testdb
    # spring.datasource.driver-class-name=org.h2.Driver
    # spring.datasource.username=sa
    # spring.datasource.password=
    ```
    Configure your environment variables for:
    -   `SPRING_DATASOURCE_URL`: PostgreSQL connection string.
    -   `SPRING_DATASOURCE_USERNAME`: PostgreSQL username.
    -   `SPRING_DATASOURCE_PASSWORD`: PostgreSQL password.
    -   `SERVER_PORT`: (Optional) Port for the application to run on, default is 8080.

4.  **Database setup**
    Ensure your PostgreSQL database is running. The application will attempt to connect and perform schema updates (`ddl-auto=update`) on startup if configured.
    It is recommended to create the database manually first:
    ```sql
    CREATE DATABASE hisaab_kitaab_db;
    ```

5.  **Start development server**
    You can run the application directly using Maven Spring Boot plugin:
    ```bash
    mvn spring-boot:run
    ```
    Alternatively, if you've built a JAR file, you can run it:
    ```bash
    java -jar target/hisaabKitaab-0.0.1-SNAPSHOT.jar # Adjust version as needed
    ```

6.  **Access the API**
    The API will be available at `http://localhost:[detected port, default 8080]`.

## 📁 Project Structure

```
hisaabKitaab/
├── backend/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/              # Main Java source code (e.g., controllers, services, entities, repositories)
│   │   │   │   └── com/
│   │   │   │       └── mjunaid6/
│   │   │   │           └── hisaabkitaab/
│   │   │   │               ├── config/      # Application configurations
│   │   │   │               ├── controller/  # REST API endpoints
│   │   │   │               ├── model/       # Data models (entities)
│   │   │   │               ├── repository/  # Data access layer
│   │   │   │               ├── service/     # Business logic
│   │   │   │               └── HisaabKitaabApplication.java # Main application entry point
│   │   │   └── resources/         # Configuration files (application.properties/yml), static assets
│   │   │       ├── application.properties # Main application configuration
│   │   │       └── data.sql       # Optional: Initial data for H2/testing
│   │   └── test/
│   │       └── java/              # Test source code
│   │           └── com/
│   │               └── mjunaid6/
│   │                   └── hisaabkitaab/
│   │                       └── HisaabKitaabApplicationTests.java # Spring Boot test
│   ├── .gitignore             # Git ignore rules
│   └── pom.xml                # Maven project object model (dependencies, build configuration)
└── README.md                  # This README file
```

## ⚙️ Configuration

### Environment Variables
The application relies on environment variables or `application.properties`/`application.yml` for sensitive information and runtime settings.

| Variable                  | Description                               | Default       | Required |

|---------------------------|-------------------------------------------|---------------|----------|

| `SERVER_PORT`             | Port the Spring Boot application will run on. | `8080`        | No       |

| `SPRING_DATASOURCE_URL`   | JDBC URL for the database connection.     | `jdbc:h2:mem:testdb` (for H2) | Yes      |

| `SPRING_DATASOURCE_USERNAME`| Username for the database connection.     | `sa` (for H2) | Yes      |

| `SPRING_DATASOURCE_PASSWORD`| Password for the database connection.     | `(empty)`     | Yes      |

### Configuration Files
-   `backend/src/main/resources/application.properties`: Primary configuration file for Spring Boot, used for database settings, server port, logging, etc.

## 🔧 Development

### Available Scripts
The `pom.xml` defines standard Maven lifecycle goals.

| Command                     | Description                             |

|-----------------------------|-----------------------------------------|

| `mvn clean`                 | Cleans the build directory.             |

| `mvn install`               | Compiles, tests, and packages the project into a JAR. |

| `mvn spring-boot:run`       | Runs the Spring Boot application locally. |

| `mvn test`                  | Runs all unit and integration tests.    |

| `mvn package`               | Builds the JAR package in the `target/` directory. |

### Development Workflow
1.  Ensure all prerequisites are installed.
2.  Clone the repository and navigate to the `backend` directory.
3.  Configure your `application.properties` with local database settings.
4.  Run `mvn spring-boot:run` to start the development server.
5.  Use a tool like Postman, Insomnia, or a local frontend application to interact with the API endpoints.

## 🧪 Testing

The project uses JUnit and Spring Boot Test for unit and integration testing.

```bash

# Run all tests
mvn test

# Run a specific test class
mvn test -Dtest=com.mjunaid6.hisaabkitaab.service.ExpenseServiceTests
```

## 🚀 Deployment

### Production Build
To create a production-ready JAR file, use the Maven `package` goal:
```bash
cd backend
mvn clean package
```
This will generate an executable JAR file in the `backend/target/` directory (e.g., `hisaabKitaab-0.0.1-SNAPSHOT.jar`).

### Deployment Options
-   **Direct Execution**: The generated JAR is an executable fat JAR. You can run it on any server with a compatible JVM:
    ```bash
    java -jar hisaabKitaab-0.0.1-SNAPSHOT.jar
    ```
-   **Docker**: For containerized deployments, a `Dockerfile` would typically be provided.
-   **Cloud Platforms**: Deploy to platforms like AWS Elastic Beanstalk, Google Cloud App Engine, Heroku, or Azure App Service.

## 📚 API Reference

The API follows a RESTful design. All endpoints are prefixed with `/api`.

### Authentication
User authentication is handled via standard login endpoints, likely returning a JWT or session token for subsequent requests.

-   **`POST /api/auth/register`**: Register a new user.
-   **`POST /api/auth/login`**: Authenticate a user and receive a token.

### Endpoints

#### User Endpoints
-   **`GET /api/users/{id}`**: Get user profile details (requires authentication).
-   **`PUT /api/users/{id}`**: Update user profile (requires authentication).

#### Expense Endpoints
-   **`GET /api/expenses`**: Retrieve all expenses for the authenticated user.
    -   _Parameters_: `?categoryId=X`, `?startDate=YYYY-MM-DD`, `?endDate=YYYY-MM-DD` (inferred)
-   **`GET /api/expenses/{id}`**: Get a specific expense by ID.
-   **`POST /api/expenses`**: Add a new expense.
-   **`PUT /api/expenses/{id}`**: Update an existing expense.
-   **`DELETE /api/expenses/{id}`**: Delete an expense.

#### Category Endpoints
-   **`GET /api/categories`**: Retrieve all expense categories for the authenticated user.
-   **`POST /api/categories`**: Create a new category.
-   **`PUT /api/categories/{id}`**: Update an existing category.
-   **`DELETE /api/categories/{id}`**: Delete a category.

## 🤝 Contributing

We welcome contributions! Please see our [Contributing Guide](CONTRIBUTING.md) for details. <!-- TODO: Create a CONTRIBUTING.md -->

### Development Setup for Contributors
The development setup is as described in the [Quick Start](#🚀-quick-start) section. Ensure you have the JDK and Maven installed, and set up your local PostgreSQL database.

## 🙏 Acknowledgments

-   **Spring Boot**: For simplifying Java application development.
-   **Maven**: For robust project build management.
-   **PostgreSQL**: For powerful and reliable data storage.
-   **mjunaid6**: The original author and maintainer.

## 📞 Support & Contact

-   🐛 Issues: [GitHub Issues](https://github.com/mjunaid6/hisaabKitaab/issues)
-   💬 Discussions: [GitHub Discussions](https://github.com/mjunaid6/hisaabKitaab/discussions) <!-- TODO: Enable GitHub Discussions if desired -->

---

<div align="center">

**⭐ Star this repo if you find it helpful!**

Made with ❤️ by [mjunaid6]

</div>

