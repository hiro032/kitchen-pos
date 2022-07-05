package hiro.kitchenpos.menugroup.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import hiro.kitchenpos.menugroup.domain.exception.MenuGroupNameException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

class MenuGroupTest {

    @DisplayName("메뉴 그룹의 이름이 null & empty 인 경우 예외가 발생한다.")
    @ParameterizedTest
    @NullAndEmptySource
    void menu_group_name_null_or_empty(String  name) {
        assertThatThrownBy(() -> new MenuGroup(name))
                .isInstanceOf(MenuGroupNameException.class);
    }

    @DisplayName("메뉴 그룹 생성 테스트")
    @Test
    void create() {
        assertAll(
                () -> assertDoesNotThrow(() -> new MenuGroup("한식")),
                () -> assertThat(new MenuGroup("한식")).isInstanceOf(MenuGroup.class)
        );
    }

    @DisplayName("메뉴 그룹 이름 변경")
    @Test
    void changeName() {
        MenuGroup menuGroup = new MenuGroup("한식");
        String newName = "일식";

        menuGroup.changeMenuGroupName(newName);

        assertThat(menuGroup.getName()).isEqualTo(newName);
    }

}
