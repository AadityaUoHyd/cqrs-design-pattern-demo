package org.aadi.productqueryservice.controller;


import lombok.RequiredArgsConstructor;
import org.aadi.productqueryservice.entity.Product;
import org.aadi.productqueryservice.service.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public List<Product> fetchAll(){
        return productService.fetchAll();
    }
}
