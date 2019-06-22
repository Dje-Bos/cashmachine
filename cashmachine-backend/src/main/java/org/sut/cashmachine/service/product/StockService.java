package org.sut.cashmachine.service.product;

import org.sut.cashmachine.model.order.ReceiptEntryModel;
import org.sut.cashmachine.model.product.ProductModel;

import java.math.BigDecimal;

public interface StockService {
    void returnToStock(ProductModel product, BigDecimal quantity);
    void getFromStock(ProductModel product, BigDecimal quantity);
}
