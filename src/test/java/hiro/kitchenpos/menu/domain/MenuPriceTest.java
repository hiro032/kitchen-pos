package hiro.kitchenpos.menu.domain;

import hiro.kitchenpos.menu.domain.exception.MenuPriceLessThanZeroException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class MenuPriceTest {

    @DisplayName("메뉴 가격 생성")
    @Test
    void create() {
        assertAll(
                () -> assertDoesNotThrow(() -> new MenuPrice(BigDecimal.TEN)),
                () -> assertThat(new MenuPrice(BigDecimal.TEN)).isInstanceOf(MenuPrice.class)
        );
    }

    @DisplayName("0 미만의 가격으로 메뉴 가격을 생성시 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(longs = {-1000, -1})
    void menu_price_less_than_zero(final long price) {
        assertThatThrownBy(() -> new MenuPrice(BigDecimal.valueOf(price)))
                .isInstanceOf(MenuPriceLessThanZeroException.class);
    }
}
