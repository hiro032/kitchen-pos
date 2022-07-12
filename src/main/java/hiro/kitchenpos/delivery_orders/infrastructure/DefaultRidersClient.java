package hiro.kitchenpos.delivery_orders.infrastructure;

import hiro.kitchenpos.delivery_orders.domain.RidersClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.UUID;

@Slf4j
@Component
public class DefaultRidersClient implements RidersClient {

    @Override
    public void requestDelivery(UUID orderId, BigDecimal amount, String deliveryAddress) {
        log.info("call riders: orderId={}, amount={}, address={},", orderId, amount, deliveryAddress);
    }
}
