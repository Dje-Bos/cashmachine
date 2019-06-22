package org.sut.cashmachine.rest.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;
import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReceiptDTO {
    private long id;
    private String cashierName;
    private String status;
    private String total;
    private String creationTime;
    private List<ReceiptEntryDTO> entries;

    public ReceiptDTO() {
    }

    public ReceiptDTO(long id, String cashierName, String status, String total, String creationTime, List<ReceiptEntryDTO> entries) {
        this.id = id;
        this.cashierName = cashierName;
        this.status = status;
        this.total = total;
        this.creationTime = creationTime;
        this.entries = entries;
    }

    @Override
    public String toString() {
        return "ReceiptDTO{" +
                "id=" + id +
                ", cashierName='" + cashierName + '\'' +
                ", status='" + status + '\'' +
                ", total='" + total + '\'' +
                ", creationTime='" + creationTime + '\'' +
                ", entries=" + entries +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReceiptDTO that = (ReceiptDTO) o;
        return id == that.id &&
                Objects.equals(cashierName, that.cashierName) &&
                Objects.equals(status, that.status) &&
                Objects.equals(total, that.total) &&
                Objects.equals(creationTime, that.creationTime) &&
                Objects.equals(entries, that.entries);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cashierName, status, total, creationTime, entries);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCashierName() {
        return cashierName;
    }

    public void setCashierName(String cashierName) {
        this.cashierName = cashierName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(String creationTime) {
        this.creationTime = creationTime;
    }

    public List<ReceiptEntryDTO> getEntries() {
        return entries;
    }

    public void setEntries(List<ReceiptEntryDTO> entries) {
        this.entries = entries;
    }
}
