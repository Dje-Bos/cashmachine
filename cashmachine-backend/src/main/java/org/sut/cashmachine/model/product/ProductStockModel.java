package org.sut.cashmachine.model.product;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "product_stocks")
public class ProductStockModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(targetEntity = ProductModel.class)
    private ProductModel product;

    @Enumerated(EnumType.STRING)
    private ProductUnit unit;
    private BigDecimal stockLevel;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ProductModel getProduct() {
        return product;
    }

    public void setProduct(ProductModel product) {
        this.product = product;
    }

    public ProductUnit getUnit() {
        return unit;
    }

    public void setUnit(ProductUnit unit) {
        this.unit = unit;
    }

    public BigDecimal getStockLevel() {
        return stockLevel;
    }

    public void setStockLevel(BigDecimal stockLevel) {
        this.stockLevel = stockLevel;
    }
}
