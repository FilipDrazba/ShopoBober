package pl.edu.pb.wi.authservice.dtos;

import jakarta.validation.constraints.NotBlank;

public record JwtValueDto(
        @NotBlank
        String token
) {

}
