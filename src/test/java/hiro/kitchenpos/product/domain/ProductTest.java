package hiro.kitchenpos.product.domain;

import hiro.kitchenpos.product.domain.exception.ProductNameContainProfanityException;
import hiro.kitchenpos.product.domain.exception.ProductPriceLessThanZeroException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

    @DisplayName("Product 생성시 식별자가 할당 된다.")
    @Test
    void create_product() {
        Product product = new Product("치킨", BigDecimal.valueOf(20000), new InmemoryPurgomalumClient());

        assertThat(product.getId()).isNotNull();
    }

    @DisplayName("비속어가 포함된 이름으로 Product 생성시 예외가 발생한다.")
    @Test
    void contain_profanity() {
        assertThatThrownBy(() -> new Product("비속어", BigDecimal.valueOf(20000), new InmemoryPurgomalumClient()))
                .isInstanceOf(ProductNameContainProfanityException.class);
    }

    @DisplayName("상품의 금액을 0 보다 적은 금액으로 생성시 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(longs = {-100, -1})
    void price_less_than_zero(final long price) {
        assertThatThrownBy(() -> new Product("치킨", BigDecimal.valueOf(price), new InmemoryPurgomalumClient()))
                .isInstanceOf(ProductPriceLessThanZeroException.class);
    }

    @DisplayName("상품의 가격을 변경할 수 있다.")
    @Test
    void change_product_price() {
        // Arrange
        Product product = new Product("치킨", BigDecimal.valueOf(20000), new InmemoryPurgomalumClient());

        BigDecimal changePrice = BigDecimal.valueOf(10000);

        // Act
        product.changePrice(changePrice);

        // Assert
        assertThat(product.getPrice()).isEqualTo(new ProductPrice(changePrice));
    }

    @DisplayName("변경하려는 상품의 가격이 0 보다 적은 금액이라면 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(longs = {-100, -1})
    void change_price_less_than_zero(final long price) {
        Product product = new Product("치킨", BigDecimal.valueOf(20000), new InmemoryPurgomalumClient());

        BigDecimal changePrice = BigDecimal.valueOf(price);

        assertThatThrownBy(() -> product.changePrice(changePrice))
                .isInstanceOf(ProductPriceLessThanZeroException.class);
    }

    @DisplayName("상품의 이름을 변경할 수 있다.")
    @Test
    void change_product_name() {
        // Arrange
        Product product = new Product("치킨", BigDecimal.valueOf(20000), new InmemoryPurgomalumClient());

        // Act
        product.changeName("양념 치킨", new InmemoryPurgomalumClient());

        // Assert
        assertThat(product.getName()).isEqualTo(new ProductName("양념 치킨"));
    }

    @DisplayName("변경하려는 상품 이름에 비속어가 포함된 경우 예외가 발생한다.")
    @Test
    void change_product_name_contain_profanity() {
        Product product = new Product("치킨", BigDecimal.valueOf(20000), new InmemoryPurgomalumClient());

        assertThatThrownBy(() -> product.changeName("비속어", new InmemoryPurgomalumClient()))
                .isInstanceOf(ProductNameContainProfanityException.class);
    }
}
