package hiro.kitchenpos.menu.application;

import hiro.kitchenpos.menu.application.dtos.CreateMenuCommand;
import hiro.kitchenpos.menu.application.dtos.CreateMenuInfo;
import hiro.kitchenpos.menu.application.dtos.CreateMenuProductCommand;
import hiro.kitchenpos.menu.application.dtos.MenuInfo;
import hiro.kitchenpos.menu.domain.Menu;
import hiro.kitchenpos.menu.domain.MenuProduct;
import hiro.kitchenpos.menu.domain.MenuRepository;
import hiro.kitchenpos.menu.domain.exception.MenuNotFoundException;
import hiro.kitchenpos.menu.domain.exception.MenuPriceOverThanProductsPriceException;
import hiro.kitchenpos.menugroup.domain.MenuGroupRepository;
import hiro.kitchenpos.menugroup.exception.MenuGroupNotFoundException;
import hiro.kitchenpos.product.domain.Product;
import hiro.kitchenpos.product.domain.ProductRepository;
import hiro.kitchenpos.product.domain.exception.ProductNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional
@Service
public class MenuService {

    private final MenuRepository menuRepository;
    private final MenuGroupRepository menuGroupRepository;
    private final ProductRepository productRepository;

    public CreateMenuInfo create(final CreateMenuCommand command) {

        validateMenuGroupId(command.getMenuGroupId());
        validateProductIds(command.getCreateMenuProductCommands());
        validateMenuPrice(command.getPrice(), command.getCreateMenuProductCommands());

        Menu menu = new Menu(command.getName(),
                command.getPrice(),
                command.getMenuGroupId(),
                command.getCreateMenuProductCommands().stream()
                        .map(createMenuProductCommand -> new MenuProduct(
                                productRepository.findById(
                                createMenuProductCommand.getProductId()
                                ).orElseThrow(ProductNotFoundException::new),
                                createMenuProductCommand.getQuantity()))
                        .collect(Collectors.toList()));

        Menu entity = menuRepository.save(menu);

        return CreateMenuInfo.toEntity(entity);
    }

    private void validateMenuGroupId(UUID menuGroupId) {
        if (!menuGroupRepository.existsById(menuGroupId)) {
            throw new MenuGroupNotFoundException();
        }
    }

    private void validateProductIds(List<CreateMenuProductCommand> commands) {
        List<UUID> productIds = commands.stream()
                .map(CreateMenuProductCommand::getProductId)
                .collect(Collectors.toList());

        List<Product> products = productRepository.findAllByIdIn(productIds);

        if (commands.size() != products.size()) {
            throw new ProductNotFoundException();
        }
    }

    private void validateMenuPrice(BigDecimal menuPrice, List<CreateMenuProductCommand> createMenuProductCommands) {
        BigDecimal menuProductsPrice = BigDecimal.ZERO;

        for (CreateMenuProductCommand createMenuProductCommand : createMenuProductCommands) {
            Product product = productRepository.findById(createMenuProductCommand.getProductId())
                    .orElseThrow(IllegalArgumentException::new);

            BigDecimal menuProductPrice = product.getPrice().getPrice().multiply(BigDecimal.valueOf(createMenuProductCommand.getQuantity()));
            menuProductsPrice = menuProductsPrice.add(menuProductPrice);
        }

        if (menuPrice.compareTo(menuProductsPrice) > 0) {
            throw new MenuPriceOverThanProductsPriceException();
        }
    }

    public MenuInfo changePrice(final UUID id, final BigDecimal changePrice) {
        Menu menu = menuRepository.findById(id)
                .orElseThrow(MenuNotFoundException::new);

        menu.changePrice(changePrice);

        return MenuInfo.builder()
                .id(menu.getId())
                .price(menu.getPrice().getPrice())
                .build();
    }
}
