package hiro.kitchenpos.menu.domain.exception;

import hiro.kitchenpos.global.error.BusinessException;
import org.springframework.http.HttpStatus;

public class MenuProductQuantityException extends BusinessException {

    private static final String MESSAGE = "메뉴 제품의 수량은 0보다 커야 합니다.";
    public MenuProductQuantityException() {
        super(MESSAGE, HttpStatus.BAD_REQUEST);
    }
}
