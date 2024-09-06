package ru.company.test.services;

import ru.company.test.models.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    Product addProduct(Product productDTO);

    Optional<Product> getProductById(Long id);

    Product editProduct(Long id, Product newProductData);

    List<Product> getAllProductsByType(Product.ProductType type);
}
