package hiro.kitchenpos.delivery_orders.domain.exception;

import hiro.kitchenpos.global.error.EntityNotFoundException;
import org.springframework.http.HttpStatus;

public class DeliveryOrderNotFoundException extends EntityNotFoundException {

    private static final String MESSAGE = "id 에 해당하는 배달 주문을 찾을 수 없습니다.";

    public DeliveryOrderNotFoundException() {
        super(MESSAGE, HttpStatus.BAD_REQUEST);
    }
}
