package hiro.kitchenpos.menu.domain;

import hiro.kitchenpos.menu.domain.exception.MenuNameEmptyException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class MenuNameTest {

    @DisplayName("메뉴 이름 생성 테스트")
    @Test
    void create() {
        assertAll(
                () -> assertDoesNotThrow(() -> new MenuName("메뉴명")),
                () -> assertThat(new MenuName("메뉴명")).isInstanceOf(MenuName.class)
        );
    }

    @DisplayName("null, 공백으로 메뉴 이름 생성시 예외가 발생한다.")
    @ParameterizedTest
    @NullAndEmptySource
    void create_null_empty(final String name) {
        assertThatThrownBy(() -> new MenuName(name)).isInstanceOf(MenuNameEmptyException.class);
    }

}
