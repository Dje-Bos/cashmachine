package org.sut.cashmachine.model.product;

import javax.persistence.*;
import java.time.OffsetDateTime;

@Entity
@Table(name = "products")
public class ProductModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "code", unique = true)
    private String code;

    @Column(name = "creation_time", updatable = false, nullable = false)
    private OffsetDateTime creationTime;

    @Column(name = "is_allowed_for_purchase")
    private Boolean isAllowedForPurchase;

    @Column(name = "unit")
    private String unit;

    @Column(name = "price")
    private Double price;


    @Column(name = "in_stock")
    private Double inStock;

    public ProductModel() {
    }

    public ProductModel(String name, String code, Boolean isAllowedForPurchase) {
        this.name = name;
        this.code = code;
        this.isAllowedForPurchase = isAllowedForPurchase;
    }

    public ProductModel(Long id, String name, String code, OffsetDateTime creationTime, Boolean isAllowedForPurchase, String unit, Double price, Double inStock) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.creationTime = creationTime;
        this.isAllowedForPurchase = isAllowedForPurchase;
        this.unit = unit;
        this.price = price;
        this.inStock = inStock;
    }

    public ProductModel(String name, String code) {
        this(name, code, true);
    }

    @PrePersist
    public void preInsert() {
        if (this.creationTime == null) {
            this.creationTime = OffsetDateTime.now();
        }
        if (this.isAllowedForPurchase == null) {
            this.isAllowedForPurchase = true;
        }
        if (this.unit == null) {
            this.unit = "pieces";
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public OffsetDateTime getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(OffsetDateTime creationTime) {
        this.creationTime = creationTime;
    }

    public Boolean getAllowedForPurchase() {
        return isAllowedForPurchase;
    }

    public void setAllowedForPurchase(Boolean allowedForPurchase) {
        isAllowedForPurchase = allowedForPurchase;
    }

    @Override
    public String toString() {
        return "ProductModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", creationTime=" + creationTime +
                ", isAllowedForPurchase=" + isAllowedForPurchase +
                ", unit='" + unit + '\'' +
                ", price=" + price +
                ", inStock=" + inStock +
                '}';
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getInStock() {
        return inStock;
    }

    public void setInStock(Double inStock) {
        this.inStock = inStock;
    }
}
