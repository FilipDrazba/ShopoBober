package pl.edu.pb.wi.shopservice.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.edu.pb.wi.shopservice.dtos.ProductDto;
import pl.edu.pb.wi.shopservice.services.ProductService;

import java.util.List;

@RestController
@RequestMapping("shop/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping("all")
    @PreAuthorize("hasRole('CUSTOMER') " +
            "or hasRole('EMPLOYEE')" +
            "or hasRole('UNVERIFIED_EMPLOYEE')")
    public ResponseEntity<List<ProductDto>> getProducts() {
        return ResponseEntity
                .ok(productService.getAllProducts());
    }

    @GetMapping("{id}")
    @PreAuthorize("hasRole('CUSTOMER') " +
            "or hasRole('EMPLOYEE')" +
            "or hasRole('UNVERIFIED_EMPLOYEE')")
    public ResponseEntity<ProductDto> getProductById(@PathVariable Long id) {
        return ResponseEntity
                .ok(productService.getProductById(id));
    }

    @PostMapping
    @PreAuthorize("hasRole('EMPLOYEE')")
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDto) {
        var createdProductDto = productService.createProduct(productDto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(createdProductDto);
    }

    @PutMapping("{id}")
    @PreAuthorize("hasRole('EMPLOYEE')")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable Long id,
                                                    @RequestBody ProductDto productDto) {
        var updatedProduct = productService.updateProduct(id, productDto);

        return ResponseEntity
                .ok(updatedProduct);
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasRole('EMPLOYEE')")
    public ResponseEntity<ProductDto> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity
                .noContent()
                .build();
    }
}
