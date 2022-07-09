package hiro.kitchenpos.menu.application.dtos;

import hiro.kitchenpos.product.domain.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductInfo {

    private UUID id;
    private String name;
    private BigDecimal price;

    public static ProductInfo toEntity(Product entity) {
        return new ProductInfo(entity.getId(), entity.getName().getName(), entity.getPrice().getPrice());
    }
}
