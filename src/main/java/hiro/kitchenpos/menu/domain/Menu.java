package hiro.kitchenpos.menu.domain;

import hiro.kitchenpos.menu.domain.exception.MenuPriceOverThanProductsPriceException;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "menus")
@Entity
public class Menu {

    @Column(name = "id", columnDefinition = "varbinary(16)")
    @Id
    private UUID id;

    @Column(name = "name", nullable = false)
    @Embedded
    private MenuName name;

    @Column(name = "price", nullable = false)
    @Embedded
    private MenuPrice price;

    @Column(nullable = false)
    private UUID menuGroupId;

    @Embedded
    private MenuProducts menuProducts;

    @Column(name = "displayed", nullable = false)
    private Boolean displayed;

    public Menu(String name, BigDecimal price, UUID menuGroupId, List<MenuProduct> menuProducts) {
        this.id = UUID.randomUUID();
        this.name = new MenuName(name);
        this.price = new MenuPrice(price);
        this.menuGroupId = menuGroupId;
        this.menuProducts = new MenuProducts(menuProducts);
        this.displayed = Boolean.TRUE;
    }

    public boolean priceIsOverThanProductsPrice() {
        return menuPriceOverThenProductsPrice();
    }

    public void display() {
        if(menuPriceOverThenProductsPrice()) {
            throw new MenuPriceOverThanProductsPriceException();
        }
        displayed = Boolean.TRUE;
    }

    public void unDisplay() {
        displayed = Boolean.FALSE;
    }

    public void changePrice(final BigDecimal changePrice) {
        if (changePriceIsOverThanProductsPrice(changePrice)) {
            unDisplay();
        }
        price = new MenuPrice(changePrice);
    }

    private boolean changePriceIsOverThanProductsPrice(final BigDecimal changePrice) {
        return changePrice.compareTo(menuProducts.calcTotalPrice()) > 0;
    }

    private boolean menuPriceOverThenProductsPrice() {
        return price.getPrice().compareTo(menuProducts.calcTotalPrice()) > 0;
    }

}
