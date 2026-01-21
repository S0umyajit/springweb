package com.example.springweb.advices;

import com.example.springweb.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiError> emplpyeeNotFoundExcep(ResourceNotFoundException exception){
        ApiError apiError=ApiError.builder()
                .status(HttpStatus.NOT_FOUND)
                .message(exception.getMessage())
                .build();
        return new ResponseEntity<>(apiError,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleInternalServerError(Exception exception){
        ApiError error=ApiError.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .message(exception.getMessage())
                .build();
        return new ResponseEntity<>(error,HttpStatus.INTERNAL_SERVER_ERROR);
    }
//To bind the all the exception
@ExceptionHandler(MethodArgumentNotValidException.class)
public ResponseEntity<ApiError> handdleInputValidation(MethodArgumentNotValidException exception) {
    // 2. EXTRACTION: We need to get the simple error messages out of the complex exception object.
    List<String> errors = exception.getBindingResult() // Access the result of the validation (contains the errors)
            .getAllErrors()                            // Get the list of all validation failures
            .stream()                                  // Start a stream to process the list
            .map(error -> error.getDefaultMessage())   // For every error object, extract just the message string (e.g., "Email is invalid")
            .toList();                                 // Collect these strings into a simple List<String>

    // 3. CONSTRUCTION: Build the custom error object to return to the user.
    ApiError error = ApiError.builder()
            .status(HttpStatus.BAD_REQUEST)          // Set the HTTP code to 400 (Bad Request) inside the object
            .message("Input Validation Error")
            .subErrors(errors)                                       // Convert the List ["Error1", "Error2"] into a single String "[Error1, Error2]"
            .build();
    // 4. RESPONSE: Wrap the error object in a ResponseEntity so Spring knows what Status Code to send over the network.
    return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
}
}
