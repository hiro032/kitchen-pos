package hiro.kitchenpos.menu.application;

import hiro.kitchenpos.menu.application.dtos.CreateMenuCommand;
import hiro.kitchenpos.menu.application.dtos.CreateMenuProductCommand;
import hiro.kitchenpos.menu.domain.Menu;
import hiro.kitchenpos.menu.domain.MenuProduct;
import hiro.kitchenpos.menu.domain.MenuRepository;
import hiro.kitchenpos.menugroup.domain.MenuGroupRepository;
import hiro.kitchenpos.product.domain.Product;
import hiro.kitchenpos.product.domain.ProductRepository;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class MenuService {

    private final MenuRepository menuRepository;
    private final MenuGroupRepository menuGroupRepository;
    private final ProductRepository productRepository;

    public UUID create(final CreateMenuCommand command) {
        List<UUID> productIds = command.getCreateMenuProductCommands().stream()
                .map(CreateMenuProductCommand::getProductId)
                .collect(Collectors.toList());

        List<Product> products = productRepository.findAllByIdIn(productIds);

        validateProductIds(command.getCreateMenuProductCommands(), products);
        validateMenuGroupId(command.getMenuGroupId());

        Menu menu = new Menu(command.getName(),
                command.getPrice(),
                command.getMenuGroupId(),
                command.getCreateMenuProductCommands().stream()
                        .map(createMenuProductCommand -> new MenuProduct(
                                createMenuProductCommand.getProductId(),
                                createMenuProductCommand.getQuantity()))
                        .collect(Collectors.toList()));

        Menu entity = menuRepository.save(menu);

        return entity.getId();
    }

    private void validateMenuGroupId(UUID menuGroupId) {
        if (!menuGroupRepository.existById(menuGroupId)) {
            throw new IllegalArgumentException();
        }
    }

    private void validateProductIds(List<CreateMenuProductCommand> commands, List<Product> products) {
        if (commands.size() != products.size()) {
            throw new IllegalArgumentException();
        }
    }

}
