package hiro.kitchenpos.menu.application.dtos;

import hiro.kitchenpos.menu.domain.MenuProduct;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MenuProductInfo {

    private Long id;
    private UUID productId;
    private int quantity;

    public static MenuProductInfo toEntity(final MenuProduct entity) {
        return new MenuProductInfo(
                entity.getId(),
                entity.getProductId(),
                entity.getQuantity()
        );
    }
}
