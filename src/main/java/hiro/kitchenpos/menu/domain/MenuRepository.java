package hiro.kitchenpos.menu.domain;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MenuRepository {

    Menu save(Menu menu);

    List<Menu> findAllContainProduct(UUID productId);

    Optional<Menu> findById(UUID id);
}
