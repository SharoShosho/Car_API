// GlobalExceptionHandler.java
package org.example.biluthyrning.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> hanteraValideringsFel(MethodArgumentNotValidException ex) {
        Map<String, String> fel = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fältNamn = ((FieldError) error).getField();
            String felMeddelande = error.getDefaultMessage();
            fel.put(fältNamn, felMeddelande);
        });
        return new ResponseEntity<>(fel, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> hanteraRuntimeException(RuntimeException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> hanteraAllaException(Exception ex) {
        return new ResponseEntity<>("Ett internt fel uppstod: " + ex.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
}