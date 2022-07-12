package hiro.kitchenpos.delivery_orders.application;

import hiro.kitchenpos.delivery_orders.application.dtos.CreateDeliveryOrderCommand;
import hiro.kitchenpos.delivery_orders.domain.DeliveryOrder;
import hiro.kitchenpos.menu.domain.Menu;
import hiro.kitchenpos.menu.domain.MenuProduct;
import hiro.kitchenpos.menu.domain.MenuRepository;
import hiro.kitchenpos.menu.fake.InMemoryMenuRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.UUID;

import static hiro.kitchenpos.delivery_orders.DeliveryOrderFixtures.createDeliveryOrderCommand;
import static hiro.kitchenpos.delivery_orders.DeliveryOrderFixtures.orderLineItemCommand;
import static hiro.kitchenpos.menu.MenuFixtures.menu;
import static hiro.kitchenpos.menu.MenuFixtures.menuProduct;
import static hiro.kitchenpos.product.ProductsFixtures.product;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class DeliveryOrderFactoryTest {

    private DeliveryOrderFactory deliveryOrderFactory;
    private MenuRepository menuRepository;

    @BeforeEach
    void setUp() {
        menuRepository = new InMemoryMenuRepository();
        deliveryOrderFactory = new DeliveryOrderFactory(menuRepository);
    }

    @DisplayName("CreateDeliveryOrderCommand 를 통해 도메인 객체를 생성한다.")
    @Test
    void create() {
        MenuProduct 치킨 = menuProduct(product("치킨"));
        MenuProduct 콜라 = menuProduct(product("콜라"));
        MenuProduct 피자 = menuProduct(product("피자"));

        Menu 치킨_콜라 = menu("치킨+콜라", BigDecimal.TEN, UUID.randomUUID(), Arrays.asList(치킨, 콜라));
        Menu 피자_콜라 = menu("피자+콜라", BigDecimal.TEN, UUID.randomUUID(), Arrays.asList(피자, 콜라));

        menuRepository.save(치킨_콜라);
        menuRepository.save(피자_콜라);

        orderLineItemCommand(치킨_콜라.getId(), 1);
        orderLineItemCommand(피자_콜라.getId(), 1);

        CreateDeliveryOrderCommand deliveryOrderCommand = createDeliveryOrderCommand(Arrays.asList(
                orderLineItemCommand(치킨_콜라.getId(), 1),
                orderLineItemCommand(피자_콜라.getId(), 1)
        ));

        // Act
        DeliveryOrder deliveryOrder = deliveryOrderFactory.createDeliveryOrder(deliveryOrderCommand);

        // Assert
        assertAll(
                () -> assertThat(deliveryOrder).isInstanceOf(DeliveryOrder.class),
                () -> assertThat(deliveryOrder.getId()).isNotNull()
        );
    }
}
