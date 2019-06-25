package org.sut.cashmachine.rest.dto;

public class SortDto {
    private String sort;
    private SortOrder sortOrder;

    public SortDto(String sort, SortOrder sortOrder) {
        this.sort = sort;
        this.sortOrder = sortOrder;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public SortOrder getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(SortOrder sortOrder) {
        this.sortOrder = sortOrder;
    }
}
