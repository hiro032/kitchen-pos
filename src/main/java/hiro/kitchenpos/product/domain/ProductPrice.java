package hiro.kitchenpos.product.domain;

import lombok.*;

import javax.persistence.Embeddable;
import java.math.BigDecimal;

@Getter
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Embeddable
public class ProductPrice {

    private BigDecimal price;

}
