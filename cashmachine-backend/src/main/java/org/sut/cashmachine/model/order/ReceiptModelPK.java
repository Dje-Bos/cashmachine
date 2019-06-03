package org.sut.cashmachine.model.order;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ReceiptModelPK implements Serializable {

    @Column(name = "product_id")
    private Long productId;
    @Column(name = "receipt_id")
    private Long receiptId;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getReceiptId() {
        return receiptId;
    }

    public void setReceiptId(Long receiptId) {
        this.receiptId = receiptId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReceiptModelPK that = (ReceiptModelPK) o;
        return Objects.equals(productId, that.productId) &&
                Objects.equals(receiptId, that.receiptId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, receiptId);
    }
}
