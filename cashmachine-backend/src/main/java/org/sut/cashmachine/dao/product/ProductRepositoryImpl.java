package org.sut.cashmachine.dao.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.sut.cashmachine.model.product.ProductModel;
import org.sut.cashmachine.rest.dto.ProductDTO;

import java.util.List;

@Repository
public class ProductRepositoryImpl implements ProductRepository {
    private DataJpaProductRepository repository;

    @Autowired
    public ProductRepositoryImpl(DataJpaProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<ProductModel> findProductsByQuery(String query) {
        return repository.findFirst3ByCodeOrNameContainingIgnoreCaseOrderByCodeAsc(query, query);
    }

    @Override
    public ProductModel findProductByCode(String code) {
        return repository.findByCode(code);
    }

    @Override
    public ProductModel save(ProductModel productModel) {
        return repository.save(productModel);
    }

    @Override
    public Page<ProductModel> getProductsPage(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    @Transactional
    @Modifying
    public void removeByCode(String code) {
        repository.removeByCode(code);
    }
}
