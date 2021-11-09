package com.infinx.hacker_bills.exception;

import com.infinx.hacker_bills.pojo.dto.JsonResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(RestExceptionHandler.class);

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String error = "Malformed JSON request";
        return buildResponseEntity(new JsonResponse(false, error ,null,HttpStatus.BAD_REQUEST,0, ex.getMessage()));
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        List<String> errors = new ArrayList<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.add(errorMessage);
        });

        return buildResponseEntity(new JsonResponse(false, errors.toString() ,null,HttpStatus.BAD_REQUEST,0, ""));
    }

    @ExceptionHandler(value = { Exception.class })
    public ResponseEntity<Object> handleException(Exception e){

        String error = "Internal Server Error";
        return buildResponseEntity(new JsonResponse(false, error ,null,HttpStatus.INTERNAL_SERVER_ERROR,0, e.getMessage()));
    }

    @ExceptionHandler(value = { ParseException.class })
    public ResponseEntity<Object> handleParseException(ParseException e){
        String error = "Parsing Data Error";
        return buildResponseEntity(new JsonResponse(false, error ,null,HttpStatus.INTERNAL_SERVER_ERROR,0, e.getMessage()));
    }

    @ExceptionHandler(value = { SQLException.class })
    public ResponseEntity<Object> handleSQLException(SQLException e){
        String error = "Db Querying Exception";
        return buildResponseEntity(new JsonResponse(false, error ,null,HttpStatus.INTERNAL_SERVER_ERROR,0, e.getMessage()));
    }

    @ExceptionHandler(value = { IOException.class })
    public ResponseEntity<Object> handleIOException(IOException e){
        String error = "Server internal IO exception error";
        return buildResponseEntity(new JsonResponse(false, error ,null,HttpStatus.INTERNAL_SERVER_ERROR,0, e.getMessage()));
    }

    @ExceptionHandler(value = { InterruptedException.class })
    public ResponseEntity<Object> handleInterruptedException(InterruptedException e){
        String error = "Server internal Interrupted Exception error";
        return buildResponseEntity(new JsonResponse(false, error ,null,HttpStatus.INTERNAL_SERVER_ERROR,0, e.getMessage()));
    }

    @ExceptionHandler(value = { BillException.class })
    public ResponseEntity<Object> handleBillException(BillException e){
        String error = "Could not the find the bill id";
        return buildResponseEntity(new JsonResponse(false, error ,null,HttpStatus.BAD_REQUEST,0, e.getMessage()));
    }

    private ResponseEntity<Object> buildResponseEntity(JsonResponse jsonResponse) {
        return new ResponseEntity<>(jsonResponse, jsonResponse.getStatus());
    }

    //other exception handlers below

}
