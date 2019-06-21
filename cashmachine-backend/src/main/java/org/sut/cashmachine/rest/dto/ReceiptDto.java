package org.sut.cashmachine.rest.dto;

import java.util.Objects;

public class ReceiptDto {
    private long id;
    private String cashierName;
    private String status;
    private String total;
    private String creationTime;

    public ReceiptDto() {
    }

    public ReceiptDto(long id, String cashierName, String status, String total, String creationTime) {
        this.id = id;
        this.cashierName = cashierName;
        this.status = status;
        this.total = total;
        this.creationTime = creationTime;
    }

    @Override
    public String toString() {
        return "ReceiptDto{" +
                "id=" + id +
                ", cashierName='" + cashierName + '\'' +
                ", status='" + status + '\'' +
                ", total='" + total + '\'' +
                ", creationTime='" + creationTime + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReceiptDto that = (ReceiptDto) o;
        return id == that.id &&
                Objects.equals(cashierName, that.cashierName) &&
                Objects.equals(status, that.status) &&
                Objects.equals(total, that.total) &&
                Objects.equals(creationTime, that.creationTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cashierName, status, total, creationTime);
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
}
