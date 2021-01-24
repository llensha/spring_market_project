package ru.geekbrains.spring.market.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.spring.market.dto.ProductDto;
import ru.geekbrains.spring.market.exceptions_handling.ResourceNotFoundException;
import ru.geekbrains.spring.market.repositories.specifications.ProductSpecifications;
import ru.geekbrains.spring.market.services.ProductService;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public Page<ProductDto> findAllProducts(
            @RequestParam MultiValueMap<String, String> params,
            @RequestParam(name = "p", defaultValue = "1") Integer page
    ) {
        if (page < 1) {
            page = 1;
        }
        return productService.findAll(ProductSpecifications.build(params), page, 5);
    }

    @GetMapping("/{id}")
    public ProductDto findProductById(@PathVariable Long id) {
        return productService.findProductDtoById(id).orElseThrow(() -> new ResourceNotFoundException("Продукт с id = " + id + " не найден"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDto saveNewProduct(@RequestBody ProductDto productDto) {
        productDto.setId(null);
        return productService.saveOrUpdate(productDto);
    }

    @PutMapping
    public ProductDto updateProduct(@RequestBody ProductDto productDto) {
        return productService.saveOrUpdate(productDto);
    }

    @DeleteMapping("/{id}")
    public void deleteProductById(@PathVariable Long id) {
        productService.deleteProductById(id);
    }

}
