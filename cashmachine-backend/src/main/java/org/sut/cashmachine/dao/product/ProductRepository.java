package org.sut.cashmachine.dao.product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.sut.cashmachine.model.product.ProductModel;

import java.util.List;

public interface ProductRepository {
    List<ProductModel> findProductsByQuery(String query);

    ProductModel findProductByCode(String code);

    ProductModel save(ProductModel productModel);

    Page<ProductModel> getProductsPage(Pageable pageable);

    void removeByCode(String code);

}
