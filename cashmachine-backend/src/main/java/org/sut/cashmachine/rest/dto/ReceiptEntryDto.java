package org.sut.cashmachine.rest.dto;

import java.math.BigDecimal;
import java.util.Objects;

public class ReceiptEntryDto {
    private long id;
    private String productCode;
    private String productName;
    private BigDecimal total;
    private BigDecimal quantity;

    public ReceiptEntryDto() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "ReceiptEntryDto{" +
                "id=" + id +
                ", productCode='" + productCode + '\'' +
                ", productName='" + productName + '\'' +
                ", total=" + total +
                ", quantity=" + quantity +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReceiptEntryDto that = (ReceiptEntryDto) o;
        return id == that.id &&
                Objects.equals(productCode, that.productCode) &&
                Objects.equals(productName, that.productName) &&
                Objects.equals(total, that.total) &&
                Objects.equals(quantity, that.quantity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, productCode, productName, total, quantity);
    }

    public ReceiptEntryDto(long id, String productCode, String productName, BigDecimal total, BigDecimal quantity) {
        this.id = id;
        this.productCode = productCode;
        this.productName = productName;
        this.total = total;
        this.quantity = quantity;
    }
}
