package hiro.kitchenpos.menu.domain;

import hiro.kitchenpos.menu.domain.exception.MenuGroupNameException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MenuGroupTest {

    @DisplayName("메뉴 그룹의 이름이 null & empty 인 경우 예외가 발생한다.")
    @ParameterizedTest
    @NullAndEmptySource
    void menu_group_name_null_or_empty(String  name) {
        assertThatThrownBy(() -> new MenuGroup(name))
                .isInstanceOf(MenuGroupNameException.class);
    }
}
