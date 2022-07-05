package hiro.kitchenpos.menugroup.presentation.dtos;

import hiro.kitchenpos.menugroup.application.dtos.ChangeMenuGroupInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChangeMenuGroupResponse {

    private UUID id;
    private String name;

    public static ChangeMenuGroupResponse fromInfo(ChangeMenuGroupInfo info) {
        return new ChangeMenuGroupResponse(info.getId(), info.getName());
    }
}
