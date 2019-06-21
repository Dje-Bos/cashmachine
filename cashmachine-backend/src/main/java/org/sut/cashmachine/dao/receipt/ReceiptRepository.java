package org.sut.cashmachine.dao.receipt;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.sut.cashmachine.model.order.ReceiptModel;
import org.sut.cashmachine.model.user.UserModel;

public interface ReceiptRepository {
    ReceiptModel getById(Long id);

    ReceiptModel getWithEntries(Long id);

    Page<ReceiptModel> findAll(Pageable pageable);

    ReceiptModel createNew(long userId);

    ReceiptModel save(ReceiptModel receiptModel);

}
