package hiro.kitchenpos.menu.domain;

import hiro.kitchenpos.menu.domain.exception.MenuNameEmptyException;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.util.Objects;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class MenuName {

    private String name;

    public MenuName(String name) {
        validateMenuName(name);
        this.name = name;
    }

    private void validateMenuName(final String name) {
        if (Objects.isNull(name) || name.isEmpty()) {
            throw new MenuNameEmptyException();
        }
    }
}
