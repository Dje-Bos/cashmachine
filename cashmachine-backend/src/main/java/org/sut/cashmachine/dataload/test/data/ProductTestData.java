package org.sut.cashmachine.dataload.test.data;

import org.sut.cashmachine.model.product.ProductModel;

import java.util.HashSet;
import java.util.Set;

public final class ProductTestData {
    public static final Set<ProductModel> PRODUCTS = new HashSet<>();

    public static final ProductModel PARSLEY = new ProductModel("Петрушка", "222");
    public static final ProductModel WAFFLES = new ProductModel("Вафли", "322");
    public static final ProductModel WAR_SAUSAGE = new ProductModel("Боевая Колбаса", "111111");
    public static final ProductModel REVO = new ProductModel("Ревасик", "222222");

    static {
        PRODUCTS.add(PARSLEY);
        PRODUCTS.add(WAFFLES);
        PRODUCTS.add(WAR_SAUSAGE);
        PRODUCTS.add(REVO);
    }

}
