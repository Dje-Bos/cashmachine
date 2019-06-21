package org.sut.cashmachine.dao.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sut.cashmachine.dao.user.DataJpaUserRepository;
import org.sut.cashmachine.dao.user.UserRepository;
import org.sut.cashmachine.model.product.ProductModel;

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
}
