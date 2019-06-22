package org.sut.cashmachine.dao.receipt;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.sut.cashmachine.model.order.ReceiptEntryModel;

@Repository
public interface ReceiptEntryRepository extends JpaRepository<ReceiptEntryModel, Long> {
    ReceiptEntryModel findByProductIdAndReceiptId(long productId, long receiptId);
}
