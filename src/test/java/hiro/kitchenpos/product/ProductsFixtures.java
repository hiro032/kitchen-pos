package hiro.kitchenpos.product;

import hiro.kitchenpos.product.domain.InmemoryPurgomalumClient;
import hiro.kitchenpos.product.domain.Product;
import hiro.kitchenpos.product.domain.PurgomalumClient;
import hiro.kitchenpos.product.presentation.dtos.CreateProductRequest;

import java.math.BigDecimal;

public class ProductsFixtures {

    private static final String DEFAULT_PRODUCT_NAME = "치킨";
    private static final BigDecimal DEFAULT_PRODUCT_PRICE = BigDecimal.valueOf(10000);
    private static final PurgomalumClient PURGOMALUM_CLIENT = new InmemoryPurgomalumClient();

    public static Product product() {
        return new Product(DEFAULT_PRODUCT_NAME, DEFAULT_PRODUCT_PRICE, PURGOMALUM_CLIENT);
    }

    public static Product product(final BigDecimal price) {
        return new Product(DEFAULT_PRODUCT_NAME, price, PURGOMALUM_CLIENT);
    }

    public static Product product(final String name) {
        return new Product(name, DEFAULT_PRODUCT_PRICE, PURGOMALUM_CLIENT);
    }

    public static CreateProductRequest createProductRequest() {
        return new CreateProductRequest(DEFAULT_PRODUCT_NAME, DEFAULT_PRODUCT_PRICE);
    }

    public static CreateProductRequest createProductRequest(final String name) {
        return new CreateProductRequest(name, DEFAULT_PRODUCT_PRICE);
    }
}
