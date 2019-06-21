package org.sut.cashmachine.rest.dto;


import org.sut.cashmachine.model.order.ReceiptModel;

import java.util.List;
import java.util.Objects;

public class ReceiptPageableResponse {
    private List<ReceiptDto> items;
    private long totalCount;

    public ReceiptPageableResponse(List<ReceiptDto> items, long totalCount) {
        this.items = items;
        this.totalCount = totalCount;
    }

    public ReceiptPageableResponse() {
    }

    public void setItems(List<ReceiptDto> items) {
        this.items = items;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }

    public List<ReceiptDto> getItems() {
        return items;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReceiptPageableResponse that = (ReceiptPageableResponse) o;
        return totalCount == that.totalCount &&
                Objects.equals(items, that.items);
    }

    @Override
    public int hashCode() {
        return Objects.hash(items, totalCount);
    }

    public long getTotalCount() {
        return totalCount;
    }
}
