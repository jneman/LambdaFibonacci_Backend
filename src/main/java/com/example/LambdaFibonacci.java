package com.example;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import java.util.HashMap;
import java.util.Map;

public class LambdaFibonacci implements RequestHandler<Map<String, Object>, Map<String, Object>> {
	
	private static final String ERROR_MSG = "Please enter a number between 1 and 47.";

    @Override
    public Map<String, Object> handleRequest(Map<String, Object> input, Context context) {
    	
    	if (input == null || input.get("body") == null) {
    		return createErrorResponse(ERROR_MSG, 400);
    	}
    	
    	// 1. Extract and Validate 'n'
    	int n = 10;
	    try {
	        n = Integer.parseInt(input.get("body").toString().trim());
	        
	        // Validation Check
	        if (n <= 0 || n >= 48) {
	            return createErrorResponse(ERROR_MSG, 400);
	        }
	    } catch (NumberFormatException e) {
	        return createErrorResponse("Invalid input. Please provide a whole number.", 400);
	    }

        // Safely check the input map and the body key


        StringBuilder sequence = new StringBuilder();
        long a = 0, b = 1;
        for (int i = 0; i < n; i++) {
            sequence.append(a);
            if (i < n - 1) sequence.append(", ");
            long temp = a + b;
            a = b;
            b = temp;
        }

        // Initialize the response map and headers to prevent NullPointer
        Map<String, Object> response = new HashMap<>();
        Map<String, String> headers = new HashMap<>();
        
        headers.put("Content-Type", "text/plain");
        headers.put("Access-Control-Allow-Origin", "*"); 
        headers.put("Access-Control-Allow-Methods", "POST, OPTIONS");
        
        response.put("statusCode", 200);
        response.put("headers", headers);
        response.put("body", sequence.toString());

        return response;
    }
    
    private Map<String, Object> createErrorResponse(String message, int statusCode) {
        Map<String, Object> response = new HashMap<>();
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "text/plain");
        headers.put("Access-Control-Allow-Origin", "*"); // Keep CORS for errors!

        response.put("statusCode", statusCode);
        response.put("headers", headers);
        response.put("body", message);
        return response;
    }

    // Main for local testing
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