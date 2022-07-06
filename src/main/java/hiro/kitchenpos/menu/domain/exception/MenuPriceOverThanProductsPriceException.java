package hiro.kitchenpos.menu.domain.exception;

import hiro.kitchenpos.global.error.BusinessException;
import org.springframework.http.HttpStatus;

public class MenuPriceOverThanProductsPriceException extends BusinessException {

    private static final String MESSAGE = "등록할 메뉴의 가격은 메뉴에 포함된 상품의 가격의 합 보다 클 수 없습니다.";

    public MenuPriceOverThanProductsPriceException() {
        super(MESSAGE, HttpStatus.BAD_REQUEST);
    }
}
