package pl.edu.pb.wi.shopservice.services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.edu.pb.wi.shopservice.dtos.ProductDto;
import pl.edu.pb.wi.shopservice.entities.Product;
import pl.edu.pb.wi.shopservice.exceptions.ResourceNotFoundException;
import pl.edu.pb.wi.shopservice.mappers.ProductMapper;
import pl.edu.pb.wi.shopservice.repositories.ProductRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Transactional
    public ProductDto createProduct(ProductDto productDto) {
        var product = productMapper.toProductWithoutId(productDto);

        var savedProduct = productRepository.save(product);

        return productMapper.toProductDto(savedProduct);
    }

    public List<ProductDto> getAllProducts() {
        return productRepository.findAll().stream()
                .map(productMapper::toProductDto)
                .toList();
    }

    public ProductDto getProductById(Long id) {
        var product = findProductById(id);

        return productMapper.toProductDto(product);
    }

    @Transactional
    public ProductDto updateProduct(Long id, ProductDto productDto) {
        var product = findProductById(id);

        productMapper.updateProductFromDto(productDto, product);

        var updatedProduct = productRepository.save(product);

        return productMapper.toProductDto(updatedProduct);
    }

    @Transactional
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    private Product findProductById(Long id) {
        return productRepository
                .findById(id)
                .orElseThrow(ResourceNotFoundException::new);
    }
}
