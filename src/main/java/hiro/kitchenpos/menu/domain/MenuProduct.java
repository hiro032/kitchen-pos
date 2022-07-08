package hiro.kitchenpos.menu.domain;

import hiro.kitchenpos.menu.domain.exception.MenuProductQuantityException;
import hiro.kitchenpos.product.domain.Product;
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
    @Column(name = "id", columnDefinition = "varbinary(16)")
    private UUID id;

    @JoinColumn(name = "product_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Product product;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    public MenuProduct(final Product product, final int quantity) {
        validateQuantity(quantity);
        this.id = UUID.randomUUID();
        this.product = product;
        this.quantity = quantity;
    }

    private void validateQuantity(int quantity) {
        if (quantity <= 0) {
            throw new MenuProductQuantityException();
        }
    }

    public BigDecimal getPrice() {
        return product.getPrice().getPrice().multiply(BigDecimal.valueOf(quantity));
    }
}
