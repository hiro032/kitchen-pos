package hiro.kitchenpos.global.error;

import org.springframework.http.HttpStatus;

public class EntityNotFoundException extends BusinessException {

    public EntityNotFoundException(String message, HttpStatus httpStatus) {
        super(message, httpStatus);
    }

}
