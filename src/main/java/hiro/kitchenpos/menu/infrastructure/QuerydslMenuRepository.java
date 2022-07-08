package hiro.kitchenpos.menu.infrastructure;

import hiro.kitchenpos.menu.domain.Menu;

import java.util.List;
import java.util.UUID;

public interface QuerydslMenuRepository {

    List<Menu> findAllContainProduct(UUID productId);

}
