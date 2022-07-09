package hiro.kitchenpos.menu.presentation.dtos;

import hiro.kitchenpos.menu.application.dtos.MenuProductInfo;
import hiro.kitchenpos.product.presentation.dtos.ProductResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuProductResponse {

    private UUID id;
    private ProductResponse productResponse;
    private int quantity;

    public static MenuProductResponse toInfo(MenuProductInfo menuProductInfo) {
        return new MenuProductResponse(
                menuProductInfo.getId(),
                ProductResponse.toInfo(menuProductInfo.getInfo()),
                menuProductInfo.getQuantity()
        );
    }
}
