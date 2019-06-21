package org.sut.cashmachine.rest.dto;

import java.util.Objects;

public class ProductDto {
    private long id;
    private String code;
    private String name;
    private double price;

    @Override
    public String toString() {
        return "ProductDto{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductDto that = (ProductDto) o;
        return id == that.id &&
                Double.compare(that.price, price) == 0 &&
                Objects.equals(code, that.code) &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, code, name, price);
    }

    public ProductDto() {
    }

    public ProductDto(long id, String code, String name, double price) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.price = price;
    }
}
