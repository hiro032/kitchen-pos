package hiro.kitchenpos.menu.domain;

import hiro.kitchenpos.menu.domain.exception.MenuPriceLessThanZeroException;
import lombok.*;

import javax.persistence.Embeddable;
import java.math.BigDecimal;

@Getter
@Embeddable
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MenuPrice {

    private BigDecimal price;

    public MenuPrice(BigDecimal price) {
        validateMenuPrice(price);
        this.price = price;
    }

    private void validateMenuPrice(BigDecimal price) {
        if (price.compareTo(BigDecimal.ZERO) < 0) {
            throw new MenuPriceLessThanZeroException();
        }
    }
}
