package org.sut.cashmachine.rest.populator.impl;

import org.springframework.stereotype.Component;
import org.sut.cashmachine.model.product.ProductModel;
import org.sut.cashmachine.rest.dto.ProductDTO;
import org.sut.cashmachine.rest.populator.Populator;

@Component
public class ProductPopulator implements Populator<ProductModel, ProductDTO> {
    @Override
    public void populate(ProductModel source, ProductDTO target) {
        target.setId(source.getId());
        target.setCode(source.getCode());
        target.setName(source.getName());
        target.setPrice(source.getPrice());
    }
}
