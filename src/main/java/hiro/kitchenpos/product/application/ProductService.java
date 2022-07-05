package hiro.kitchenpos.product.application;

import hiro.kitchenpos.product.application.dtos.ChangeProductInfo;
import hiro.kitchenpos.product.domain.Product;
import hiro.kitchenpos.product.domain.ProductRepository;
import hiro.kitchenpos.product.domain.PurgomalumClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final PurgomalumClient purgomalumClient;

    public UUID create(final String name, final BigDecimal price) {
        Product product = new Product(name, price, purgomalumClient);
        Product entity = productRepository.save(product);

        return entity.getId();
    }

    public ChangeProductInfo change(UUID id, String name, BigDecimal price) {
        Product product = productRepository.findById(id)
                .orElseThrow();

        product.changeName(name, purgomalumClient);
        product.changePrice(price);

        return ChangeProductInfo.fromEntity(product);
    }
}
