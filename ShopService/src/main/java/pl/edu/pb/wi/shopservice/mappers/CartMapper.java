package pl.edu.pb.wi.shopservice.mappers;

import org.mapstruct.Mapper;
import pl.edu.pb.wi.shopservice.dtos.CartProductDto;
import pl.edu.pb.wi.shopservice.entities.CartProduct;

@Mapper(componentModel = "spring")
public interface CartMapper {
    CartProductDto toCartProductDto(CartProduct cartProduct);
}
