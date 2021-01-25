package kz.project.demo.controllers;

import kz.project.demo.model.errors.ErrorResponse;
import kz.project.demo.model.errors.ServiceException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AdviceController {

    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<?> errors(ServiceException e) {
        return new ResponseEntity<>(buildErrorResponse(e), e.getHttpStatus());
    }

    private ErrorResponse buildErrorResponse(ServiceException serviceException) {
        return ErrorResponse.builder()
                .code(serviceException.getErrorCode())
                .message(serviceException.getMessage())
                .build();
    }
}
