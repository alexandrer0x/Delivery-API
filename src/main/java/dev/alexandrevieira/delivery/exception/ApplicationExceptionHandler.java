package dev.alexandrevieira.delivery.exception;

import dev.alexandrevieira.delivery.config.Messages;
import dev.alexandrevieira.delivery.exception.exceptions.BusinessRuleException;
import dev.alexandrevieira.delivery.exception.exceptions.NotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ResponseError> handleNotFoundException(NotFoundException ex) {
        ResponseError error = new ResponseError(HttpStatus.valueOf(ex.getStatus()), ex.getMessage());
        return ResponseEntity.status(ex.getStatus()).body(error);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ResponseError> handleIllegalArgumentException(IllegalArgumentException ex) {
        final HttpStatus status = HttpStatus.BAD_REQUEST;
        ResponseError error = new ResponseError(status, ex.getMessage());
        return ResponseEntity.status(status).body(error);
    }



    @ExceptionHandler(BusinessRuleException.class)
    public ResponseEntity<ResponseError> handleBusinessRuleException(BusinessRuleException ex) {
        ResponseError error = new ResponseError(HttpStatus.valueOf(ex.getStatus()), ex.getMessage());
        return ResponseEntity.status(ex.getStatus()).body(error);
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<ResponseError> handleConstraintViolationException(SQLIntegrityConstraintViolationException ex) {
        final HttpStatus status = HttpStatus.BAD_REQUEST;
        ResponseError error = new ResponseError(status, ex.getMessage());
        return ResponseEntity.status(status).body(error);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<ResponseError.Field> fields = new ArrayList<>();
        List<ObjectError> objectErrors = ex.getBindingResult().getAllErrors();
        boolean allAreFieldErrors = true;
        ResponseError responseError;

        for(var error : objectErrors) {
            if(!(error instanceof FieldError)) {
                allAreFieldErrors = false;
                break;
            }
        }

        if(allAreFieldErrors) {
            objectErrors.forEach(x -> {
                    FieldError fieldError = (FieldError)x;
                    ResponseError.Field field = new ResponseError.Field(
                            fieldError.getObjectName(),
                            fieldError.getField(),
                            fieldError.getDefaultMessage()
                    );
                    fields.add(field);
            });
            responseError = new ResponseError(status, Messages.FIELD_ERROR, fields);
        }
        else {
            responseError = new ResponseError(status);
        }

        return ResponseEntity.status(status).body(responseError);
    }
}
