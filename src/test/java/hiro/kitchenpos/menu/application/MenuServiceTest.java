package hiro.kitchenpos.menu.application;

import hiro.kitchenpos.menu.application.dtos.CreateMenuCommand;
import hiro.kitchenpos.menu.application.dtos.CreateMenuProductCommand;
import hiro.kitchenpos.menu.application.dtos.MenuInfo;
import hiro.kitchenpos.menu.domain.Menu;
import hiro.kitchenpos.menu.domain.MenuProduct;
import hiro.kitchenpos.menu.domain.MenuRepository;
import hiro.kitchenpos.menu.domain.exception.MenuNotFoundException;
import hiro.kitchenpos.menu.domain.exception.MenuPriceOverThanProductsPriceException;
import hiro.kitchenpos.menu.fake.InMemoryMenuRepository;
import hiro.kitchenpos.menugroup.domain.MenuGroup;
import hiro.kitchenpos.menugroup.domain.MenuGroupRepository;
import hiro.kitchenpos.menugroup.exception.MenuGroupNotFoundException;
import hiro.kitchenpos.menugroup.fake.InMemoryMenuGroupRepository;
import hiro.kitchenpos.product.domain.InmemoryPurgomalumClient;
import hiro.kitchenpos.product.domain.Product;
import hiro.kitchenpos.product.domain.ProductRepository;
import hiro.kitchenpos.product.domain.exception.ProductNotFoundException;
import hiro.kitchenpos.product.fake.InMemoryProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MenuServiceTest {

    private MenuService menuService;
    private MenuRepository menuRepository;
    private MenuGroupRepository menuGroupRepository;
    private ProductRepository productRepository;

    @BeforeEach
    void setUp() {
        menuRepository = new InMemoryMenuRepository();
        menuGroupRepository = new InMemoryMenuGroupRepository();
        productRepository = new InMemoryProductRepository();

        menuService = new MenuService(menuRepository, menuGroupRepository, productRepository);
    }

    /**
     * Arrange
     * 메뉴 그룹이 존재함
     * 제품이 존재함
     *
     * Act
     * 등록 되어있는 메뉴 그룹, 제품, 수량을 통해 메뉴 생성 요청
     *
     * Assert
     * 메뉴가 등록되고 식별자가 할당된다.
     */
    @DisplayName("메뉴 생성")
    @Test
    void create() {
        // Arrange
        MenuGroup menuGroup = new MenuGroup("한식");
        Product product = new Product("치킨"
                , BigDecimal.valueOf(10000)
                , new InmemoryPurgomalumClient());

        UUID menuGroupId = menuGroupRepository.save(menuGroup).getId();
        UUID productId = productRepository.save(product).getId();

        CreateMenuProductCommand createMenuProductCommand = new CreateMenuProductCommand(productId, 3);
        CreateMenuCommand createMenuCommand = new CreateMenuCommand("치킨 메뉴", BigDecimal.valueOf(30000), menuGroupId, Collections.singletonList(createMenuProductCommand));

        // Act
        assertThat(menuService.create(createMenuCommand)).isNotNull();
    }

    /**
     * Arrange
     *
     * Act
     * 등록 되어 있지 않은 메뉴 그룹을 통해 메뉴 생성 요청시
     *
     * Assert
     * 에러 발생
     */
    @DisplayName("등록되어있지 않은 메뉴 그룹을 통해 메뉴 생성 요청시 예외")
    @Test
    void not_found_menu_group() {
        Product product = new Product("치킨"
                , BigDecimal.valueOf(10000)
                , new InmemoryPurgomalumClient());

        UUID productId = productRepository.save(product).getId();

        CreateMenuProductCommand createMenuProductCommand = new CreateMenuProductCommand(productId, 3);

        UUID menuGroupId = UUID.randomUUID();
        CreateMenuCommand createMenuCommand = new CreateMenuCommand("치킨 메뉴", BigDecimal.valueOf(30000), menuGroupId, Collections.singletonList(createMenuProductCommand));

        assertThatThrownBy(() -> menuService.create(createMenuCommand)).isInstanceOf(MenuGroupNotFoundException.class);
    }

    @DisplayName("등록되어있지 않은 상품을 통해 메뉴 생성 요청시 예외")
    @Test
    void not_found_product() {
        // Arrange
        MenuGroup menuGroup = new MenuGroup("한식");

        UUID menuGroupId = menuGroupRepository.save(menuGroup).getId();

        CreateMenuProductCommand createMenuProductCommand = new CreateMenuProductCommand(UUID.randomUUID(), 3);

        CreateMenuCommand createMenuCommand = new CreateMenuCommand("치킨 메뉴", BigDecimal.valueOf(50000), menuGroupId, Collections.singletonList(createMenuProductCommand));

        //  Act Assert
        assertThatThrownBy(() -> menuService.create(createMenuCommand))
                .isInstanceOf(ProductNotFoundException.class);
    }

    @DisplayName("메뉴의 가격이 메뉴에 포함된 메뉴 상품의 가격의 합보다 클 경우 예외")
    @Test
    void menu_price_over_than_products() {
        // Arrange
        MenuGroup menuGroup = new MenuGroup("한식");
        Product product = new Product("치킨"
                , BigDecimal.valueOf(10000)
                , new InmemoryPurgomalumClient());

        UUID menuGroupId = menuGroupRepository.save(menuGroup).getId();
        UUID productId = productRepository.save(product).getId();

        CreateMenuProductCommand createMenuProductCommand = new CreateMenuProductCommand(productId, 3);
        CreateMenuCommand createMenuCommand = new CreateMenuCommand("치킨 메뉴", BigDecimal.valueOf(50000), menuGroupId, Collections.singletonList(createMenuProductCommand));

        // Act
        assertThatThrownBy(() -> menuService.create(createMenuCommand))
                .isInstanceOf(MenuPriceOverThanProductsPriceException.class);
    }

    @DisplayName("존재하지 않는 메뉴 id를 통해 메뉴 가격 변경 요청시 예외")
    @Test
    void change_price_not_found_menu_id() {
        assertThatThrownBy(() -> menuService.changePrice(UUID.randomUUID(), BigDecimal.ONE))
                .isInstanceOf(MenuNotFoundException.class);
    }

    @DisplayName("메뉴 가격 변경 성공시, 변경된 메뉴 정보를 응답 한다.")
    @Test
    void change_menu_price() {
        UUID menuId = 메뉴_등록();
        MenuInfo menuInfo = menuService.changePrice(menuId, BigDecimal.valueOf(5000));

        assertThat(menuInfo.getPrice()).isEqualTo(BigDecimal.valueOf(5000));
    }

    private UUID 메뉴_등록() {
        MenuGroup menuGroup = new MenuGroup("한식");
        Product product = new Product("치킨"
                , BigDecimal.valueOf(10000)
                , new InmemoryPurgomalumClient());

        MenuProduct menuProduct = new MenuProduct(product.getId(), 1, product.getPrice().getPrice());

        Menu menu = new Menu("치킨", BigDecimal.valueOf(10000), menuGroup.getId(), Collections.singletonList(menuProduct));

        menuRepository.save(menu);

        return menu.getId();
    }
}
