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
- Dynamic test data generation
- Local and Docker execution
- Selenium Grid support
- Multi-environment configuration
- Local cross-browser execution (Chrome, Firefox and Edge)
- Allure reporting
- GitHub Actions CI pipeline
- PowerShell and Bash helper scripts

---

## Technologies

| Technology | Purpose |
|------------|---------|
| Java | Programming language |
| Maven | Dependency management and test execution |
| Selenium WebDriver | Browser automation |
| TestNG | Test framework |
| Docker & Docker Compose | Dockerized test environment |
| Allure Report | Test reporting |
| GitHub Actions | Continuous Integration |
| PowerShell / Bash | Helper scripts |

---

## Project Structure

```text
project/
├── docker/              Docker environment and configuration
├── scripts/             Helper scripts
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

## Implemented Test Scenarios

The framework currently automates the following user workflows:

- User authentication
- User registration
- Product filtering
- Product sorting
- Shopping cart management
- Checkout process

---

## Running Tests

### Requirements

#### Required

- Java 21+
- Maven
- Git

#### Optional

- Docker Desktop / Docker Engine (required for Docker execution)
- Allure CLI (required only to view test reports)

### Clone the repository

```bash
git clone https://github.com/bszczerba13/projekt_java.git
cd projekt_java
```

### Local execution

Run all tests using the default local configuration:

```bash
mvn clean test
```

### Helper scripts (recommended)

The helper scripts provide the easiest way to run the framework. They automatically:

- start and stop the Docker environment,
- collect execution logs,
- display Allure report instructions.

**Windows**

```powershell
.\scripts\run-tests.ps1
```

**Linux / macOS**

```bash
./scripts/run-tests.sh
```

### Docker execution

Start the required Docker services:

```bash
docker compose -f docker/docker-compose.yml up -d
```

Run the test suite against the Docker environment:

```bash
mvn clean test -Denvironment=docker
```

Stop the Docker services:

```bash
docker compose -f docker/docker-compose.yml down
```

---

## Allure Report

To view the generated report:

```bash
allure serve allure-results
```

or

```bash
allure generate allure-results --clean
allure open allure-report
```

If Allure CLI is not installed, the provided helper scripts will display an appropriate message.

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

### Headless execution

Local execution uses a visible browser.

The CI pipeline automatically runs tests in headless mode by passing:

```text
-Dheadless=true
```

---

## Logs

When using the provided helper scripts, execution logs are automatically saved to the `logs` directory.

- `docker-up.log`
- `docker-down.log`
- `test-run.log`

---

## Test Data

The framework uses both static and dynamically generated test data.

- `users.csv` for predefined login credentials.
- Java Faker and custom utilities for dynamically generated registration data.