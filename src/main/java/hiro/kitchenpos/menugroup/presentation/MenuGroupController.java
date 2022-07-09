package hiro.kitchenpos.menugroup.presentation;

import hiro.kitchenpos.menugroup.application.CreateMenuGroupInfo;
import hiro.kitchenpos.menugroup.application.MenuGroupService;
import hiro.kitchenpos.menugroup.application.dtos.ChangeMenuGroupInfo;
import hiro.kitchenpos.menugroup.presentation.dtos.ChangeMenuGroupRequest;
import hiro.kitchenpos.menugroup.presentation.dtos.ChangeMenuGroupResponse;
import hiro.kitchenpos.menugroup.presentation.dtos.CreateMenuGroupRequest;
import hiro.kitchenpos.menugroup.presentation.dtos.MenuGroupResponse.CreateMenuGroupResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.UUID;

@RequestMapping("/api/menu-groups")
@RequiredArgsConstructor
@RestController
public class MenuGroupController {

    private final MenuGroupService menuGroupService;

    @PostMapping
    public ResponseEntity<CreateMenuGroupResponse> createMenuGroup(@Valid @RequestBody CreateMenuGroupRequest request) {
        CreateMenuGroupInfo info = menuGroupService.create(request.getName());

        return ResponseEntity.created(URI.create("/api/menu-groups/" + info.getId())).body(CreateMenuGroupResponse.toInfo(info));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ChangeMenuGroupResponse> changeMenuGroup(@Valid @RequestBody ChangeMenuGroupRequest request, @PathVariable UUID id) {
        ChangeMenuGroupInfo info = menuGroupService.change(id, request.getName());

        return ResponseEntity.ok(ChangeMenuGroupResponse.fromInfo(info));
    }
}
