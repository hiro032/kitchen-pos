package hiro.kitchenpos.menu.domain;

import hiro.kitchenpos.menu.domain.exception.MenuProductsEmptyException;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class MenuProducts {

    @OneToMany
    @JoinColumn(name = "menu_id")
    private List<MenuProduct> products = new ArrayList<>();

    public MenuProducts(final List<MenuProduct> products) {
        validateMenuProducts(products);
        this.products = products;
    }

    private void validateMenuProducts(List<MenuProduct> products) {
        if(products.isEmpty()) {
            throw new MenuProductsEmptyException();
        }
    }
}
