package org.sut.cashmachine.rest.dto;


import java.util.List;
import java.util.Objects;

public class ReceiptPageableResponseDTO {
    private List<ReceiptDTO> items;
    private long totalCount;

    public ReceiptPageableResponseDTO(List<ReceiptDTO> items, long totalCount) {
        this.items = items;
        this.totalCount = totalCount;
    }

    public ReceiptPageableResponseDTO() {
    }

    public void setItems(List<ReceiptDTO> items) {
        this.items = items;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }

    public List<ReceiptDTO> getItems() {
        return items;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReceiptPageableResponseDTO that = (ReceiptPageableResponseDTO) o;
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
