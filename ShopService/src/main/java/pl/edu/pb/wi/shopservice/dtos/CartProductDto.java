package pl.edu.pb.wi.shopservice.dtos;

import pl.edu.pb.wi.shopservice.entities.Product;

public record CartProductDto(Product product, Integer quantity) {
}
