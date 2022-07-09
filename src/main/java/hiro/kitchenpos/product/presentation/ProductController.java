package hiro.kitchenpos.product.presentation;

import hiro.kitchenpos.product.application.ProductService;
import hiro.kitchenpos.product.application.dtos.ChangeProductInfo;
import hiro.kitchenpos.product.application.dtos.CreateProductInfo;
import hiro.kitchenpos.product.presentation.dtos.*;
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
    public ResponseEntity<CreateProductResponse> createProduct(@RequestBody @Valid final CreateProductRequest request) {
        CreateProductInfo info = productService.create(request.getName(), request.getPrice());

        CreateProductResponse response = CreateProductResponse.toInfo(info);

        return ResponseEntity.created(URI.create("/api/products/" + response.getId())).body(response);
    }

    @PutMapping("/{id}/name")
    public ResponseEntity<ChangeProductResponse> changeProductName(@RequestBody @Valid final ChangeProductNameRequest request, @PathVariable final UUID id) {
        ChangeProductInfo productInfo = productService.changeName(id, request.getName());

        return ResponseEntity.ok(ChangeProductResponse.fromProductInfo(productInfo));
    }

    @PutMapping("/{id}/price")
    public ResponseEntity<ChangeProductResponse> changeProductPrice(@RequestBody @Valid final ChangeProductPriceRequest request, @PathVariable final UUID id) {
        ChangeProductInfo productInfo = productService.changePrice(id, request.getPrice());

        return ResponseEntity.ok(ChangeProductResponse.fromProductInfo(productInfo));
    }

}
