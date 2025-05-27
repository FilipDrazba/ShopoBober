package pl.edu.pb.wi.shopservice.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import pl.edu.pb.wi.shopservice.dtos.CartProductDto;
import pl.edu.pb.wi.shopservice.dtos.ProductDto;
import pl.edu.pb.wi.shopservice.entities.CartProduct;
import pl.edu.pb.wi.shopservice.entities.Product;

@Mapper(componentModel = "spring")
public interface CartMapper {
    CartProductDto toCartProductDto(CartProduct cartProduct);
}
