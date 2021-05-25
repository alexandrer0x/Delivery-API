package dev.alexandrevieira.delivery.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.OffsetDateTime;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
public class ResponseError {

    private HttpStatus status;
    private String message;
    private List<Field> fields;
    private OffsetDateTime timestamp = OffsetDateTime.now();

    public ResponseError(HttpStatus status) {
        this.status = status;
        this.message = status.getReasonPhrase();
    }

    public ResponseError(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public ResponseError(HttpStatus status, String message, List<Field> fields) {
        this.status = status;
        this.message = message;
        this.fields = fields;
    }

    public int getStatus() {
        return status.value();
    }

    @AllArgsConstructor
    @Getter
    public static class Field {
        private String object;
        private String field;
        private String message;
    }
}
