package org.sut.cashmachine.dao.product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.sut.cashmachine.model.product.ProductModel;

import java.util.List;

@Repository
public interface DataJpaProductRepository extends JpaRepository<ProductModel, Long> {
    List<ProductModel> findFirst3ByCodeOrNameContainingIgnoreCaseOrderByCodeAsc(String codeQuery, String nameQuery);

    ProductModel findByCode(String code);

    Page<ProductModel> findAll(Pageable pageable);

    void removeByCode(String code);
}
