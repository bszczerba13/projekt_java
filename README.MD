# Selenium UI Automation Framework (Java)

Test automation framework for an e-commerce demo application built with Java, Selenium WebDriver and TestNG using Page Object Model (POM).

---

## Tech Stack

- Java
- Selenium WebDriver
- TestNG
- Maven
- PageFactory
- Faker
- Extent Reports
- CSV DataProvider

---

## Project Structure

```text
.
├── src
│   ├── main
│   │   └── resources
│   │
│   └── test
│       ├── java
│       │   └── com
│       │       └── practicesoftwaretesting
│       │           ├── base
│       │           │   ├── BaseTest.java
│       │           │   └── DriverFactory.java
│       │           │
│       │           ├── data
│       │           │   ├── DataGenerator.java
│       │           │   ├── LoginDataProvider.java
│       │           │   ├── RegistrationData.java
│       │           │   ├── OrderData.java
│       │           │   └── InvalidLoginData.java
│       │           │
│       │           ├── pages
│       │           │   ├── components
│       │           │   │   ├── FilterComponent.java
│       │           │   │   └── HeaderComponent.java
│       │           │   │
│       │           │   ├── AccountPage.java
│       │           │   ├── BasePage.java
│       │           │   ├── CartPage.java
│       │           │   ├── CheckoutPage.java
│       │           │   ├── HomePage.java
│       │           │   ├── LoginPage.java
│       │           │   ├── ProductPage.java
│       │           │   └── RegistrationPage.java
│       │           │
│       │           ├── tests
│       │           │   ├── CartTest.java
│       │           │   ├── CheckoutTest.java
│       │           │   ├── FilteringTest.java
│       │           │   ├── LoginTest.java
│       │           │   ├── RegistrationTest.java
│       │           │   └── SortingTest.java
│       │           │
│       │           └── utils
│       │               ├── Constants.java
│       │               ├── CsvReader.java
│       │               ├── ReportManager.java
│       │               └── TestListener.java
│       │
│       └── resources
│           └── users.csv
│
├── reports
├── testng.xml
├── pom.xml
└── README.MD
```

---

## Test Coverage

Implemented automated UI tests for:

- Login (positive and negative scenarios)
- User registration (positive and negative scenarios)
- Product sorting
- Product filtering
- Shopping cart operations
- Guest checkout flow

---

## Framework Features

- Page Object Model (POM)
- Page Components pattern (Header and Filter components)
- DriverFactory for browser management
- BasePage with reusable Selenium helper methods
- Dynamic test data generation using Faker
- Data-driven testing with TestNG DataProvider and CSV
- Shared project constants
- Explicit waits with Selenium ExpectedConditions
- HTML reporting with Extent Reports

---

## Installation

Clone repository:

```bash
git clone https://github.com/bszczerba13/projekt_java.git
cd projekt_java
```

---

## Running Tests

Run all tests:

```bash
mvn test
```

---

## Test Configuration

Framework supports browser and environment configuration through TestNG parameters:

```xml
<parameter name="browser" value="chrome"/>
<parameter name="baseUrl"
           value="https://practicesoftwaretesting.com"/>
```

---

## Report

After test execution report is generated automatically:

```text
reports/report.html
```

---

## Notes

This project was created for learning purposes and as part of postgraduate studies focused on test automation.