package com.aldebaran.qrcodeapi.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@RestControllerAdvice
public class ExceptionController extends ResponseEntityExceptionHandler {

    //TODO: Create @ExceptionHandler for each specific exceptions

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleAllExceptions(Exception ex) throws JsonProcessingException {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(
                        exceptionToJson(ex)
                );
    }

    public static String exceptionToJson(Exception exception) throws JsonProcessingException {
        ObjectMapper mapper = JsonMapper.builder()
                .enable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                .build();
        return mapper.writeValueAsString(exception);
    }

}
