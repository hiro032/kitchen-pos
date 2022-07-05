package hiro.kitchenpos.menugroup.domain.exception;

import hiro.kitchenpos.global.error.BusinessException;
import org.springframework.http.HttpStatus;

public class MenuGroupNameException extends BusinessException {

    private static final String MESSAGE = "메뉴 그룹의 이름은 빈 값일 수 없습니다.";

    public MenuGroupNameException() {
        super(MESSAGE, HttpStatus.BAD_REQUEST);
    }
}
