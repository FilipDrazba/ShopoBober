package pl.edu.pb.wi.repositories;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import pl.edu.pb.wi.dtos.ProductReportDto;
import pl.edu.pb.wi.entities.Product;
import reactor.core.publisher.Flux;

public interface ProductRepository extends ReactiveCrudRepository<Product, Long> {

    @Query("""
                SELECT p.id,
                       p.name,
                       p.description,
                       COUNT(cp.user_email) AS cart_count,
                       COALESCE(SUM(cp.quantity),0) AS total_cart_count
                FROM products p
                         LEFT JOIN cart_product cp ON p.id = cp.product_id
                GROUP BY p.id, p.name, p.description
                ORDER BY p.id;
            """)
    Flux<ProductReportDto> findProductReports();
}
