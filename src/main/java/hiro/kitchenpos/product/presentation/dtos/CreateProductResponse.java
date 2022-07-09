package hiro.kitchenpos.product.presentation.dtos;

import hiro.kitchenpos.product.application.dtos.CreateProductInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateProductResponse {

    private UUID id;
    private String name;
    private BigDecimal price;

    public static CreateProductResponse toInfo(CreateProductInfo info) {
        return new CreateProductResponse(info.getId(), info.getName(), info.getPrice());
    }
}
