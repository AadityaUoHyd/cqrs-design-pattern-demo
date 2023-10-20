package org.aadi.productcommandservice.service;

import lombok.RequiredArgsConstructor;
import org.aadi.productcommandservice.dto.ProductEvent;
import org.aadi.productcommandservice.entity.Product;
import org.aadi.productcommandservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductCommandService {

    private final ProductRepository productRepository;

    @Autowired
    private KafkaTemplate<String,Object> kafkaTemplate;

    public Product createProduct(ProductEvent productEvent){
        Product productDO = productRepository.save(productEvent.getProduct());
        ProductEvent event=new ProductEvent("CreateProduct", productDO);
        kafkaTemplate.send("product-event-topic", event);
        return productDO;
    }

    public Product updateProduct(long id,ProductEvent productEvent){
        Product existingProduct = productRepository.findById(id).get();
        Product newProduct=productEvent.getProduct();
        existingProduct.setName(newProduct.getName());
        existingProduct.setPrice(newProduct.getPrice());
        existingProduct.setDescription(newProduct.getDescription());
        Product productDO = productRepository.save(existingProduct);
        ProductEvent event=new ProductEvent("UpdateProduct", productDO);
        kafkaTemplate.send("product-event-topic", event);
        return productDO;
    }

}