# Selenium Java Automation Framework

A scalable UI automation framework built using **Selenium WebDriver, Java, TestNG, Maven, Page Object Model, Allure, Log4j2, and GitHub Actions**.
The framework demonstrates maintainable test design, browser abstraction, data-driven testing, parallel execution, retry handling, failure evidence capture, reporting, and CI execution.

## Tech Stack

| Technology         | Purpose                         |
| ------------------ | ------------------------------- |
| Java               | Programming language            |
| Selenium WebDriver | Web UI automation               |
| TestNG             | Test execution and assertions   |
| Maven              | Dependency and build management |
| Page Object Model  | Test design pattern             |
| Allure             | Test reporting                  |
| Log4j2 / SLF4J     | Logging                         |
| GitHub Actions     | CI execution                    |

## Framework Architecture

```text
                    GitHub Actions
                          |
                        Maven
                          |
                     testng.xml
                          |
                       BaseTest
                  /                 \
        DriverFactory               Tests
             |                        |
       WebDriver                Page Objects
      /        \                     |
   Chrome     Firefox           ElementUtils
                                   |
                                WaitUtils

Tests
  |
  +-- DataProvider
  +-- RetryAnalyzer
  +-- TestListener
          |
          +-- Screenshots
          +-- Logs
          +-- Allure Results
```

### Execution Flow

```text
TestNG
  ↓
BaseTest @BeforeMethod
  ↓
DriverFactory
  ↓
Create WebDriver
  ↓
Open Application
  ↓
Test Method
  ↓
Page Object
  ↓
ElementUtils
  ↓
WaitUtils
  ↓
Selenium WebDriver
  ↓
Browser
  ↓
Assertions
  ↓
Listener / Allure / Logs
  ↓
BaseTest @AfterMethod
  ↓
Driver Cleanup
```

## Project Structure

```text
project/
│
├── .github/
│   └── workflows/
│       └── automation.yml
│
├── src/
│   ├── main/
│   │   └── java/
│   │       ├── factory/
│   │       │   └── DriverFactory.java
│   │       │
│   │       ├── pages/
│   │       │   ├── LoginPage.java
│   │       │   ├── ProductsPage.java
│   │       │   ├── CartPage.java
│   │       │   ├── CheckoutPage.java
│   │       │   ├── CheckoutOverviewPage.java
│   │       │   └── OrderConfirmationPage.java
│   │       │
│   │       └── utils/
│   │           ├── ElementUtils.java
│   │           ├── WaitUtils.java
│   │           └── ScreenshotUtils.java
│   │
│   └── test/
│       ├── java/
│       │   ├── base/
│       │   │   └── BaseTest.java
│       │   ├── data/
│       │   │   ├── LoginDataProvider.java
│       │   │   └── CheckoutDataProvider.java
│       │   ├── listeners/
│       │   │   └── TestListener.java
│       │   ├── retry/
│       │   │   └── RetryAnalyzer.java
│       │   └── tests/
│       │       ├── SauceDemoTest.java
│       │       ├── LoginErrorTest.java
│       │       ├── CartTest.java
│       │       └── CheckoutValidationTest.java
│       │
│       └── resources/
│           ├── config.properties
│           ├── testdata.properties
│           └── log4j2.xml
│
├── pom.xml
├── testng.xml
└── README.md
```

## Key Framework Features

* Page Object Model for separation of test logic and UI interactions
* DriverFactory for centralized browser creation
* BaseTest for WebDriver lifecycle management
* Chrome and Firefox support
* Headed execution locally and headless execution in CI
* Explicit wait strategy using WebDriverWait
* Reusable ElementUtils abstraction
* Data-driven testing using TestNG DataProvider
* Test grouping using smoke and regression groups
* Parallel execution at test-class level
* Retry mechanism for failed test executions
* Automatic screenshot capture on failure
* Structured application and framework logging
* Allure reporting
* Maven-based execution
* GitHub Actions CI integration
* CI artifact preservation for debugging failed executions

## Test Coverage

The framework currently covers major SauceDemo user flows including:

