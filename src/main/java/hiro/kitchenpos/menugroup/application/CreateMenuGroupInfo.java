package hiro.kitchenpos.menugroup.application;

import hiro.kitchenpos.menugroup.domain.MenuGroup;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateMenuGroupInfo {

    private UUID id;
    private String name;

    public static CreateMenuGroupInfo toEntity(final MenuGroup entity) {
        return new CreateMenuGroupInfo(entity.getId(), entity.getName());
    }
}
