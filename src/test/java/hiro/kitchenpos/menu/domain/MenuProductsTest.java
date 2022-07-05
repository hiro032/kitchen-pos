package hiro.kitchenpos.menu.domain;

import hiro.kitchenpos.menu.domain.exception.MenuProductsEmptyException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class MenuProductsTest {

    @DisplayName("메뉴 제품 목록 생성")
    @Test
    void create_menu_products() {
        MenuProduct menuProduct = new MenuProduct(UUID.randomUUID(), 3);
        assertAll(
                () -> assertDoesNotThrow(() -> new MenuProducts(List.of(menuProduct))),
                () -> assertThat(new MenuProducts(List.of(menuProduct))).isInstanceOf(MenuProducts.class)
        );
    }

    @DisplayName("빈 컬렉션으로 메뉴 제품 목록을 생성시 예외가 발생한다.")
    @Test
    void create_empty_list() {
        assertThatThrownBy(() -> new MenuProducts(Collections.emptyList())).isInstanceOf(MenuProductsEmptyException.class);
    }
}
