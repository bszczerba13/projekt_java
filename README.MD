# Selenium Test Automation Framework

## About

This project was originally created as part of a postgraduate software
testing course and gradually evolved into a complete UI test automation
framework.

It demonstrates how to build a maintainable automation framework using
Selenium WebDriver and TestNG, with support for Docker, Allure
reporting, multiple execution environments and data-driven testing.

The project focuses on clean architecture, readability and ease of use
rather than demonstrating as many technologies as possible.

------------------------------------------------------------------------

## Features

-   Page Object Model (POM)
-   Reusable page components
-   Data-driven testing using CSV files
-   Dynamic test data generation
-   Local and Docker execution
-   Selenium Grid support
-   Multi-environment configuration
-   Local cross-browser execution (Chrome, Firefox and Edge)
-   Docker cross-browser execution (Chrome, Firefox and Edge)
-   Automatic test retry (one retry per failed test)
-   Allure reporting
-   GitHub Actions CI pipeline
-   PowerShell and Bash helper scripts

------------------------------------------------------------------------

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

------------------------------------------------------------------------

## Project Structure

``` text
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

------------------------------------------------------------------------

## Implemented Test Scenarios

The framework currently automates the following user workflows:

-   User authentication
-   User registration
-   Product filtering
-   Product sorting
-   Shopping cart management
-   Checkout process

------------------------------------------------------------------------

## Installation

Clone the repository:

    git clone https://github.com/bszczerba13/projekt_java.git

Go to the project directory:

    cd projekt_java

---

## Running Tests

The framework supports three execution methods:

1. Local execution
2. Docker Compose
3. Helper scripts

Chrome is used as the default browser. Firefox and Edge can be selected when needed.

---

### 1. Local execution

Run the tests locally with Maven:

    mvn clean test

#### Browser selection

Chrome is used by default. To run tests with another browser, use the `browser` Maven property.

| Browser | Command |
|---|---|
| Chrome | `mvn clean test` |
| Firefox | `mvn clean test -Dbrowser=firefox` |
| Edge | `mvn clean test -Dbrowser=edge` |

Headless execution can be enabled with the `headless` Maven property:

    mvn clean test -Dheadless=true

---

### 2. Docker Compose

#### Prerequisites

- Docker Desktop (Windows) or Docker Engine (Linux / macOS)
- Docker Compose

Docker Desktop / Docker Engine must be running before starting the Docker environment.

#### Run tests

Chrome is used by default. Firefox and Edge can be selected with the `browser` Maven property.

| Browser | Command |
|---|---|
| Chrome | `docker compose -f docker/docker-compose.yml up -d`<br>`mvn clean test` |
| Firefox | `docker compose -f docker/docker-compose.yml up -d`<br>`mvn clean test -Dbrowser=firefox` |
| Edge | `docker compose -f docker/docker-compose.yml up -d`<br>`mvn clean test -Dbrowser=edge` |

After the execution completes, stop and remove all containers:

    docker compose -f docker/docker-compose.yml down

---

### 3. Helper scripts (Recommended)

The helper scripts provide the easiest way to run the complete Docker test workflow.

They automatically:

- Start the Docker environment
- Execute the complete test suite
- Generate Allure results
- Stop the Docker environment
- Save logs for every execution step

Chrome is used by default. Firefox and Edge can be selected using the commands below.

| Browser | Windows | Linux / macOS |
|---|---|---|
| Chrome | `.\scripts\run-tests.ps1` | `./scripts/run-tests.sh` |
| Firefox | `.\scripts\run-tests.ps1 -Dbrowser=firefox` | `./scripts/run-tests.sh -Dbrowser=firefox` |
| Edge | `.\scripts\run-tests.ps1 -Dbrowser=edge` | `./scripts/run-tests.sh -Dbrowser=edge` |

Headless execution can be enabled by adding `-Dheadless=true`.

---

## Allure Report

To view the generated report:

``` bash
allure serve allure-results
```

or

``` bash
allure generate allure-results --clean
allure open allure-report
```

If Allure CLI is not installed, the provided helper scripts will display
an appropriate message.

------------------------------------------------------------------------

## Logs

When using the provided helper scripts, execution logs are automatically
saved to the `logs` directory.

-   `docker-up.log`
-   `docker-down.log`
-   `test-run.log`

------------------------------------------------------------------------

## Test Data

The framework uses both static and dynamically generated test data.

-   `users.csv` for predefined login credentials.
-   Java Faker and custom utilities for dynamically generated
    registration data.
