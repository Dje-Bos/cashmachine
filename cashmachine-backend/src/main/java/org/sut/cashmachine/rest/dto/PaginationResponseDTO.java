package org.sut.cashmachine.rest.dto;

import java.util.List;
import java.util.Objects;

public class PaginationResponseDTO<T> {
    private List<T> items;
    private long totalCount;

    public PaginationResponseDTO(List<T> items, long totalCount) {
        this.items = items;
        this.totalCount = totalCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaginationResponseDTO<T> that = (PaginationResponseDTO<T>) o;
        return totalCount == that.getTotalCount() &&
                Objects.equals(items, that.getItems());
    }

    @Override
    public String toString() {
        return "PaginationResponseDTO{" +
                "items=" + items +
                ", totalCount=" + totalCount +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(items, totalCount);
    }

    public long getTotalCount() {
        return totalCount;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }

    public List<T> getItems() {
        return items;
    }
}
