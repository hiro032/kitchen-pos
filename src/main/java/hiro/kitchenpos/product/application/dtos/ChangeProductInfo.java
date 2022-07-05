package hiro.kitchenpos.product.application.dtos;

import hiro.kitchenpos.product.domain.Product;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@AllArgsConstructor
public class ChangeProductInfo {

    private UUID id;
    private String name;
    private BigDecimal price;

    public static ChangeProductInfo fromEntity(Product product) {
        return new ChangeProductInfo(product.getId(), product.getName().getName(), product.getPrice().getPrice());
    }
}
