package com.transformers.application.exception;

import com.transformers.application.config.Constants;
import com.transformers.application.dto.ErrorResponseModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class ControlErrorHandler {

    private static final Logger log = LoggerFactory.getLogger(ControlErrorHandler.class);

    @ExceptionHandler(value = {MethodArgumentNotValidException.class, NoRecordFoundException.class, Exception.class})
    public final ResponseEntity<ErrorResponseModel> handleExceptions(Exception ex, WebRequest request) {
        ResponseEntity responseEntity = null;

        if (ex instanceof MethodArgumentNotValidException) {

            MethodArgumentNotValidException argumentNotValidException = (MethodArgumentNotValidException) ex;
            List<String> errorMessages = argumentNotValidException.getBindingResult().getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.toList());
            log.error("handleMethodArgumentNotValid: errors=[{}]", errorMessages);

            return new ResponseEntity<>(
                    ErrorResponseModel.builder()
                            .errorMessage(errorMessages.get(0))
                            .timestamp(LocalDateTime.now())
                            .path(request.getDescription(false))
                            .build(),
                    HttpStatus.BAD_REQUEST);

        } else if (ex instanceof NoRecordFoundException) {
            NoRecordFoundException noRecordFoundException = (NoRecordFoundException) ex;
            log.error("handleMethodArgumentNotValid: errors=[{}]", noRecordFoundException.getMessage());

            return new ResponseEntity<>(
                    ErrorResponseModel.builder()
                            .errorMessage(noRecordFoundException.getMessage())
                            .timestamp(LocalDateTime.now())
                            .path(request.getDescription(false))
                            .build(),
                    HttpStatus.NOT_FOUND);
        } else {
            log.info("Default Error Message");
            responseEntity = new ResponseEntity<>(
                    ErrorResponseModel.builder()
                            .errorMessage(Constants.DEFAULT_ERROR_MESSAGE)
                            .timestamp(LocalDateTime.now())
                            .path(request.getDescription(false))
                            .build(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return responseEntity;
    }
}
