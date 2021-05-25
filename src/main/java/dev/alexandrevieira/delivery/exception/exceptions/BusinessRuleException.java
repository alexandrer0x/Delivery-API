package dev.alexandrevieira.delivery.exception.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class BusinessRuleException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private final HttpStatus status = HttpStatus.BAD_REQUEST;
    private String message;

    public BusinessRuleException(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status.value();
    }
}
