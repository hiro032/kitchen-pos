package hiro.kitchenpos.menu.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class MenuTest {

    @DisplayName("메뉴 생성시 식별자가 할당 된다.")
    @Test
    void create() {
        assertAll(
                () -> assertDoesNotThrow(() ->
                        new Menu("메뉴명"
                                , BigDecimal.TEN
                                , UUID.randomUUID()
                                , Collections.singletonList(new MenuProduct()))),
                () -> assertThat(
                        new Menu("메뉴명"
                        , BigDecimal.TEN
                        , UUID.randomUUID()
                        , Collections.singletonList(new MenuProduct())).getId())
                        .isNotNull()
        );
    }

}
