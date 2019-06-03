package org.sut.cashmachine.dao.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.sut.cashmachine.model.order.ReceiptModel;
import org.sut.cashmachine.model.order.ReceiptModelPK;
import org.sut.cashmachine.model.order.ReceiptEntryModel;
import org.sut.cashmachine.model.product.ProductModel;

@Repository
public interface ReceiptEntryRepository extends JpaRepository<ReceiptEntryModel, ReceiptModelPK> {
    ReceiptEntryModel findByProductAndReceipt(ProductModel product, ReceiptModel receipt);

}
