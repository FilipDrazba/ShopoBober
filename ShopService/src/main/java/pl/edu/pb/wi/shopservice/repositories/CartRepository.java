package pl.edu.pb.wi.shopservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.pb.wi.shopservice.entities.CartProduct;
import pl.edu.pb.wi.shopservice.entities.CartProductId;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<CartProduct, CartProductId> {
    List<CartProduct> findAllById_UserEmail(String userEmail);
}
