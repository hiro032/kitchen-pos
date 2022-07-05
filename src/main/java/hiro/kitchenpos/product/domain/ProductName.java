package hiro.kitchenpos.product.domain;

import lombok.*;

import javax.persistence.Embeddable;

@Getter
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Embeddable
public class ProductName {

    private String name;

}
