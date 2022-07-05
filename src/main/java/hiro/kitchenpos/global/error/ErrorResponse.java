package hiro.kitchenpos.global.error;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.validation.FieldError;

import java.util.Collections;
import java.util.List;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ErrorResponse {

    private final String message;
    private final List<FieldError> fieldErrors;

    public static ErrorResponse fromFieldErrors(final String message, final List<FieldError> fieldErrors) {
        return new ErrorResponse(message, fieldErrors);
    }

    public static ErrorResponse fromMessage(final String message) {
        return new ErrorResponse(message, Collections.emptyList());
    }
}
