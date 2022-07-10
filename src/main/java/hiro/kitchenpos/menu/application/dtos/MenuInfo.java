package hiro.kitchenpos.menu.application.dtos;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Builder
public class MenuInfo {

    private UUID id;
    private String name;
    private BigDecimal price;
    private Boolean displayed;

}
