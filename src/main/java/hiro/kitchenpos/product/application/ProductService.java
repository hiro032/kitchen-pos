package hiro.kitchenpos.product.application;

import hiro.kitchenpos.product.application.dtos.ChangeProductInfo;
import hiro.kitchenpos.product.domain.Product;
import hiro.kitchenpos.product.domain.ProductRepository;
import hiro.kitchenpos.product.domain.PurgomalumClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.UUID;

@RequiredArgsConstructor
@Transactional
@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final PurgomalumClient purgomalumClient;

    public UUID create(final String name, final BigDecimal price) {
        Product product = new Product(name, price, purgomalumClient);
        Product entity = productRepository.save(product);

        return entity.getId();
    }

    public ChangeProductInfo changeName(final UUID id, final String changeName) {
        Product product = productRepository.findById(id)
                .orElseThrow();

        product.changeName(changeName, purgomalumClient);

        return ChangeProductInfo.fromEntity(product);
    }

    public ChangeProductInfo changePrice(final UUID id, final BigDecimal changePrice) {
        Product product = productRepository.findById(id)
                .orElseThrow();

        product.changePrice(changePrice);

        return ChangeProductInfo.fromEntity(product);
    }
}
