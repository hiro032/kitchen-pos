package hiro.kitchenpos.menu.fake;

import hiro.kitchenpos.menu.domain.Menu;
import hiro.kitchenpos.menu.domain.MenuRepository;

import java.util.*;

public class InMemoryMenuRepository implements MenuRepository {

    private final Map<UUID, Menu> menus = new HashMap<>();

    @Override
    public Menu save(Menu menu) {
        menus.put(menu.getId(), menu);
        return menu;
    }

    // FIXME
    @Override
    public List<Menu> findAllContainProduct(UUID id) {
        return null;
    }

    @Override
    public Optional<Menu> findById(UUID id) {
        return menus.values().stream()
                .filter(menu -> menu.getId().equals(id))
                .findFirst();
    }

    @Override
    public boolean existsById(UUID menuId) {
        return menus.values().stream()
                .anyMatch(menu -> menu.getId().equals(menuId));
    }

}
