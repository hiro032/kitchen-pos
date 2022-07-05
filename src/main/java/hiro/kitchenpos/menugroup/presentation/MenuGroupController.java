package hiro.kitchenpos.menugroup.presentation;

import hiro.kitchenpos.menugroup.application.MenuGroupService;
import hiro.kitchenpos.menugroup.presentation.dtos.CreateMenuGroupRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;
import java.util.UUID;

@RequestMapping("/api/menu-groups")
@RequiredArgsConstructor
@RestController
public class MenuGroupController {

    private final MenuGroupService menuGroupService;

    @PostMapping
    public ResponseEntity<Void> createMenuGroup(@Valid @RequestBody CreateMenuGroupRequest request) {
        UUID id = menuGroupService.create(request.getName());

        return ResponseEntity.created(URI.create("/api/menu-groups/" + id)).build();
    }
}
