package hiro.kitchenpos.menu.presentation.dtos;

import hiro.kitchenpos.menu.application.dtos.CreateMenuCommand;
import hiro.kitchenpos.menu.application.dtos.CreateMenuProductCommand;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateMenuRequest {

    private String name;
    private BigDecimal price;
    private UUID menuGroupId;
    private List<CreateMenuProductRequest> createMenuProductRequests;

    public CreateMenuCommand toCommand() {
        return new CreateMenuCommand(name, price, menuGroupId, CreateMenuProductCommand.from(createMenuProductRequests));
    }
}
