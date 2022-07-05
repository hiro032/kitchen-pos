package hiro.kitchenpos.menu.infrastructure;

import hiro.kitchenpos.menu.domain.Menu;
import hiro.kitchenpos.menu.domain.MenuRepository;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaMenuRepository extends MenuRepository, JpaRepository<Menu, UUID> {

}
