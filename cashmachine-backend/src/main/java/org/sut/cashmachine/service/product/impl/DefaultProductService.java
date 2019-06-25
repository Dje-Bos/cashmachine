package org.sut.cashmachine.service.product.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.sut.cashmachine.dao.product.ProductRepository;
import org.sut.cashmachine.exception.UnknownIdentifierException;
import org.sut.cashmachine.model.product.ProductModel;
import org.sut.cashmachine.rest.dto.ProductDTO;
import org.sut.cashmachine.service.product.ProductService;

@Service
public class DefaultProductService implements ProductService {
    private ProductRepository productRepository;

    @Autowired
    public DefaultProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    @Transactional
    public ProductModel updateByCode(String code, ProductDTO product) {
        ProductModel productByCode = productRepository.findProductByCode(code);
        if (productByCode != null) {
            productByCode.setInStock(product.getStock());
            productByCode.setAllowedForPurchase(product.isActive());
            productByCode.setName(product.getName());
            productByCode.setPrice(product.getPrice());
            return productRepository.save(productByCode);
        } else {
            throw new UnknownIdentifierException("Caanot find product for code=" + code);
        }
    }

    @Override
    @Transactional
    public ProductModel createProduct(ProductDTO product) {
        ProductModel productByCode = productRepository.findProductByCode(product.getCode());
        if (productByCode != null) {
            throw new RuntimeException("Product exists with code = " + product.getCode());
        } else {
            ProductModel productModel = new ProductModel();
            productModel.setInStock(product.getStock());
            productModel.setAllowedForPurchase(product.isActive());
            productModel.setName(product.getName());
            productModel.setPrice(product.getPrice());
            productModel.setCode(product.getCode());
            return productRepository.save(productModel);
        }
    }

    public ProductRepository getProductRepository() {
        return productRepository;
    }

    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
}
