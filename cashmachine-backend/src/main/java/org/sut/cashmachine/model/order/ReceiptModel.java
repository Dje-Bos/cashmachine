package org.sut.cashmachine.model.order;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.sut.cashmachine.model.user.UserModel;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;

@Entity
@Table(name = "receipts")
public class ReceiptModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @Version
//    private Long version;

    @Column(updatable = false, nullable = false, name = "creation_time")
    private OffsetDateTime creationTime;

    @ManyToOne(targetEntity = UserModel.class, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private UserModel cashier;


    @OneToMany(mappedBy = "receipt", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @OrderBy("id asc")
    private Set<ReceiptEntryModel> receiptEnitities;

    @Column(name = "total")
    private BigDecimal total = BigDecimal.ZERO;

    @Column(name = "status")
    private String status;

    @PrePersist
    public void preInsert() {
        if (this.creationTime == null) {
            this.creationTime = OffsetDateTime.now();
        }
    }

    public ReceiptModel() {
    }

    public ReceiptModel(UserModel cashier) {
        this.cashier = cashier;
    }

    public ReceiptModel(UserModel cashier, Set<ReceiptEntryModel> receiptEnitities, BigDecimal total) {
        this.cashier = cashier;
        this.receiptEnitities = receiptEnitities;
        this.total = total;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public OffsetDateTime getCreationTime() {
        return creationTime;
    }


    public void setCreationTime(OffsetDateTime creationTime) {
        this.creationTime = creationTime;
    }

    public UserModel getCashier() {
        return cashier;
    }

    @JsonGetter("cashier")
    public String getCashierName() {
        return cashier.getName();
    }

    public void setCashier(UserModel cashier) {
        this.cashier = cashier;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public ReceiptModel(Long id, OffsetDateTime creationTime, UserModel cashier, Set<ReceiptEntryModel> receiptEnitities, BigDecimal total, String status) {
        this.id = id;
        this.creationTime = creationTime;
        this.cashier = cashier;
        this.receiptEnitities = receiptEnitities;
        this.total = total;
        this.status = status;
    }

    @Override
    public String toString() {
        return "ReceiptModel{" +
                "id=" + id +
                ", creationTime=" + creationTime +
                ", cashier=" + cashier +
                ", total=" + total +
                ", status='" + status + '\'' +
                '}';
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
//    public Long getVersion() {
//        return version;
//    }
//
//    public void setVersion(Long version) {
//        this.version = version;
//    }

    public Set<ReceiptEntryModel> getReceiptEnitities() {
        return receiptEnitities;
    }

    public void setReceiptEnitities(Set<ReceiptEntryModel> receiptEnitities) {
        this.receiptEnitities = receiptEnitities;
    }
}
