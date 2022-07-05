package hiro.kitchenpos.product.domain.exception;

import hiro.kitchenpos.global.error.BusinessException;
import org.springframework.http.HttpStatus;

public class ProductPriceLessThanZeroException extends BusinessException {

    private static final String MESSAGE = "상품 가격은 0 이상이어야 합니다.";

    public ProductPriceLessThanZeroException() {
        super(MESSAGE, HttpStatus.BAD_REQUEST);
    }
}
