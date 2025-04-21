package pl.edu.pb.wi.shopservice.dtos;

public record ExceptionDto(int code,
                           String message,
                           String details) {

}
