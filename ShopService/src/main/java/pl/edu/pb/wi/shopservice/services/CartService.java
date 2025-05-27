package pl.edu.pb.wi.shopservice.services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.edu.pb.wi.shopservice.dtos.CartProductActionDto;
import pl.edu.pb.wi.shopservice.dtos.CartProductDto;
import pl.edu.pb.wi.shopservice.entities.CartProduct;
import pl.edu.pb.wi.shopservice.entities.CartProductId;
import pl.edu.pb.wi.shopservice.exceptions.ResourceNotFoundException;
import pl.edu.pb.wi.shopservice.mappers.CartMapper;
import pl.edu.pb.wi.shopservice.repositories.CartRepository;
import pl.edu.pb.wi.shopservice.repositories.ProductRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;
    private final CartMapper cartMapper;
    private final ProductRepository productRepository;

    public List<CartProductDto> getCartProducts(String email) {
        return cartRepository.findAllById_UserEmail(email).stream()
                .map(cartMapper::toCartProductDto)
                .toList();
    }

    @Transactional
    public void addToCart(String email, CartProductActionDto cartProductActionDto) {
        var cartProductId = new CartProductId(cartProductActionDto.productId(), email);
        var optionalCartProduct = cartRepository.findById(cartProductId);

        if (optionalCartProduct.isPresent()) {
            var product = optionalCartProduct.get();
            product.setQuantity(product.getQuantity() + cartProductActionDto.quantity());
            cartRepository.save(product);

        } else {
            var product = productRepository
                    .findById(cartProductActionDto.productId())
                    .orElseThrow(ResourceNotFoundException::new);

            var cartProduct = CartProduct.builder()
                    .id(cartProductId)
                    .product(product)
                    .quantity(cartProductActionDto.quantity())
                    .build();

            cartRepository.save(cartProduct);
        }
    }

    @Transactional
    public void removeFromCart(String email, CartProductActionDto cartProductActionDto) {
        var cartProductId = new CartProductId(cartProductActionDto.productId(), email);
        var cartProduct = cartRepository
                .findById(cartProductId)
                .orElseThrow(ResourceNotFoundException::new);

        int newQuantity = cartProduct.getQuantity() - cartProductActionDto.quantity();

        if (newQuantity <= 0) {
            cartRepository.delete(cartProduct);
        } else {
            cartProduct.setQuantity(newQuantity);
            cartRepository.save(cartProduct);
        }
    }
}
