package hiro.kitchenpos.menu.domain;

import hiro.kitchenpos.menu.domain.exception.MenuProductQuantityException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class MenuProductTest {

    @DisplayName("MenuProduct 생성")
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 10})
    void create(final int quantity) {
        assertDoesNotThrow(() -> new MenuProduct(UUID.randomUUID(), quantity));
    }

    @DisplayName("MenuProduct 의 quantity 값이 0이하인 경우 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(ints = {-10, -1, 0})
    void menu_product_quantity_less_than_zero(final int quantity) {
        assertThatThrownBy(()-> new MenuProduct(UUID.randomUUID(), quantity))
                .isInstanceOf(MenuProductQuantityException.class);
    }
}
