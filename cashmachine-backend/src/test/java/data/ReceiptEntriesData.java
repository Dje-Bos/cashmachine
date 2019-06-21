package data;

import org.sut.cashmachine.model.order.ReceiptEntryModel;
import org.sut.cashmachine.model.order.ReceiptModel;

import java.math.BigDecimal;

public class ReceiptEntriesData {

    public static  ReceiptEntryModel ENTRY_0 = new ReceiptEntryModel(1, null, null, BigDecimal.valueOf(20), BigDecimal.valueOf(2000));
    public static  ReceiptEntryModel ENTRY_1 = new ReceiptEntryModel(2, null, null, BigDecimal.valueOf(2), BigDecimal.valueOf(501));
    public static  ReceiptEntryModel ENTRY_2 = new ReceiptEntryModel(3, null, null, BigDecimal.valueOf(1), BigDecimal.valueOf(458.3));
    static {
        ENTRY_0.setProduct(ProductTestData.PRODUCT_0);
        ENTRY_1.setProduct(ProductTestData.PRODUCT_1);
        ENTRY_2.setProduct(ProductTestData.PRODUCT_2);

        ENTRY_0.setReceipt(ReceiptTestData.RECEIPT_0);
        ENTRY_1.setReceipt(ReceiptTestData.RECEIPT_0);
        ENTRY_2.setReceipt(ReceiptTestData.RECEIPT_1);
    }
}
