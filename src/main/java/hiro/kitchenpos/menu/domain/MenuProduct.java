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
    @Column(name = "id", columnDefinition = "varbinary(16)")
    private UUID id;

    private UUID productId;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    public MenuProduct(final UUID productId, final int quantity) {
        validateQuantity(quantity);
        this.id = UUID.randomUUID();
        this.productId = productId;
        this.quantity = quantity;
    }

    private void validateQuantity(int quantity) {
        if (quantity <= 0) {
            throw new MenuProductQuantityException();
        }
    }
}
