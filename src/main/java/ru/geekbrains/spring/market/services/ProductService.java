package ru.geekbrains.spring.market.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.geekbrains.spring.market.dto.ProductDto;
import ru.geekbrains.spring.market.models.Product;
import ru.geekbrains.spring.market.repositories.ProductRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<ProductDto> findAll() {
        return productRepository.findAll().stream().map(ProductDto::new).collect(Collectors.toList());
    }

    public Page<ProductDto> findAll(int page) {
        Page<Product> productPage = productRepository.findAll(PageRequest.of(page - 1, 5));
        return new PageImpl<>(productPage.getContent().stream().map(ProductDto::new).collect(Collectors.toList()), productPage.getPageable(), productPage.getTotalElements());
    }

//    public List<Product> findAllByPrice(int minPrice, int maxPrice) {
//        return productRepository.findAllByPriceBetween(minPrice, maxPrice);
//    }

    public ProductDto findProductById(Long id) {
        return new ProductDto(productRepository.findById(id).get());
    }

    public ProductDto saveOrUpdate(ProductDto productDto) {
        Product product = null;
        if (productDto.getId() == null) {
            product = new Product();
        } else {
            product = productRepository.findById(productDto.getId()).get();
        }
        product.setTitle(productDto.getTitle());
        product.setPrice(productDto.getPrice());
        return new ProductDto(productRepository.save(product));
    }

    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
    }

}
