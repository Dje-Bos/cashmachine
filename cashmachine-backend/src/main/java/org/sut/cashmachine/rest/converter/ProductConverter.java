package org.sut.cashmachine.rest.converter;

import org.sut.cashmachine.model.product.ProductModel;
import org.sut.cashmachine.rest.dto.ProductDto;

public class ProductConverter implements Converter<ProductModel, ProductDto>{

    @Override
    public ProductDto convert(ProductModel object) {
        return new ProductDto(object.getId(), object.getCode(), object.getName(), object.getPrice());
    }
}
