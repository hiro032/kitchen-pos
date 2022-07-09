package hiro.kitchenpos.menugroup.presentation.dtos;

import hiro.kitchenpos.menugroup.application.CreateMenuGroupInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

public class MenuGroupResponse {

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateMenuGroupResponse {
        private UUID id;
        private String name;

        public static CreateMenuGroupResponse toInfo(final CreateMenuGroupInfo info) {
            return new CreateMenuGroupResponse(info.getId(), info.getName());
        }
    }

}
