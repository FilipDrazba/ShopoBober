package pl.edu.pb.wi.shopservice.entities;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
public class CartProductId implements Serializable {
    private Long productId;
    private String userEmail;

    public CartProductId(Long productId, String userEmail) {
        this.productId = productId;
        this.userEmail = userEmail;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        CartProductId that = (CartProductId) o;
        return Objects.equals(productId, that.productId) && Objects.equals(userEmail, that.userEmail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, userEmail);
    }
}