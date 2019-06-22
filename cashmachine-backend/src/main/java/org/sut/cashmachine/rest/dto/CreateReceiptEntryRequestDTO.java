package org.sut.cashmachine.rest.dto;

import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;

public class CreateReceiptEntryRequestDTO {
    @NotBlank
    private String productCode;
    @Range(min = 1, max = Integer.MAX_VALUE)
    private int quantity;

    public CreateReceiptEntryRequestDTO(@NotBlank String productCode, @Range(min = 1, max = Integer.MAX_VALUE) int quantity) {
        this.productCode = productCode;
        this.quantity = quantity;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public CreateReceiptEntryRequestDTO() {
    }
}
