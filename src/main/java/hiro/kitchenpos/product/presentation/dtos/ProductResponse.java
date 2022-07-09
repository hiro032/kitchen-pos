package hiro.kitchenpos.product.presentation.dtos;

import hiro.kitchenpos.menu.application.dtos.ProductInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponse {

    private UUID id;
    private String name;
    private BigDecimal price;

    public static ProductResponse toInfo(ProductInfo info) {
        return new ProductResponse(
                info.getId(),
                info.getName(),
                info.getPrice()
        );
    }
}
