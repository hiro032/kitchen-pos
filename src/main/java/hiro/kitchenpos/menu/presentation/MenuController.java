package hiro.kitchenpos.menu.presentation;

import hiro.kitchenpos.menu.application.MenuService;
import hiro.kitchenpos.menu.application.dtos.CreateMenuInfo;
import hiro.kitchenpos.menu.presentation.dtos.CreateMenuRequest;
import hiro.kitchenpos.menu.presentation.dtos.CreateMenuResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;

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
}
