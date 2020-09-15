package com.transcacheservice.cacheapp.restController;

import com.transcacheservice.cacheapp.exceptions.BadRequestException;
import com.transcacheservice.cacheapp.exceptions.DatabaseUnavailableException;
import com.transcacheservice.cacheapp.exceptions.ErrorResponse;
import com.transcacheservice.cacheapp.exceptions.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.ws.client.WebServiceIOException;
import org.springframework.ws.soap.client.SoapFaultClientException;
import redis.clients.jedis.exceptions.JedisConnectionException;

import javax.validation.constraints.Null;
import java.time.LocalDateTime;

/**
 * @author Zahar Zaitsev
 * @version 1.0
 */

@RestControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(RestResponseEntityExceptionHandler.class);

    @ExceptionHandler(value = {SoapFaultClientException.class, NotFoundException.class})
    protected ResponseEntity<Object> handleNotFound(Exception e) {
        ErrorResponse errorResponse = new ErrorResponse("NOT_FOUND_ERROR", e.getLocalizedMessage());
        errorResponse.setTimestamp(LocalDateTime.now());
        errorResponse.setStatus(HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {WebServiceIOException.class, JedisConnectionException.class})
    protected ResponseEntity<Object> handleDatabaseUnavailable(Exception e) {
        ErrorResponse errorResponse = new ErrorResponse("DATABASE_UNAVAILABLE",
                "Can't connect to the database");
        errorResponse.setTimestamp(LocalDateTime.now());
        errorResponse.setStatus(HttpStatus.SERVICE_UNAVAILABLE.value());
        return new ResponseEntity<>(errorResponse, HttpStatus.SERVICE_UNAVAILABLE);
    }

    @ExceptionHandler(value = {BadRequestException.class})
    protected ResponseEntity<Object> handleBadRequest(Exception e) {
        ErrorResponse errorResponse = new ErrorResponse("BAD_REQUEST_ERROR", e.getLocalizedMessage());
        errorResponse.setTimestamp(LocalDateTime.now());
        errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

//    @ExceptionHandler(value = Exception.class)
//    protected ResponseEntity<Object> handleAny(Exception e) {
//        ErrorResponse errorResponse = new ErrorResponse("ERROR", "Sorry, something went wrong");
//        errorResponse.setTimestamp(LocalDateTime.now());
//        errorResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
//        logger.error("Unexpected error. Message: {}", e.getLocalizedMessage());
//        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
//    }
}
