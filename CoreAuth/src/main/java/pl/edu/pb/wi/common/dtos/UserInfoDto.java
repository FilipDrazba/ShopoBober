package pl.edu.pb.wi.common.dtos;

import pl.edu.pb.wi.common.enums.Role;

public record UserInfoDto(String email,
                          Role role) {

}
