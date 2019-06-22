package org.sut.cashmachine.service.receipt;

import org.sut.cashmachine.model.order.ReceiptEntryModel;

import java.math.BigDecimal;

public interface ReceiptEntryService {
    ReceiptEntryModel updateReceiptEntryQuantity(Long entryId, BigDecimal newQuantity);
}
