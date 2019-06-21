package org.sut.cashmachine.dao.receipt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.sut.cashmachine.model.order.ReceiptModel;
@Repository
public class ReceiptRepositoryImpl implements ReceiptRepository {

    private DataJpaReceiptRepository repository;

    @Autowired
    public ReceiptRepositoryImpl(DataJpaReceiptRepository repository) {
        this.repository = repository;
    }

    @Override
    public ReceiptModel getById(Long id) {
        return repository.getById(id);
    }

    @Override
    public Page<ReceiptModel> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }
}
