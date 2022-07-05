package hiro.kitchenpos.menu.domain.exception;

import hiro.kitchenpos.global.error.BusinessException;
import org.springframework.http.HttpStatus;

public class MenuProductsEmptyException extends BusinessException {

    private static final String MESSAGE = "메뉴 상품에 아무런 상품이 담기지 않았습니다.";

    public MenuProductsEmptyException() {
        super(MESSAGE, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
