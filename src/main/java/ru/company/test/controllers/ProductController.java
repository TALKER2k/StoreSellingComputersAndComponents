package ru.company.test.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.company.test.models.Product;
import ru.company.test.services.ProductService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@AllArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping("/add")
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        System.out.println(product);
        return new ResponseEntity<>(productService.addProduct(product), HttpStatus.CREATED);
    }

    @PatchMapping("/edit/{id}")
    public ResponseEntity<Product> editProduct(@PathVariable Long id, @RequestBody Product product) {
        return new ResponseEntity<>(productService.editProduct(id, product), HttpStatus.OK);
    }

    @GetMapping("/type")
    public ResponseEntity<List<Product>> getAllProductsByType(@RequestParam Product.ProductType type) {
        return new ResponseEntity<>(productService.getAllProductsByType(type), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        return productService.getProductById(id)
                .map(product -> new ResponseEntity<>(product, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}

