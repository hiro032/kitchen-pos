package hiro.kitchenpos.product.presentation.dtos;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class ChangeProductRequest {

    @NotBlank
    private String name;

    @DecimalMin(value = "0.0")
    private BigDecimal price;

}
