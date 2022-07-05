package hiro.kitchenpos.global;

import hiro.kitchenpos.global.error.BusinessException;
import hiro.kitchenpos.global.error.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.EntityNotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BindException.class)
    public ResponseEntity<ErrorResponse> handler(final BindException e) {
        ErrorResponse errorResponse = ErrorResponse.fromFieldErrors(e.getMessage(), e.getFieldErrors());

        return ResponseEntity.badRequest().body(errorResponse);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Void> handler() {

        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> handler(final BusinessException e) {
        ErrorResponse errorResponse = ErrorResponse.fromMessage(e.getMessage());

        return ResponseEntity.status(e.getHttpStatus().value()).body(errorResponse);
    }
}
