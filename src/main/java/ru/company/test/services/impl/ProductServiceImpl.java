package ru.company.test.services.impl;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.company.test.models.Product;
import ru.company.test.repositories.ProductRepository;
import ru.company.test.services.ProductService;

import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private static final Logger LOGGER = LoggerFactory.getLogger("ProductServiceImpl");
    private final ProductRepository productRepository;

    @Override
    public Product addProduct(Product product) {
        LOGGER.info("Adding product {}", product);
        return productRepository.save(product);
    }

    @Override
    public Optional<Product> getProductById(Long id) {
        Optional<Product> product = productRepository.findById(id);
        LOGGER.info("Getting product {}", product);
        return product;
    }

    @Override
    public Product editProduct(Long id, Product newProductData) {
        return productRepository
                .findById(id)
                .map(product -> {
                    product.setPrice(newProductData.getPrice())
                            .setQuantity(newProductData.getQuantity());
                    LOGGER.info("Updating product {}", product);
                    return productRepository.save(product);
                })
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }

    @Override
    public List<Product> getAllProductsByType(Product.ProductType type) {
        List<Product> productList = productRepository.findByProductType(type);
        LOGGER.info("Getting all products by type {} size", productList.size());
        return productList;
    }
}
