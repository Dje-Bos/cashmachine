package org.sut.cashmachine.model.product;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Set;

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

    @OneToMany
    @JoinColumn(name = "product_id")
    private Set<ProductPriceModel> prices;

    @OneToMany
    @JoinColumn(name = "product_id")
    private List<ProductStockModel> stocks;

    public ProductModel() {
    }

    public ProductModel(String name, String code, Boolean isAllowedForPurchase) {
        this.name = name;
        this.code = code;
        this.isAllowedForPurchase = isAllowedForPurchase;
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

    public Set<ProductPriceModel> getPrices() {
        return prices;
    }

    public void setPrices(Set<ProductPriceModel> prices) {
        this.prices = prices;
    }

    public List<ProductStockModel> getStocks() {
        return stocks;
    }

    public void setStocks(List<ProductStockModel> stocks) {
        this.stocks = stocks;
    }

    public Boolean getAllowedForPurchase() {
        return isAllowedForPurchase;
    }

    public void setAllowedForPurchase(Boolean allowedForPurchase) {
        isAllowedForPurchase = allowedForPurchase;
    }
}
