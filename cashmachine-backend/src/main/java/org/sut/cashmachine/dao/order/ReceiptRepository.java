package org.sut.cashmachine.dao.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.stereotype.Repository;
import org.sut.cashmachine.model.order.ReceiptModel;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;

@Repository
public interface ReceiptRepository extends JpaRepository<ReceiptModel, Long> {

    @Query(name = "getByIdWithLock", value = "SELECT receipt FROM ReceiptModel receipt WHERE receipt.id = :id")
    @QueryHints(value = {@QueryHint(name = "javax.persistence.lock.scope", value = "EXTENDED")})
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    ReceiptModel getById(Long id);
}
