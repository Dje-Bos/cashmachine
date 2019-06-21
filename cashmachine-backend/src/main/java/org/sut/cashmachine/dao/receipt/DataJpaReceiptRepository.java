package org.sut.cashmachine.dao.receipt;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.sut.cashmachine.model.order.ReceiptModel;


public interface DataJpaReceiptRepository extends JpaRepository<ReceiptModel, Long> {

    @Query(name = "getById", value = "SELECT receipt FROM ReceiptModel receipt WHERE receipt.id = :id")
    ReceiptModel getById(Long id);

    @Override
    Page<ReceiptModel> findAll(Pageable pageable);
}
