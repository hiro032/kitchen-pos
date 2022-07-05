package hiro.kitchenpos.menu.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "menus")
@Entity
public class Menu {

    @Column(name = "id", columnDefinition = "varbinary(16)")
    @Id
    private UUID id;

    @Column(name = "name", nullable = false)
    @Embedded
    private MenuName name;

    @Column(name = "price", nullable = false)
    @Embedded
    private MenuPrice price;

    @Column(nullable = false)
    private UUID menuGroupId;

    @Embedded
    private MenuProducts menuProducts;

    public Menu(String name, BigDecimal price, UUID menuGroupId, List<MenuProduct> menuProducts) {
        this.id = UUID.randomUUID();
        this.name = new MenuName(name);
        this.price = new MenuPrice(price);
        this.menuGroupId = menuGroupId;
        this.menuProducts = new MenuProducts(menuProducts);
    }

}
