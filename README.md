# Humming Records

## Overview

Humming Records is a mock e-commerce (EC) site dealing with CDs. It provides functions such as new member registration, login, product purchase, and order history browsing. This is the very first application I built for learning Java and Spring Boot. Initially created using Maven, I later migrated the project to Gradle.

![Screenshot](https://user-images.githubusercontent.com/44774033/64426848-a58e4580-d0ea-11e9-937e-c9e92e506ef3.png)

> **Note:** This application was originally deployed on **Heroku**, but since Heroku's free plan is no longer available, it is now only intended for local development.

## Running Locally

### Prerequisites

- **Java 8** (or later)
- **Gradle**  
  If you don't have Gradle installed, use the included Gradle Wrapper (`gradlew`/`gradlew.bat`).
- **PostgreSQL** installed and running locally
- **psql** command-line tool (optional, for managing your PostgreSQL database)

### Setting Up PostgreSQL

#### Installing PostgreSQL

- **Ubuntu/Debian:**
  ```bash
  sudo apt-get update
  sudo apt-get install postgresql postgresql-contrib
  ```

- **macOS (using Homebrew):**
  ```bash
  brew install postgresql
  brew services start postgresql
  ```

- **Windows:**  
  Download and install PostgreSQL from [PostgreSQL.org](https://www.postgresql.org/download/).

#### Creating the Database

1. Open a terminal and run:

  ```bash
  sudo -u postgres createdb humming
  ```

2. Alternatively, log in as the postgres user:

  ```bash
  sudo -u postgres psql
  CREATE DATABASE Humming;
  ```

#### Configuring Database Credentials

Edit the file src/main/resources/application.yml with your PostgreSQL connection details:

```yaml
spring:
  datasource:
    driverClassName: org.postgresql.Driver
    url: jdbc:postgresql://localhost:5432/Humming
    username: your_username
    password: your_password
```

### Running the Application

Use the Gradle Wrapper to build and run the application locally:

```bash
./gradlew bootRun
```

The application will start on http://localhost:8080.

### Running Tests

To run the automated tests, execute:

```bash
./gradlew test
```

## Environments

- Programming Languages: Java 8, JavaScript
- Framework: Spring Boot 2.7.12 (migrated from Spring Boot 1.5.4)
- Build Tool: Gradle (migrated from Maven)
- Database: PostgreSQL
- Libraries: Bootstrap, jQuery
- Template Engine: JSP

## Project Structure

```
humming-records/
├── src/
│   ├── main/
│   │   ├── java/                 # Java source files
│   │   └── resources/
│   │       ├── application.yml
│   │       └── sql/
│   │           ├── ddl.sql       # DDL for initializing database schema
│   │           └── dml.sql       # DML for populating initial data
│   └── test/
│       └── java/                 # Test source files
├── build.gradle                  # Gradle build file
├── gradlew                       # Gradle Wrapper (Linux/Mac)
├── gradlew.bat                   # Gradle Wrapper (Windows)
└── README.md                     # This file
```
 