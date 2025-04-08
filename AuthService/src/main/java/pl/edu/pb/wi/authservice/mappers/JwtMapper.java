package pl.edu.pb.wi.authservice.mappers;

import org.mapstruct.Mapper;
import pl.edu.pb.wi.authservice.dtos.JwtValueDto;

@Mapper(componentModel = "spring")
public interface JwtMapper {
    JwtValueDto toJwtDto(String token);
}
