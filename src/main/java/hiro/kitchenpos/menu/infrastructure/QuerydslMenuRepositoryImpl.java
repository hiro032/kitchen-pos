package hiro.kitchenpos.menu.infrastructure;

import com.querydsl.jpa.impl.JPAQueryFactory;
import hiro.kitchenpos.menu.domain.Menu;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

import static hiro.kitchenpos.menu.domain.QMenu.menu;

@RequiredArgsConstructor
@Repository
public class QuerydslMenuRepositoryImpl implements QuerydslMenuRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Menu> findAllContainProduct(UUID productId) {
        return queryFactory
                .selectFrom(menu)
                .where(menu.menuProducts.products.any().product.id.eq(productId))
                .fetch();
    }

}
