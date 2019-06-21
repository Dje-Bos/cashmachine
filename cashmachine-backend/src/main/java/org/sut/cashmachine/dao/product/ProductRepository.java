package org.sut.cashmachine.dao.product;

import org.sut.cashmachine.model.product.ProductModel;

import java.util.List;

public interface ProductRepository {
    List<ProductModel> findProductsByQuery(String query);

    ProductModel findProductByCode(String code);

    ProductModel save(ProductModel productModel);
}
