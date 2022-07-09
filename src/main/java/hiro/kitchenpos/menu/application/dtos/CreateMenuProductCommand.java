package hiro.kitchenpos.menu.application.dtos;

import hiro.kitchenpos.menu.presentation.dtos.CreateMenuProductRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateMenuProductCommand {

    private UUID productId;
    private int quantity;

    public static List<CreateMenuProductCommand> from(List<CreateMenuProductRequest> createMenuProductRequests) {
        return createMenuProductRequests.stream()
                .map(CreateMenuProductRequest::toCommand)
                .collect(Collectors.toList());
    }
}
