package hiro.kitchenpos.menugroup.fake;

import hiro.kitchenpos.menugroup.domain.MenuGroup;
import hiro.kitchenpos.menugroup.domain.MenuGroupRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public class InMemoryMenuGroupRepository implements MenuGroupRepository {

    private final Map<UUID, MenuGroup> menuGroups = new HashMap<>();

    @Override
    public MenuGroup save(MenuGroup menuGroup) {
        menuGroups.put(menuGroup.getId(), menuGroup);
        return menuGroup;
    }

    @Override
    public Optional<MenuGroup> findById(UUID id) {
        return menuGroups.values().stream()
                .filter(menuGroup -> menuGroup.getId().equals(id))
                .findFirst();
    }

    @Override
    public boolean existsById(UUID id) {
        return menuGroups.values().stream()
                .anyMatch(menuGroup -> menuGroup.getId().equals(id));
    }
}
