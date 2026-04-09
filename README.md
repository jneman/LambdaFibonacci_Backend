# Lambda Fibonacci Backend

GitHub Repository: https://github.com/jneman/LambdaFibonacci_Backend

## Overview

This project implements an AWS Lambda function in Java that generates a Fibonacci sequence up to a specified number of terms.

It is built to work with **API Gateway (Lambda Proxy Integration)** and returns structured HTTP responses including status codes, headers, and body content.

---

## Features

- Generates Fibonacci sequence up to `n` terms
- Input validation (**1 ≤ n ≤ 47**)
- Graceful error handling
- CORS support for frontend integration
- Includes unit tests and a local testing `main` method

---

## API Contract

### Request Format

```json
{
  "body": "10"
}
```

### Input Rules

- `body` must be a string containing a whole number
- Valid range: **1 to 47**

---

## Response Format

### Success (200)

```json
{
  "statusCode": 200,
  "headers": {
    "Content-Type": "text/plain",
    "Access-Control-Allow-Origin": "*",
    "Access-Control-Allow-Methods": "POST, OPTIONS"
  },
  "body": "0, 1, 1, 2, 3, 5, 8, 13, 21, 34"
}
```

### Error (400)

#### Invalid Range

```json
{
  "statusCode": 400,
  "body": "Please enter a number between 1 and 47."
}
```

#### Non-numeric Input

```json
{
  "statusCode": 400,
  "body": "Invalid input. Please provide a whole number."
}
```

---

## How It Works

1. Extracts the `body` field from the request
2. Parses it into an integer `n`
3. Validates the input range (1–47)
4. Iteratively builds the Fibonacci sequence
5. Returns the result as a comma-separated string

---

## Project Structure

```
.
├── README.md
├── pom.xml
└── src/
    ├── main/
    │   └── java/
    │       └── com/example/
    │           └── LambdaFibonacci.java
    └── test/
        └── java/
            └── com/example/
                └── TestLambdaFibonacci.java
```

---

## Local Testing

Run the `main` method inside `LambdaFibonacci` to test locally.

### Example Input

```
{
  "body": "10"
}
```

### Example Output

```
Status Code: 200
Fibonacci sequence: 0, 1, 1, 2, 3, 5, 8, 13, 21, 34
```

---

## Running Tests

This project includes unit tests.

### Run with Maven:

```bash
mvn test
```

---

## Test Coverage

- Proper error message is generated if the value of `n` is less than 0 or greater than 47, with a status code of **400**

---

## Deployment

### Prerequisites

- Java 8+
- AWS CLI configured
- Maven

### Steps

1. Package the project:

   ```bash
   mvn clean package
   ```

2. Upload the generated `.jar` to AWS Lambda
3. Set the handler:

   ```
   com.example.LambdaFibonacci::handleRequest
   ```

---

## Notes

- The upper limit of 47 prevents overflow using `int`
- Suitable for serverless API demos or lightweight backend services
