package pl.edu.pb.wi.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.pb.wi.dtos.ProductReportDto;
import pl.edu.pb.wi.services.ProductService;
import reactor.core.publisher.Flux;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/reports/get")
    public Flux<ProductReportDto> streamProducts() {
        return productService.getProducts();
    }
}
