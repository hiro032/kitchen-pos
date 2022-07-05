package hiro.kitchenpos.product.presentation.dtos;

import hiro.kitchenpos.product.application.dtos.ChangeProductInfo;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class ChangeProductResponse {

    private UUID id;
    private String name;
    private BigDecimal price;

    public static ChangeProductResponse fromProductInfo(ChangeProductInfo info) {
        return new ChangeProductResponse(info.getId(), info.getName(), info.getPrice());
    }
}
