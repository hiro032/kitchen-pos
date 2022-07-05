package hiro.kitchenpos.menu.application.dtos;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateMenuCommand {

    private String name;
    private BigDecimal price;
    private UUID menuGroupId;
    private List<CreateMenuProductCommand> createMenuProductCommands;

}
