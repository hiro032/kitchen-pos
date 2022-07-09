package hiro.kitchenpos.product.application.dtos;

import hiro.kitchenpos.product.domain.Product;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@AllArgsConstructor
public class CreateProductInfo {

    private UUID id;
    private String name;
    private BigDecimal price;

    public static CreateProductInfo toEntity(Product entity) {
        return new CreateProductInfo(entity.getId(), entity.getName().getName(), entity.getPrice().getPrice());
    }
}
