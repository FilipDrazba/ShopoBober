package pl.edu.pb.wi.shopservice.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import pl.edu.pb.wi.shopservice.dtos.ProductDto;
import pl.edu.pb.wi.shopservice.entities.Product;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductDto toProductDto(Product product);

    @Mapping(target = "id", ignore = true)
    Product toProductWithoutId(ProductDto productDto);

    void updateProductFromDto(ProductDto productDto,
                              @MappingTarget Product product);
}
