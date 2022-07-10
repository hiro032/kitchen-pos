package hiro.kitchenpos.menu.presentation.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChangeMenuResponse {

    private UUID id;
    private String name;
    private BigDecimal price;

}
