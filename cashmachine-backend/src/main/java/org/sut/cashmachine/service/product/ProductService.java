package org.sut.cashmachine.service.product;

import org.sut.cashmachine.model.product.ProductModel;
import org.sut.cashmachine.rest.dto.ProductDTO;

public interface ProductService {
    ProductModel updateByCode(String code, ProductDTO product);
    ProductModel createProduct(ProductDTO product);
}
