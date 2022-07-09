package hiro.kitchenpos.menu;

import hiro.kitchenpos.menu.presentation.dtos.CreateMenuProductRequest;
import hiro.kitchenpos.menu.presentation.dtos.CreateMenuRequest;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public class MenuFixtures {

    public static CreateMenuRequest createMenuRequest(final UUID menuGroupId, final List<CreateMenuProductRequest> requests) {
        return new CreateMenuRequest("치킨 + 콜라", BigDecimal.valueOf(20000), menuGroupId, requests);
    }

    public static CreateMenuProductRequest createMenuProductRequest(final UUID productId, final int quantity) {
        return new CreateMenuProductRequest(productId, quantity);
    }

}
