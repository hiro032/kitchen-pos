package hiro.kitchenpos.menugroup.domain;

import java.util.Optional;
import java.util.UUID;

public interface MenuGroupRepository {
    MenuGroup save(MenuGroup menuGroup);

    Optional<MenuGroup> findById(UUID id);

    boolean existsById(UUID id);
}
