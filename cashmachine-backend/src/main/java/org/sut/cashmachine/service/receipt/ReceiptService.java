package org.sut.cashmachine.service.receipt;

import org.sut.cashmachine.model.order.ReceiptModel;
import org.sut.cashmachine.rest.dto.ReceiptEntryDTO;

public interface ReceiptService {
    ReceiptModel addProductToReceipt(Long receiptId, ReceiptEntryDTO entry);
    ReceiptModel cancelReceipt(Long receiptId);
}
