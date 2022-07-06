package hiro.kitchenpos.product.fake;

import hiro.kitchenpos.product.domain.Product;
import hiro.kitchenpos.product.domain.ProductRepository;

import java.util.*;
import java.util.stream.Collectors;

public class InMemoryProductRepository implements ProductRepository {

    private final Map<UUID, Product> products = new HashMap<>();

    @Override
    public Product save(Product product) {
        products.put(product.getId(), product);
        return product;
    }

    @Override
    public Optional<Product> findById(UUID id) {
        return products.values().stream()
                .filter(product -> product.getId().equals(id))
                .findFirst();
    }

    @Override
    public List<Product> findAllByIdIn(List<UUID> ids) {
        return products.values().stream()
                .filter(product -> ids.contains(product.getId()))
                .collect(Collectors.toList());
    }
}
