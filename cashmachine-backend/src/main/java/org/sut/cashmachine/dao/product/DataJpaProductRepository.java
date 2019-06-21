package org.sut.cashmachine.dao.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.sut.cashmachine.model.product.ProductModel;

import java.util.List;

@Repository
public interface DataJpaProductRepository extends JpaRepository<ProductModel, Long> {
    List<ProductModel> findFirst3ByCodeOrNameContainingIgnoreCase(String codeQuery, String nameQuery);
}
