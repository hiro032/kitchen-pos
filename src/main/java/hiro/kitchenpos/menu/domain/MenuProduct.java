package hiro.kitchenpos.menu.domain;

import hiro.kitchenpos.menu.domain.exception.MenuProductQuantityException;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "menu_product")
@Entity
public class MenuProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_id", nullable = false)
    private UUID product;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    public MenuProduct(final UUID product, final int quantity) {
        validateQuantity(quantity);
        this.product = product;
        this.quantity = quantity;
    }

    private void validateQuantity(int quantity) {
        if (quantity <= 0) {
            throw new MenuProductQuantityException();
        }
    }

}
