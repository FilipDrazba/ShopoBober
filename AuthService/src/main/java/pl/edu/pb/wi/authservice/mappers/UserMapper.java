package pl.edu.pb.wi.authservice.mappers;

import org.mapstruct.Mapper;
import pl.edu.pb.wi.authservice.dtos.RegisterRequestDto;
import pl.edu.pb.wi.authservice.entities.User;
import pl.edu.pb.wi.common.dtos.UserInfoDto;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toUser(RegisterRequestDto registerRequestDto);

    UserInfoDto toUserInfoDto(User user);
}
