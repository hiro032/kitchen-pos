package hiro.kitchenpos.delivery_orders.domain.exception;

import hiro.kitchenpos.global.error.BusinessException;
import org.springframework.http.HttpStatus;

public class OrderStatusException extends BusinessException {

    public OrderStatusException(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }
}
