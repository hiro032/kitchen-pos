package hiro.kitchenpos.menu.domain;

import hiro.kitchenpos.menu.domain.exception.MenuGroupNameException;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;
import java.util.UUID;

@Getter
@Table(name = "menu_group")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class MenuGroup {

    @Column(name = "id", columnDefinition = "varbinary(16)")
    @Id
    private UUID id;

    @Column(name = "name", nullable = false)
    private String name;

    public MenuGroup(final String name) {
        validateMenuGroupName(name);
        this.id = UUID.randomUUID();
        this.name = name;
    }

    private void validateMenuGroupName(String name) {
        if (Objects.isNull(name)) {
            throw new MenuGroupNameException();
        }
        if (name.isBlank()) {
            throw new MenuGroupNameException();
        }
    }
}
