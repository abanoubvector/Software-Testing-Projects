# DummyJSON API Testing with Postman

Welcome to the **DummyJSON API Testing** project! 🎉

This project includes a **Postman collection** for testing the **DummyJSON API**. The collection covers several key endpoints for authentication, product management, and cart operations. It validates API responses, checks for correct status codes, and ensures the data returned is consistent.

---

## 🚀 Key Features

- **Authentication**: Tests for logging in, session refresh, and verifying user information.
- **Product Management**: Fetches all products, retrieves individual products, and searches for specific products.
- **Cart Operations**: Adds products to the cart, removes items, and handles the checkout process.
- **Response Validation**: Verifies correct status codes and ensures the API returns expected data formats.

---

## 📋 Setup Instructions

### 1. **Import the Postman Collection**
To get started, you need to import the **Postman collection** (`DummyJSON_API_Testing.postman_collection.json`) into **Postman**.

- Open **Postman**.
- Click the **Import** button in the top-left corner of the Postman interface.
- Choose **File** and select the `DummyJSON_API_Testing.postman_collection.json` file from this folder.

### 2. **Set Up Environment Variables**
To run the collection effectively, you'll need to configure environment variables in Postman:

- **`base_url`**: Set this to `https://dummyjson.com` to define the base URL for API requests.
- **`username`**: Your test username (e.g., `emilys`).
- **`password`**: Your test password.
- **`accessToken`**: This will be generated after logging in successfully.
- **`refreshToken`**: This will be generated after logging in successfully.

### 3. **Running the Tests**
Once the collection is imported and the environment variables are set:

1. **Choose the Environment**: Select the environment that contains the variables.
2. **Run the Collection**: Click the **Run** button in Postman to start executing the tests.
3. **View Results**: After running the tests, Postman will display the results, showing whether each request passed or failed, along with detailed response information.

---

## 🧪 Test Scenarios

### **Authentication**
- **Login User and Get Tokens**: Tests successful login and token generation.
- **Get Authenticated User**: Fetches and validates the authenticated user's details.
- **Refresh Session**: Tests the refresh functionality to renew the authentication token.

### **Products**
- **Get All Products**: Retrieves all products and validates the response.
- **Get Single Product**: Fetches and validates a specific product's details.
- **Search Products**: Performs a search operation to filter products by a query.
- **Sort Products**: Tests sorting products based on titles or other criteria.

### **Cart**
- **Get All Carts**: Fetches all active carts and validates the cart structure.
- **Get Single Cart**: Retrieves a specific cart and checks its contents.
- **Add to Cart**: Adds a new item to the cart and validates the update.
- **Delete from Cart**: Removes an item from the cart and verifies the cart update.

---

## 📂 Folder Structure

```plaintext
DummyJSON API Testing/
├── DummyJSON_API_Testing.postman_collection.json  # Postman collection file for testing the DummyJSON API
└── README.md                                      # Instructions for using the Postman collection
