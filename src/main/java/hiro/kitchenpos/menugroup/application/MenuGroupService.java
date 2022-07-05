package hiro.kitchenpos.menugroup.application;

import hiro.kitchenpos.menugroup.application.dtos.ChangeMenuGroupInfo;
import hiro.kitchenpos.menugroup.domain.MenuGroup;
import hiro.kitchenpos.menugroup.domain.MenuGroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
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

    public ChangeMenuGroupInfo change(final UUID id, final String changeName) {
        MenuGroup entity = repository.findById(id)
                .orElseThrow(EntityNotFoundException::new);

        entity.changeMenuGroupName(changeName);

        return ChangeMenuGroupInfo.fromEntity(entity);
    }
}
