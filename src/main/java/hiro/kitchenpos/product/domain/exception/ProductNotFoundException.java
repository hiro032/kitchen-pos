package hiro.kitchenpos.product.domain.exception;


import hiro.kitchenpos.global.error.EntityNotFoundException;
import org.springframework.http.HttpStatus;

public class ProductNotFoundException extends EntityNotFoundException {

    private static final String MESSAGE = "id 에 해당하는 product 를 찾을 수 없습니다.";

    public ProductNotFoundException() {
        super(MESSAGE, HttpStatus.BAD_REQUEST);
    }
}
