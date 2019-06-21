package data;

import org.sut.cashmachine.model.product.ProductModel;

import java.time.LocalDateTime;

public class ProductTestData {

    public static final ProductModel PRODUCT_0 = new ProductModel(1L, "Petrushka", "Petr", TimeUtil.convert("2019-06-20T22:55:18"), true, "unit", 100.0, 34.0);
    public static final ProductModel PRODUCT_1 = new ProductModel(2L, "Petdro", "Petro", TimeUtil.convert("2019-06-20T22:56:18"), true, "kg", 250.5, 50.0);
    public static final ProductModel PRODUCT_2 = new ProductModel(3L, "Revo drink", "Revo", TimeUtil.convert("2019-06-20T22:57:18"), true, "unit", 458.3, 43.0);
    public static final ProductModel PRODUCT_3 = new ProductModel(4L, "Waffles", "Roshed waffles", TimeUtil.convert("2019-06-20T22:58:18"), true, "kg", 102.0, 34.0);
    public static final ProductModel PRODUCT_4 = new ProductModel(5L, "Milk waffles", "Switoch waffles", TimeUtil.convert("2019-06-20T22:59:18"), true, "pound", 900.0, 65.0);
}
