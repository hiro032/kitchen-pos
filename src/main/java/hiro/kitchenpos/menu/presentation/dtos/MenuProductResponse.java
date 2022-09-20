package hiro.kitchenpos.menu.presentation.dtos;

import hiro.kitchenpos.menu.application.dtos.MenuProductInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuProductResponse {

    private Long id;
    private UUID productId;
    private int quantity;

    public static MenuProductResponse toInfo(MenuProductInfo menuProductInfo) {
        return new MenuProductResponse(
                menuProductInfo.getId(),
                menuProductInfo.getProductId(),
                menuProductInfo.getQuantity()
        );
    }
}
