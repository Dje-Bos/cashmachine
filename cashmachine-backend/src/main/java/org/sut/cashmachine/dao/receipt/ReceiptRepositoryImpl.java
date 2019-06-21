package org.sut.cashmachine.dao.receipt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.sut.cashmachine.dao.user.DataJpaUserRepository;
import org.sut.cashmachine.model.order.ReceiptModel;
import org.sut.cashmachine.model.user.UserModel;

@Repository
public class ReceiptRepositoryImpl implements ReceiptRepository {

    private DataJpaReceiptRepository repository;
    private DataJpaUserRepository userRepository;

    @Autowired
    public ReceiptRepositoryImpl(DataJpaReceiptRepository repository, DataJpaUserRepository userRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
    }

    @Override
    public ReceiptModel getById(Long id) {
        return repository.getById(id);
    }


    @Override
    public Page<ReceiptModel> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    @Transactional
    @Modifying
    public ReceiptModel createNew(long userId) {
        UserModel user = userRepository.getUserById(userId);
        ReceiptModel receiptModel = new ReceiptModel(user);
        return repository.save(receiptModel);
    }
}
