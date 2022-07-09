package hiro.kitchenpos.menugroup;

import hiro.kitchenpos.menugroup.presentation.dtos.CreateMenuGroupRequest;

public class MenuGroupFixtures {

    private static final String DEFAULT_MENU_GROUP_NAME = "치킨+콜리";

    public static CreateMenuGroupRequest createMenuGroupRequest() {
        return new CreateMenuGroupRequest(DEFAULT_MENU_GROUP_NAME);
    }
}
