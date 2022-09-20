package hiro.kitchenpos.menu;

import hiro.kitchenpos.menu.domain.Menu;
import hiro.kitchenpos.menu.domain.MenuProduct;
import hiro.kitchenpos.menu.presentation.dtos.ChangeMenuPriceRequest;
import hiro.kitchenpos.menu.presentation.dtos.CreateMenuProductRequest;
import hiro.kitchenpos.menu.presentation.dtos.CreateMenuRequest;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public class MenuFixtures {

    private static final int DEFAULT_QUANTITY = 1;
    private static final BigDecimal DEFAULT_PRICE = BigDecimal.valueOf(10000);

    public static CreateMenuRequest createMenuRequest(final UUID menuGroupId, final List<CreateMenuProductRequest> requests) {
        return new CreateMenuRequest("치킨 + 콜라", BigDecimal.valueOf(10000), menuGroupId, requests);
    }

    public static CreateMenuProductRequest createMenuProductRequest(final UUID productId, final int quantity) {
        return new CreateMenuProductRequest(productId, quantity);
    }

    public static MenuProduct menuProduct() {
        return new MenuProduct(UUID.randomUUID(), DEFAULT_QUANTITY, DEFAULT_PRICE);
    }

    public static MenuProduct menuProduct(final BigDecimal price) {
        return new MenuProduct(UUID.randomUUID(), DEFAULT_QUANTITY, price);
    }

    public static MenuProduct menuProduct(final UUID productId, final BigDecimal price) {
        return new MenuProduct(productId, DEFAULT_QUANTITY, price);
    }

    public static ChangeMenuPriceRequest changeMenuPriceRequest(final BigDecimal changePrice) {
        return new ChangeMenuPriceRequest(changePrice);
    }

    public static Menu menu(String name, BigDecimal price, UUID menuGroupId, List<MenuProduct> menuProducts) {
        return new Menu(name, price, menuGroupId, menuProducts);
    }
}
