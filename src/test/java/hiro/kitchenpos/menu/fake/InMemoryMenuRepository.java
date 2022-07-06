package hiro.kitchenpos.menu.fake;

import hiro.kitchenpos.menu.domain.Menu;
import hiro.kitchenpos.menu.domain.MenuRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class InMemoryMenuRepository implements MenuRepository {

    private final Map<UUID, Menu> menus = new HashMap<>();

    @Override
    public Menu save(Menu menu) {
        menus.put(menu.getId(), menu);
        return menu;
    }
}
