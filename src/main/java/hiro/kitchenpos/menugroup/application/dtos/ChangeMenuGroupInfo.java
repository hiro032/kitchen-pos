package hiro.kitchenpos.menugroup.application.dtos;

import hiro.kitchenpos.menugroup.domain.MenuGroup;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChangeMenuGroupInfo {

    private UUID id;
    private String name;

    public static ChangeMenuGroupInfo fromEntity(MenuGroup menuGroup) {
        return new ChangeMenuGroupInfo(menuGroup.getId(), menuGroup.getName());
    }
}
