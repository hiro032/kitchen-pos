package hiro.kitchenpos.menu.domain.exception;

import hiro.kitchenpos.global.error.BusinessException;
import org.springframework.http.HttpStatus;

public class MenuPriceLessThanZeroException extends BusinessException {

    private static final String MESSAGE = "메뉴 가격은 0보다 작을 수 없습니다.";

    public MenuPriceLessThanZeroException() {
        super(MESSAGE, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
