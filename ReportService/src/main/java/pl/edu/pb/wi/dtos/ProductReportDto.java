package pl.edu.pb.wi.dtos;

public record ProductReportDto(Long id,
                               String name,
                               String description,
                               int cartCount,
                               int total_cart_count) {
}
