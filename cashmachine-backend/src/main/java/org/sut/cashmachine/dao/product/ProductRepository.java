package org.sut.cashmachine.dao.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.sut.cashmachine.model.product.ProductModel;

@Repository
public interface ProductRepository extends JpaRepository<ProductModel, Long> {

}
