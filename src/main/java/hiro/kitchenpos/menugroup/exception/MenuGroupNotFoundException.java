package hiro.kitchenpos.menugroup.exception;


import hiro.kitchenpos.global.error.EntityNotFoundException;
import org.springframework.http.HttpStatus;

public class MenuGroupNotFoundException extends EntityNotFoundException {

    private static final String MESSAGE = "id 에 해당하는 MenuGroup 을 찾을 수 없습니다.";

    public MenuGroupNotFoundException() {
        super(MESSAGE, HttpStatus.BAD_REQUEST);
    }
}
