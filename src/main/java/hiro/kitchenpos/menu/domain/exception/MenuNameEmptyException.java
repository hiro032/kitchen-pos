package hiro.kitchenpos.menu.domain.exception;

import hiro.kitchenpos.global.error.BusinessException;
import org.springframework.http.HttpStatus;

public class MenuNameEmptyException extends BusinessException {

    private static final String MESSAGE = "메뉴의 이름은 공백일 수 없습니다.";

    public MenuNameEmptyException() {
        super(MESSAGE, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
