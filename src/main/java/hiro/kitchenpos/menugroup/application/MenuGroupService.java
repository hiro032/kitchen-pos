package hiro.kitchenpos.menugroup.application;

import hiro.kitchenpos.menugroup.domain.MenuGroup;
import hiro.kitchenpos.menugroup.domain.MenuGroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class MenuGroupService {

    private final MenuGroupRepository repository;

    public UUID create(final String name) {
        MenuGroup menuGroup = new MenuGroup(name);
        MenuGroup entity = repository.save(menuGroup);

        return entity.getId();
    }
}
