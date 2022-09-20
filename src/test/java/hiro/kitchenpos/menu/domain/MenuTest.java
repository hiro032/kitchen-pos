package hiro.kitchenpos.menu.domain;

import hiro.kitchenpos.menu.domain.exception.MenuPriceLessThanZeroException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.UUID;

import static hiro.kitchenpos.menu.MenuFixtures.menuProduct;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class MenuTest {

    @DisplayName("메뉴 생성시 식별자가 할당 되고 메뉴가 전시 된다..")
    @Test
    void create() {
        Menu menu = new Menu("메뉴명"
                , BigDecimal.TEN
                , UUID.randomUUID()
                , Collections.singletonList(new MenuProduct()));

        assertAll(
                () -> assertDoesNotThrow(() ->
                        new Menu("메뉴명"
                                , BigDecimal.TEN
                                , UUID.randomUUID()
                                , Collections.singletonList(new MenuProduct()))),

                () -> assertThat(menu.getId()).isNotNull(),

                () -> assertThat(menu.getDisplayed()).isTrue()
        );
    }

    @DisplayName("0보다 작은 금액으로 메뉴 가격 변경시 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(longs = {-100, -1})
    void change_price_under_zero(final long price) {
        assertThatThrownBy(() -> new Menu("치킨 메뉴", BigDecimal.valueOf(price), UUID.randomUUID(), Arrays.asList(menuProduct(), menuProduct())))
                .isInstanceOf(MenuPriceLessThanZeroException.class);
    }

    @DisplayName("메뉴 가격 변경시 메뉴가 포함한 상품의 금액의 합 보다 메뉴의 금액이 크다면, 전시 상태가 내려간다.")
    @Test
    void change_price() {
        // Arrange
        MenuProduct menuProduct1 = menuProduct();
        MenuProduct menuProduct2 = menuProduct();

        Menu menu = new Menu("치킨 메뉴", BigDecimal.valueOf(50000), UUID.randomUUID(), Arrays.asList(menuProduct2, menuProduct1));

        // Act
        menu.changePrice(BigDecimal.valueOf(50000));

        // Assert
        assertThat(menu.getDisplayed()).isFalse();
    }

    @DisplayName("메뉴가 포함한 상품의 금액의 합보다 적은 금액으로 메뉴 가격 변경시 전시 상태가 유지 된다.")
    @Test
    void change_price_less_than_products() {
        // Arrange
        MenuProduct menuProduct1 = menuProduct(BigDecimal.valueOf(10000));
        MenuProduct menuProduct2 = menuProduct(BigDecimal.valueOf(20000));

        Menu menu = new Menu("치킨 메뉴", BigDecimal.valueOf(10000), UUID.randomUUID(), Arrays.asList(menuProduct2, menuProduct1));

        // Act
        menu.changePrice(BigDecimal.valueOf(10000));

        // Assert
        assertThat(menu.getDisplayed()).isTrue();
    }
}
