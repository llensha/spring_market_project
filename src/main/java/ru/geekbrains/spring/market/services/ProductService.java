package ru.geekbrains.spring.market.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.geekbrains.spring.market.dto.ProductDto;
import ru.geekbrains.spring.market.exceptions_handling.ResourceNotFoundException;
import ru.geekbrains.spring.market.models.Product;
import ru.geekbrains.spring.market.repositories.ProductRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public Page<ProductDto> findAll(Specification<Product> specification, int page, int pageSize) {
        return productRepository.findAll(specification, PageRequest.of(page - 1, pageSize)).map(ProductDto::new);
    }

    public Optional<ProductDto> findProductDtoById(Long id) {
        return productRepository.findById(id).map(ProductDto::new);
    }

    public Optional<Product> findProductById(Long id) {
        return productRepository.findById(id);
    }

    public ProductDto saveOrUpdate(ProductDto productDto) {
        Product product = null;
        if (productDto.getId() == null) {
            product = new Product();
        } else {
            product = productRepository.findById(productDto.getId()).orElseThrow(() -> new ResourceNotFoundException("Продукт с id = " + productDto.getId() + " не найден"));
        }
        product.setTitle(productDto.getTitle());
        product.setPrice(productDto.getPrice());
        return new ProductDto(productRepository.save(product));
    }

    public void deleteProductById(Long id) {
        productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Продукт с id = " + id + " не найден"));
        productRepository.deleteById(id);
    }

}
