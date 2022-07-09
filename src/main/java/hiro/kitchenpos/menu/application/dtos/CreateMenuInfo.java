package hiro.kitchenpos.menu.application.dtos;

import hiro.kitchenpos.menu.domain.Menu;
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
public class CreateMenuInfo {

    private UUID id;
    private String name;
    private BigDecimal price;
    private List<MenuProductInfo> menuProductInfos = new ArrayList<>();

    public static CreateMenuInfo toEntity(Menu entity) {
        List<MenuProductInfo> menuProductInfos = entity.getMenuProducts().getProducts().stream()
                .map(MenuProductInfo::toEntity)
                .collect(Collectors.toList());

        return new CreateMenuInfo(
                entity.getId(),
                entity.getName().getName(),
                entity.getPrice().getPrice(),
                menuProductInfos
        );
    }
}
