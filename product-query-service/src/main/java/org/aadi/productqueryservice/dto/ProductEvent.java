package org.aadi.productqueryservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.aadi.productqueryservice.entity.Product;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductEvent {

    private String eventType;
    private Product product;
}
