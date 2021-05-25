package dev.alexandrevieira.delivery.exception.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class NotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private final HttpStatus status = HttpStatus.NOT_FOUND;
    private String message;

    public NotFoundException(Class c, Long id) {
        this.message = String.format("Not found a %s with id %d", c.getSimpleName(), id);
    }

    public NotFoundException(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status.value();
    }
}
