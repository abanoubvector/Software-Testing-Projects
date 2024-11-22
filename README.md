# Software Testing Projects

Welcome to the **Software Testing Projects** repository! 🎉

This repository contains two important projects related to **Software Testing**:

- **SauceDemo Automation Testing**: Automated testing of the **SauceDemo** website using **Selenium WebDriver** and **TestNG**.
- **DummyJSON API Testing**: API testing for **DummyJSON** using a **Postman** collection.

These projects are neatly organized and easy to use. Below you'll find detailed instructions for setting up and running both projects.

---

## 📂 Project Overview

### 1. **SauceDemo Automation Testing (Selenium with TestNG)**

The **SauceDemo Automation Testing** project automates common user flows on the **SauceDemo** website. The project utilizes **Selenium WebDriver** for browser automation and **TestNG** for managing the tests.

#### Key Features:
- **Selenium WebDriver** for automating browser interactions (e.g., login, product selection, and checkout).
- **TestNG** for managing tests, parallel execution, and generating rich reports.
- **Cross-browser support**: Test on multiple browsers such as **Chrome** and **Firefox**.

#### How to Use:
1. **Navigate to the Project Folder**:
   - Open the `sauce-demo-automation/` folder in your IDE.

2. **Set Up the Project**:
   - Open the project in **IntelliJ IDEA** or **Eclipse**.
   - Ensure **Java**, **Maven**, and **TestNG** are installed on your machine.
   - Run `mvn install` to download and install required dependencies.

3. **Run the Tests**:
   - In your IDE, run the tests using **TestNG**.
   - Ensure the appropriate **WebDriver** (e.g., **ChromeDriver**) is installed.

4. **View Test Reports**:
   - After running the tests, **TestNG** will generate detailed reports to view test results.

For additional setup instructions, refer to the `README.md` in the `sauce-demo-automation/` folder.

---

### 2. **DummyJSON API Testing (Postman Collection)**

The **Postman collection** for **DummyJSON API Testing** includes tests for various API endpoints, such as authentication, product retrieval, and cart management. It validates the API responses and ensures correct status codes and data integrity.

#### Key Features:
- **Authentication**: Login, session refresh, and user verification.
- **Product Retrieval**: Fetch all products, retrieve individual products, and perform searches.
- **Cart Management**: Add products to the cart and perform checkout.
- **Response Validation**: Validates correct status codes and ensures data consistency.

#### How to Use:
1. **Import the Collection into Postman**:
   - Open **Postman** and click **Import**.
   - Choose the `DummyJSON_API_Testing.postman_collection.json` file and import it.

2. **Set Up Environment Variables**:
   - Set the following environment variables in **Postman**:
     - `base_url`: `https://dummyjson.com`
     - `username`: Your test username (e.g., `emilys`).
     - `password`: Your test password.
     - `accessToken`: Generated after login.
     - `refreshToken`: Generated after login.

3. **Run the Tests**:
   - After setting up the environment variables, run the collection in **Postman**.

For more details, refer to the `README.md` in the `api-testing/` folder.

---

## 📂 Folder Structure

```plaintext
software-testing-projects/
├── sauce-demo-automation/           # SauceDemo automation project using Selenium
│   ├── src/                         # Source code for Selenium tests
│   ├── pom.xml                      # Maven configuration file with dependencies
│   └── README.md                    # Instructions for the SauceDemo project
├── api-testing/                     # API Testing project with Postman
│   ├── DummyJSON_API_Testing.postman_collection.json  # Postman collection file
│   └── README.md                    # Instructions for API Testing with Postman
└── README.md                        # Main repository README
