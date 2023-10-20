package org.aadi.productcommandservice.controller;

import lombok.RequiredArgsConstructor;
import org.aadi.productcommandservice.dto.ProductEvent;
import org.aadi.productcommandservice.entity.Product;
import org.aadi.productcommandservice.service.ProductCommandService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductCommandController {

    private final ProductCommandService productCommandService;

    @PostMapping
    public Product createProduct(@RequestBody ProductEvent productEvent) {
        return productCommandService.createProduct(productEvent);
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable long id, @RequestBody ProductEvent productEvent) {
        return productCommandService.updateProduct(id, productEvent);
    }
}
