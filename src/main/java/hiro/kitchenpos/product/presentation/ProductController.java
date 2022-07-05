package hiro.kitchenpos.product.presentation;

import hiro.kitchenpos.product.application.ProductService;
import hiro.kitchenpos.product.application.dtos.ChangeProductInfo;
import hiro.kitchenpos.product.presentation.dtos.ChangeProductRequest;
import hiro.kitchenpos.product.presentation.dtos.ChangeProductResponse;
import hiro.kitchenpos.product.presentation.dtos.CreateProductRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.UUID;

@RequestMapping("/api/products")
@AllArgsConstructor
@RestController
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<Void> createProduct(@RequestBody @Valid final CreateProductRequest request) {
        UUID id = productService.create(request.getName(), request.getPrice());

        return ResponseEntity.created(URI.create("/api/products/" + id)).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ChangeProductResponse> changeProductInfo(@RequestBody @Valid final ChangeProductRequest request, @PathVariable final UUID id) {
        ChangeProductInfo productInfo = productService.change(id, request.getName(), request.getPrice());

        return ResponseEntity.ok(ChangeProductResponse.fromProductInfo(productInfo));
    }
}
