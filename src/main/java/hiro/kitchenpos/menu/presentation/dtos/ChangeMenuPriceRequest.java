package hiro.kitchenpos.menu.presentation.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChangeMenuPriceRequest {

    private BigDecimal changePrice;

}