**Authentication**

* Valid login
* Invalid credentials
* Locked user validation

**Cart**

* Add product to cart
* Remove product from cart
* Validate cart count
* Validate cart persistence after navigation

**Checkout**

* Mandatory field validation
* Customer information submission
* Product verification
* Order price calculation
* Tax and final total validation

**End-to-End Purchase**

* Login
* Product selection
* Cart
* Checkout
* Order overview
* Order completion
* Confirmation validation

## Browser Management

Browser creation is centralized inside `DriverFactory`.

`BaseTest` owns the WebDriver instance and passes it to Page Objects using constructor injection.

```java
LoginPage loginPage = new LoginPage(driver);
ProductsPage productsPage = new ProductsPage(driver);
```

This keeps Page Objects independent from browser creation and lifecycle management.

## Local Execution

Run the complete TestNG suite:

```bash
mvn clean test
```

Chrome runs in headed mode locally by default.

Run using a specific browser:

```bash
mvn clean test -Dbrowser=chrome
```

## Headless Execution

Headless mode can be explicitly enabled:

```bash
mvn clean test -Dbrowser=chrome -Dheadless=true
```

This is used by the CI pipeline.

## Parallel Execution

Parallel execution is configured through `testng.xml` at the **class level**.

```xml
<suite name="Automation Suite"
       parallel="classes"
       thread-count="3">
```

Each test class manages its own WebDriver lifecycle through `BaseTest`, preventing test methods within the same class from competing for the same driver field.

## Data-Driven Testing

TestNG `DataProvider` is used to execute the same test scenario against multiple datasets.

Example use cases include:

* Invalid login combinations
* Checkout mandatory-field validation

This separates test data from test execution logic.

## Retry Handling

`RetryAnalyzer` provides controlled retry support for failed TestNG executions.

Retries are intentionally kept limited so that genuine application failures are not hidden by excessive reruns.

## Failure Handling

When a test fails, the framework captures debugging evidence through the TestNG listener.

Failure evidence can include:

* Screenshot
* Test logs
* Allure result
* TestNG/Surefire report

This information is also preserved as CI artifacts.

## Reporting

Allure is integrated for test execution reporting.

Generate the report after execution:

```bash
allure serve allure-results
```

The report provides test status, execution details, failures, attachments, and screenshots.

## CI/CD

GitHub Actions automatically executes the Selenium suite.

The pipeline performs:

```text
Checkout repository
        ↓
Configure Java
        ↓
Restore Maven dependencies
        ↓
Execute Selenium tests
        ↓
Generate test results
        ↓
Upload reports / screenshots / logs
```

CI execution uses:

```bash
mvn clean test -Dbrowser=chrome -Dheadless=true
```

Test artifacts are uploaded even when test execution fails, making CI failures easier to investigate.

## Design Principles

The framework follows clear responsibility boundaries:

**DriverFactory**
Creates browser instances.

**BaseTest**
Controls browser setup and cleanup.

**Page Objects**
Contain page locators and business-level UI actions.

**ElementUtils**
Provides reusable element interactions.

**WaitUtils**
Centralizes explicit synchronization.

**Tests**
Contain test flow and assertions.

**DataProvider**
Supplies reusable test datasets.

**Listener**
Handles test lifecycle events and failure evidence.

**RetryAnalyzer**
Handles controlled reruns.

## Application Under Test

The automation suite uses SauceDemo as the demonstration application.

The framework focuses on framework architecture and engineering practices rather than simply automating a large number of test cases.

## Future Enhancements

Potential extensions include:

* Cloud execution using LambdaTest or BrowserStack
* Cross-browser matrix execution
* Environment-specific configuration
* Dockerized test execution
* API + UI integrated scenarios
* Visual regression testing

## Key Engineering Decisions

The framework intentionally favors simple, maintainable abstractions over unnecessary complexity.

WebDriver creation is separated from WebDriver lifecycle management, Page Objects receive dependencies through constructor injection, synchronization is centralized, tests remain focused on business behavior, and parallelism is configured at a level compatible with the framework's driver ownership model.
