package org.sut.cashmachine.service.product.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.sut.cashmachine.dao.product.ProductRepository;
import org.sut.cashmachine.exception.OutOfStockException;
import org.sut.cashmachine.model.product.ProductModel;
import org.sut.cashmachine.service.product.StockService;

import java.math.BigDecimal;

@Service
public class DefaultStockService implements StockService {
    private static final Logger LOG = LoggerFactory.getLogger(DefaultStockService.class);

    private ProductRepository productRepository;

    @Autowired
    public DefaultStockService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    @Transactional
    public void returnToStock(ProductModel product, BigDecimal quantity) {
        product.setInStock(quantity.doubleValue() + product.getInStock());
        productRepository.save(product);
    }

    @Override
    @Transactional
    public void getFromStock(ProductModel product, BigDecimal quantity) {
        if (product.getInStock() > quantity.doubleValue()) {
            product.setInStock(product.getInStock() - quantity.doubleValue());
            productRepository.save(product);
        } else {
            LOG.error("Failed to get product with code = {} from stock: available = {}, requested = {}", product.getCode(), product.getInStock(), quantity);
            throw new OutOfStockException(String.format("Product with code = '%s' is out of stock", product.getCode()));
        }
    }
}
