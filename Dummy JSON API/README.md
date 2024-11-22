# DummyJSON API Testing with Postman

Welcome to the **DummyJSON API Testing** project! 🎉

This project contains a **Postman collection** used for testing the **DummyJSON API**. It covers various endpoints such as authentication, product management, and cart operations. The tests validate API responses, status codes, and ensure data consistency.

---

## 🚀 Key Features

- **Authentication**: Tests for logging in and refreshing sessions.
- **Product Management**: Fetching all products, retrieving individual products, searching, and sorting.
- **Cart Management**: Adding and removing items from the cart, as well as completing the checkout process.
- **Response Validation**: Verifies status codes, checks for valid JSON responses, and ensures data consistency.

---

## 📋 Setup Instructions

### 1. **Import the Postman Collection**
To start testing the **DummyJSON API**, import the **Postman collection** (`DummyJSON_API_Testing.postman_collection.json`) into **Postman**.

- Open **Postman**.
- Click **Import** in the top-left corner of the app.
- Choose **File** and select the `DummyJSON_API_Testing.postman_collection.json` file from this directory.

### 2. **Set Up Environment Variables**

To ensure the collection runs smoothly, set the following **environment variables** in Postman:

- **`base_url`**: The base URL for all API requests. Set it to `https://dummyjson.com`.
- **`username`**: Your test username (e.g., `emilys`).
- **`password`**: Your test password.
- **`accessToken`**: This will be generated after a successful login.
- **`refreshToken`**: This will be generated after a successful login.

### 3. **Run the Postman Collection**

Once the collection is imported and the environment variables are set:

1. **Choose the Environment**: Select the environment you just created in Postman.
2. **Run the Collection**: Click the **Run** button to execute the tests.
3. **View Results**: Postman will display the results for each test in the collection. You can see detailed information about each request's response, status codes, and any errors.

---

## 🧪 Test Scenarios

### **Authentication**
- **Login User and Get Tokens**: Tests successful login and token generation.
- **Get Authenticated User**: Fetches the authenticated user's information and validates it.
- **Refresh Auth Session**: Verifies the refresh functionality works to renew the token.

### **Products**
- **Get All Products**: Retrieves all products and validates the response format.
- **Get Single Product**: Fetches a single product's details by ID.
- **Search Products**: Performs searches to filter products by query parameters.
- **Sort Products**: Tests sorting functionality for products based on title or other criteria.

### **Cart**
- **Get All Carts**: Retrieves all active carts for the user.
- **Get Single Cart**: Fetches a single cart and its contents, validating product IDs, quantities, and pricing.
- **Add to Cart**: Adds a new item to the cart and verifies the updated cart data.
- **Delete from Cart**: Removes an item from the cart and ensures the cart is updated correctly.

---

## 📂 Folder Structure

```plaintext
api-testing/
├── DummyJSON_API_Testing.postman_collection.json  # Postman collection file for API testing
└── README.md                                      # Instructions for using the Postman collection
