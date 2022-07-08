package hiro.kitchenpos.menu.domain;

import hiro.kitchenpos.menu.domain.exception.MenuProductsEmptyException;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class MenuProducts {

    @JoinColumn(name = "menu_id")
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<MenuProduct> products = new ArrayList<>();

    public MenuProducts(final List<MenuProduct> products) {
        validateMenuProducts(products);
        this.products = products;
    }

    private void validateMenuProducts(final List<MenuProduct> products) {
        if(products.isEmpty()) {
            throw new MenuProductsEmptyException();
        }
    }

    public BigDecimal calcTotalPrice() {
        BigDecimal totalPrice = BigDecimal.ZERO;
        for (MenuProduct product : products) {
            totalPrice = product.getPrice();
        }
        return totalPrice;
    }
}
