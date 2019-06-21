package org.sut.cashmachine.dataload.test.data;

import org.sut.cashmachine.model.order.ReceiptModel;

import java.util.HashSet;
import java.util.Set;

public final class ReceiptTestData {
    public static final Set<ReceiptModel> RECEIPTS = new HashSet<>();
    public static final ReceiptModel FIRST_ADMIN_RECEIPT = new ReceiptModel(UserTestData.ADMIN);
    public static final ReceiptModel SECOND_ADMIN_RECEIPT = new ReceiptModel(UserTestData.ADMIN);
    public static final ReceiptModel FIRST_CASHIER_RECEIPT = new ReceiptModel(UserTestData.CASHIER);
    public static final ReceiptModel SECOND_CASHIER_RECEIPT = new ReceiptModel(UserTestData.CASHIER);

    static {
        FIRST_ADMIN_RECEIPT.setId(1L);
        SECOND_ADMIN_RECEIPT.setId(2L);
        FIRST_CASHIER_RECEIPT.setId(3L);
        SECOND_CASHIER_RECEIPT.setId(4L);
        RECEIPTS.add(FIRST_ADMIN_RECEIPT);
        RECEIPTS.add(SECOND_ADMIN_RECEIPT);
        RECEIPTS.add(FIRST_CASHIER_RECEIPT);
        RECEIPTS.add(SECOND_CASHIER_RECEIPT);
    }
}
