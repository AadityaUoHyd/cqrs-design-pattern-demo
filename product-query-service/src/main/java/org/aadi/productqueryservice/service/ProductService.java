package org.aadi.productqueryservice.service;

import lombok.RequiredArgsConstructor;
import org.aadi.productqueryservice.dto.ProductEvent;
import org.aadi.productqueryservice.entity.Product;
import org.aadi.productqueryservice.repository.ProductRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<Product> fetchAll() {
        return productRepository.findAll();
    }

    @KafkaListener(topics = "product-event-topic",groupId = "product-event-group")
    public void processProductEvents(ProductEvent productEvent) {
        Product product = productEvent.getProduct();
        if (productEvent.getEventType().equals("CreateProduct")) {
            productRepository.save(product);
        }
        if (productEvent.getEventType().equals("UpdateProduct")) {
            Product existingProduct = productRepository.findById(product.getId()).get();
            existingProduct.setName(product.getName());
            existingProduct.setPrice(product.getPrice());
            existingProduct.setDescription(product.getDescription());
            productRepository.save(existingProduct);
        }
    }
}
