package pl.edu.pb.wi.shopservice.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import pl.edu.pb.wi.shopservice.dtos.CartProductActionDto;
import pl.edu.pb.wi.shopservice.dtos.CartProductDto;
import pl.edu.pb.wi.shopservice.services.CartService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("shop/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    @GetMapping
    public ResponseEntity<List<CartProductDto>> getCart() {
        String email = getCurrentUserEmail();
        return ResponseEntity
                .ok(cartService.getCartProducts(email));
    }

    @PostMapping
    public ResponseEntity<Void> addProductToCart(@RequestBody CartProductActionDto cartProductActionDto) {
        String email = getCurrentUserEmail();
        cartService.addToCart(email, cartProductActionDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .build();
    }

    @DeleteMapping("{productId}")
    public ResponseEntity<Void> removeProductFromCart(@PathVariable Long productId, @RequestParam Optional<Integer> quantity) {
        String email = getCurrentUserEmail();
        int quantityValue = quantity.orElse(1);
        CartProductActionDto cartProductActionDto = new CartProductActionDto(productId, quantityValue);
        cartService.removeFromCart(email, cartProductActionDto);
        return ResponseEntity
                .noContent()
                .build();
    }

    private String getCurrentUserEmail() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }
}