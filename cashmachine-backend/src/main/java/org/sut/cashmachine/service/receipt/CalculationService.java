package org.sut.cashmachine.service.receipt;

import org.sut.cashmachine.model.order.ReceiptModel;

public interface CalculationService {

    void calculateReceipt(ReceiptModel receipt);
}
