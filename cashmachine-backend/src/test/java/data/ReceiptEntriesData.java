package data;

import org.sut.cashmachine.model.order.ReceiptEntryModel;
import org.sut.cashmachine.model.order.ReceiptModel;

import java.math.BigDecimal;

public class ReceiptEntriesData {

    public static final ReceiptEntryModel ENTRY_0 = new ReceiptEntryModel(1, ProductTestData.PRODUCT_0, ReceiptTestData.RECEIPT_0, BigDecimal.valueOf(20), BigDecimal.valueOf(2000));
    public static final ReceiptEntryModel ENTRY_1 = new ReceiptEntryModel(2, ProductTestData.PRODUCT_1, ReceiptTestData.RECEIPT_0, BigDecimal.valueOf(2), BigDecimal.valueOf(501));
    public static final ReceiptEntryModel ENTRY_2 = new ReceiptEntryModel(3, ProductTestData.PRODUCT_2, ReceiptTestData.RECEIPT_1, BigDecimal.valueOf(1), BigDecimal.valueOf(458.3));
}
