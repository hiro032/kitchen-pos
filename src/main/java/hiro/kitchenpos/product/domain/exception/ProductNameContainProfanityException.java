package hiro.kitchenpos.product.domain.exception;

import hiro.kitchenpos.global.error.BusinessException;
import org.springframework.http.HttpStatus;

public class ProductNameContainProfanityException extends BusinessException {

    private static final String MESSAGE = "상품 이름에 욕설이 포함되어있습니다.";
    public ProductNameContainProfanityException() {
        super(MESSAGE, HttpStatus.BAD_REQUEST);
    }
}
