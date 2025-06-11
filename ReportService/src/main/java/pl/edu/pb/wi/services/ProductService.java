package pl.edu.pb.wi.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.edu.pb.wi.dtos.ProductReportDto;
import pl.edu.pb.wi.repositories.ProductRepository;
import reactor.core.publisher.Flux;

import java.time.Duration;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public Flux<ProductReportDto> getProducts() {
        return productRepository.findProductReports()
                .delayElements(Duration.ofSeconds(1));
    }
}
