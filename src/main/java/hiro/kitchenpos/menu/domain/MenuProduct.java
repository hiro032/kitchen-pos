package hiro.kitchenpos.menu.domain;

import hiro.kitchenpos.menu.domain.exception.MenuProductQuantityException;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
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
    private UUID productId;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    @Column(name = "menu_product_price", nullable = false)
    private BigDecimal menuProductPrice;

    public MenuProduct(final UUID productId, final int quantity, final BigDecimal menuProductPrice) {
        validateQuantity(quantity);
        this.productId = productId;
        this.quantity = quantity;
        this.menuProductPrice = menuProductPrice;
    }

    private void validateQuantity(final int quantity) {
        if (quantity <= 0) {
            throw new MenuProductQuantityException();
        }
    }

}
