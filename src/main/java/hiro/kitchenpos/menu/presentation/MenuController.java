package hiro.kitchenpos.menu.presentation;

import hiro.kitchenpos.menu.application.MenuService;
import hiro.kitchenpos.menu.application.dtos.CreateMenuInfo;
import hiro.kitchenpos.menu.application.dtos.MenuInfo;
import hiro.kitchenpos.menu.presentation.dtos.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.UUID;

@RequiredArgsConstructor
@RequestMapping("/api/menus")
@RestController
public class MenuController {

    private final MenuService menuService;

    @PostMapping
    public ResponseEntity<CreateMenuResponse> create(@Valid @RequestBody CreateMenuRequest request) {
        CreateMenuInfo createMenuInfo = menuService.create(request.toCommand());

        CreateMenuResponse response = CreateMenuResponse.toInfo(createMenuInfo);

        return ResponseEntity.created(URI.create("/api/menus" + response.getId())).body(response);
    }

    @PutMapping("{menuId}/price")
    public ResponseEntity<MenuResponse> changeMenusPrice(@PathVariable UUID menuId, @Valid @RequestBody ChangeMenuPriceRequest request) {
        MenuInfo info = menuService.changePrice(menuId, request.getChangePrice());

        MenuResponse response = MenuResponse.builder()
                .id(info.getId())
                .name(info.getName())
                .price(info.getPrice())
                .build();

        return ResponseEntity.ok(response);
    }
}
