package com.example;

import java.util.HashMap;
import java.util.Map;

public class TestLambdaFibonacci {
    public static void main(String[] args) {
        LambdaFibonacci lambda = new LambdaFibonacci();

        // 1. Simulate the Proxy Integration structure
        // API Gateway puts the raw request content into a key called "body"
        Map<String, Object> inputMap = new HashMap<>();
        inputMap.put("body", "10"); 

        // 2. Call the handler
        // The handler now returns Map<String, Object> to satisfy the Proxy format
        Map<String, Object> responseMap = lambda.handleRequest(inputMap, null);

        // 3. Extract the result from the "body" of the response
        // This is exactly what the internet user will see
        System.out.println("Status Code: " + responseMap.get("statusCode"));
        System.out.println("Fibonacci sequence: " + responseMap.get("body"));
    }
}
