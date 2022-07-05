package hiro.kitchenpos.product.domain;

import hiro.kitchenpos.product.domain.exception.ProductNameContainProfanityException;
import hiro.kitchenpos.product.domain.exception.ProductPriceLessThanZeroException;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "product")
@Entity
public class Product {

    @Column(name = "id", columnDefinition = "varbinary(16)")
    @Id
    private UUID id;

    @Embedded
    @Column(name = "name", nullable = false)
    private ProductName name;

    @Embedded
    @Column(name = "price", nullable = false)
    private ProductPrice price;

    public Product(final String name, final BigDecimal price, final PurgomalumClient client) {
        validateName(name, client);
        validatePrice(price);
        this.id = UUID.randomUUID();
        this.name = new ProductName(name);
        this.price = new ProductPrice(price);
    }

    private void validatePrice(BigDecimal price) {
        if (price.compareTo(BigDecimal.ZERO) < 0) {
            throw new ProductPriceLessThanZeroException();
        }
    }

    private void validateName(String name, PurgomalumClient client) {
        if (client.containsProfanity(name)) {
            throw new ProductNameContainProfanityException();
        }
    }

    public void changePrice(final BigDecimal changePrice) {
        validatePrice(changePrice);
        this.price = new ProductPrice(changePrice);
    }

    public void changeName(final String changeName, final PurgomalumClient client) {
        validateName(changeName, client);
        this.name = new ProductName(changeName);
    }
}
