package hiro.kitchenpos.menu.presentation.dtos;

import hiro.kitchenpos.menu.application.dtos.CreateMenuInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateMenuResponse {

    private UUID id;
    private String name;
    private BigDecimal price;
    private List<MenuProductResponse> menuProductResponses = new ArrayList<>();

    public static CreateMenuResponse toInfo(CreateMenuInfo info) {
        List<MenuProductResponse> menuProductResponses = info.getMenuProductInfos().stream()
                .map(MenuProductResponse::toInfo)
                .collect(Collectors.toList());

        return new CreateMenuResponse(
                info.getId(),
                info.getName(),
                info.getPrice(),
                menuProductResponses
        );
    }
}
