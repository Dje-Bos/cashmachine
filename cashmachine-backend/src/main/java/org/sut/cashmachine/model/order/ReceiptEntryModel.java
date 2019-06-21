package org.sut.cashmachine.model.order;

import com.fasterxml.jackson.annotation.JsonGetter;
import org.sut.cashmachine.model.product.ProductModel;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "receipt_entries")
public class ReceiptEntryModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(targetEntity = ProductModel.class)
    private ProductModel product;

    @ManyToOne(targetEntity = ReceiptModel.class)
    private ReceiptModel receipt;

    @Column(name = "order_quantity")
    private BigDecimal orderQuantity;
    @Column(name = "total")
    private BigDecimal total;

    public ReceiptEntryModel(ProductModel product, ReceiptModel receipt, BigDecimal orderQuantity, BigDecimal total) {
        this.product = product;
        this.receipt = receipt;
        this.orderQuantity = orderQuantity;
        this.total = total;
    }

    public ReceiptEntryModel() {
    }

    public ReceiptEntryModel(long id, ProductModel product, ReceiptModel receipt, BigDecimal orderQuantity, BigDecimal total) {
        this.id = id;
        this.product = product;
        this.receipt = receipt;
        this.orderQuantity = orderQuantity;
        this.total = total;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReceiptEntryModel that = (ReceiptEntryModel) o;
        return id == that.id &&
                Objects.equals(orderQuantity, that.orderQuantity) &&
                Objects.equals(total, that.total);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, product.getId(), receipt.getId(), orderQuantity, total);
    }

    public ProductModel getProduct() {
        return product;
    }
//
//    @JsonGetter("product")
//    public long getProductId() {
//        return product.getId();
//    }

    public void setProduct(ProductModel product) {
        this.product = product;
    }

    public ReceiptModel getReceipt() {
        return receipt;
    }


//    @JsonGetter("receipt")
//    public long getReceiptId() {
//        return receipt.getId();
//    }


    public void setReceipt(ReceiptModel receipt) {
        this.receipt = receipt;
    }

    public BigDecimal getOrderQuantity() {
        return orderQuantity;
    }

    public void setOrderQuantity(BigDecimal orderQuantity) {
        this.orderQuantity = orderQuantity;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "ReceiptEntryModel{" +
                "id=" + id +
                ", product=" + product.getId() +
                ", receipt=" + receipt.getId() +
                ", orderQuantity=" + orderQuantity +
                ", total=" + total +
                '}';
    }
}
