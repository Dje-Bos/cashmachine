package org.sut.cashmachine.model.order;

import org.sut.cashmachine.model.product.ProductModel;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "receipt_entries")
public class ReceiptEntryModel {

    @EmbeddedId
    private ReceiptModelPK pk;

    @ManyToOne(targetEntity = ProductModel.class)
    @MapsId("product_id")
    private ProductModel product;

    @ManyToOne(targetEntity = ReceiptModel.class)
    @MapsId("receipt_id")
    private ReceiptModel receipt;

    @Column(name = "order_number")
    private Integer orderNumber;
    @Column(name = "order_quantity")
    private BigDecimal orderQuantity;
    @Column(name = "total")
    private BigDecimal total;

    public ReceiptEntryModel(ProductModel product, ReceiptModel receipt, Integer orderNumber, BigDecimal orderQuantity, BigDecimal total) {
        this.product = product;
        this.receipt = receipt;
        this.orderNumber = orderNumber;
        this.orderQuantity = orderQuantity;
        this.total = total;
    }

    public ReceiptEntryModel() {
    }

    public ReceiptModelPK getPk() {
        return pk;
    }

    public void setPk(ReceiptModelPK pk) {
        this.pk = pk;
    }

    public ProductModel getProduct() {
        return product;
    }

    public void setProduct(ProductModel product) {
        this.product = product;
    }

    public ReceiptModel getReceipt() {
        return receipt;
    }

    public void setReceipt(ReceiptModel receipt) {
        this.receipt = receipt;
    }

    public Integer getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
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


}
