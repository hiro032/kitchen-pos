package hiro.kitchenpos.menu.domain.exception;

import hiro.kitchenpos.global.error.EntityNotFoundException;
import org.springframework.http.HttpStatus;

public class MenuNotFoundException extends EntityNotFoundException {

    private static final String MESSAGE = "id 에 해당하는 메뉴를 찾을 수 없습니다.";

    public MenuNotFoundException() {
        super(MESSAGE, HttpStatus.NOT_FOUND);
    }
}
