package pl.edu.pb.wi.productservice.dtos;

public record ExceptionDto(int code,
                           String message,
                           String details) {

}
