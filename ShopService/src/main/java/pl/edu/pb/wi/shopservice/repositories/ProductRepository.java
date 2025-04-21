package pl.edu.pb.wi.shopservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.pb.wi.shopservice.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
