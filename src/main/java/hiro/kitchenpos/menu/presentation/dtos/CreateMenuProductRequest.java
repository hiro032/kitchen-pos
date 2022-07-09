package hiro.kitchenpos.menu.presentation.dtos;

import hiro.kitchenpos.menu.application.dtos.CreateMenuProductCommand;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateMenuProductRequest {

    private UUID productId;
    private int quantity;

    public CreateMenuProductCommand toCommand() {
        return new CreateMenuProductCommand(productId, quantity);
    }
}
