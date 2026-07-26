# Selenium Test Automation Framework

## About

This project was originally created as part of a postgraduate software testing course and gradually evolved into a complete UI test automation framework.

It demonstrates how to build a maintainable automation framework using Selenium WebDriver and TestNG, with support for Docker, Allure reporting, multiple execution environments and data-driven testing.

The project focuses on clean architecture, readability and ease of use rather than demonstrating as many technologies as possible.

---

## Features

- Page Object Model (POM)
- Reusable page components
- Data-driven testing using CSV files
- Test data generation with Java Faker
- Local and Docker execution
- Selenium Grid support
- Multi-environment configuration
- Allure reporting
- Cross-browser execution (local: Chrome, Firefox and Edge)
- Automatic environment reporting

---

## Technologies

| Technology | Purpose |
|------------|---------|
| Java | Programming language |
| Maven | Dependency management and test execution |
| Selenium WebDriver | Browser automation |
| TestNG | Test framework |
| Docker & Docker Compose | Dockerized test environment |
| MariaDB | Application database |
| Allure Report | Test reporting |

---

## Project Structure

```text
project/
├── docker/              Docker environment and configuration
├── src/
│   ├── test/
│   │   ├── base/        Base classes and WebDriver management
│   │   ├── data/        Test data
│   │   ├── pages/       Page Object Model implementation
│   │   ├── tests/       Test classes
│   │   └── utils/       Configuration and utility classes
├── pom.xml              Maven configuration
├── testng.xml           TestNG suite configuration
└── README.md
```

---

## Covered Features

The framework currently automates the following user workflows:

- User authentication
- User registration
- Product filtering
- Product sorting
- Shopping cart management
- Checkout process

---

## Getting Started

**Requirements:** Java 21, Maven and optionally Docker Desktop for Docker execution.

Clone the repository:

```bash
git clone https://github.com/bszczerba13/projekt_java.git
cd projekt_java
```

### Local execution

Run all tests using the default local environment:

```bash
mvn clean test
```

### Docker execution

Start the required Docker services:

```bash
docker compose up -d
```

Run the test suite against the Docker environment:

```bash
mvn clean test -Denvironment=docker
```

---

## Allure Report

### Local

Generate and open the report:

```bash
allure serve allure-results
```

### Docker

Start the Allure service:

```bash
docker compose --profile report up -d
```

The report is available at:

```text
http://localhost:5050/allure-docker-service/projects/default/reports/latest/index.html
```

---

## Test Configuration

### Environment

| Environment | Command |
|------------|---------|
| Local (default) | `mvn clean test` |
| Docker | `mvn clean test -Denvironment=docker` |

### Browser

| Browser | Command |
|---------|---------|
| Chrome (default) | `mvn clean test` |
| Firefox | `mvn clean test -Dbrowser=firefox` |
| Edge | `mvn clean test -Dbrowser=edge` |

---

## Test Data

The framework uses both static and dynamically generated test data.

- `users.csv` for predefined login credentials.
- Java Faker for dynamically generated registration data.