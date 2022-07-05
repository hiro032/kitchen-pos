package hiro.kitchenpos.product.application;

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
}
