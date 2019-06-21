package org.sut.cashmachine.dao.product;

import data.ProductTestData;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.sut.cashmachine.model.product.ProductModel;

import java.util.List;

import static org.junit.Assert.*;
@TestPropertySource(locations = {"classpath:test-db.properties"})
@DataJpaTest
@Sql(scripts = {"classpath:data-h2.sql"})
@RunWith(SpringJUnit4ClassRunner.class)
public class ProductRepositoryTest {
    @Autowired
    DataJpaProductRepository dataJpaProductRepository;

    ProductRepository productRepository;

    @Before
    public void setUp() throws Exception {
        productRepository = new ProductRepositoryImpl(dataJpaProductRepository);
    }

    @Test
    public void findProductsByQuery() {
        List<ProductModel> products = productRepository.findProductsByQuery("waf");

        List<ProductModel> expected = List.of(ProductTestData.PRODUCT_3, ProductTestData.PRODUCT_4);
        assertEquals(expected.toString(), products.toString());
    }
}