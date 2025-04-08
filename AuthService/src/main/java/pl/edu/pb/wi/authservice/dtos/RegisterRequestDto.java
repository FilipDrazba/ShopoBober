package pl.edu.pb.wi.authservice.dtos;

import lombok.Builder;

@Builder
public record RegisterRequestDto(String email,
                                 String password,
                                 String role) {

}
