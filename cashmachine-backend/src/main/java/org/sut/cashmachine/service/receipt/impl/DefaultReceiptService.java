package org.sut.cashmachine.service.receipt.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.sut.cashmachine.dao.product.ProductRepository;
import org.sut.cashmachine.dao.receipt.ReceiptRepository;
import org.sut.cashmachine.exception.UnknownIdentifierException;
import org.sut.cashmachine.model.order.ReceiptEntryModel;
import org.sut.cashmachine.model.order.ReceiptModel;
import org.sut.cashmachine.model.product.ProductModel;
import org.sut.cashmachine.rest.dto.ReceiptEntryDTO;
import org.sut.cashmachine.service.product.StockService;
import org.sut.cashmachine.service.receipt.CalculationService;
import org.sut.cashmachine.service.receipt.ReceiptService;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;

import static java.util.Optional.ofNullable;

@Service
public class DefaultReceiptService implements ReceiptService {
    private ReceiptRepository receiptRepository;
    private ProductRepository productRepository;
    private StockService stockService;
    private CalculationService calculationService;

    @Autowired
    public DefaultReceiptService(ReceiptRepository receiptRepository, ProductRepository productRepository, StockService stockService, CalculationService calculationService) {
        this.receiptRepository = receiptRepository;
        this.productRepository = productRepository;
        this.stockService = stockService;
        this.calculationService = calculationService;
    }

    @Override
    @Transactional
    public ReceiptModel addProductToReceipt(Long receiptId, ReceiptEntryDTO entry) {
        Objects.requireNonNull(receiptId);
        Objects.requireNonNull(entry);

        ReceiptModel receipt = receiptRepository.getWithEntries(receiptId);
        if (receipt == null) {
            throw new UnknownIdentifierException(String.format("Cannot find receipt for id = '%s'", receiptId));
        } else {
            addProductToExistingReceipt(receipt, entry);
            calculationService.calculateReceipt(receipt);
            receiptRepository.save(receipt);
            return receipt;
        }
    }

    private void addProductToExistingReceipt(ReceiptModel receiptModel, ReceiptEntryDTO entry) {
        ProductModel productByCode = productRepository.findProductByCode(entry.getProductCode());
        if (productByCode == null) {
            throw new UnknownIdentifierException(String.format("Cannot find product for code = '%s'", entry.getProductCode()));
        } else {

            stockService.getFromStock(productByCode, entry.getQuantity());
            createEntryInReceipt(productByCode, receiptModel, entry.getQuantity());
        }
    }

    private void createEntryInReceipt(ProductModel productByCode, ReceiptModel receiptModel, BigDecimal quantity) {
        if (receiptModel.getReceiptEntities() == null) {
            receiptModel.setReceiptEntities(new HashSet<>());
        }
        Optional<ReceiptEntryModel> existingEntryForProduct = receiptModel.getReceiptEntities().stream().filter(en -> productByCode.getCode().equals(en.getProduct().getCode())).findFirst();
        ReceiptEntryModel entry;
        if (existingEntryForProduct.isPresent()) {
            entry = existingEntryForProduct.get();
            entry.setOrderQuantity(entry.getOrderQuantity().add(quantity));
        } else {
            entry = new ReceiptEntryModel();
            entry.setProduct(productByCode);
            entry.setOrderQuantity(quantity);
            entry.setReceipt(receiptModel);
        }
        receiptModel.getReceiptEntities().add(entry);
    }

    @Override
    @Transactional
    public ReceiptModel cancelReceipt(Long receiptId) {
        Objects.requireNonNull(receiptId);

        ReceiptModel receipt = receiptRepository.getWithEntries(receiptId);
        if (receipt == null) {
            throw new UnknownIdentifierException(String.format("Cannot find receipt for id = '%s'", receiptId));
        } else {
            receipt.setStatus("CANCELLED");
            ofNullable(receipt.getReceiptEntities()).orElseGet(HashSet::new).forEach(entry -> entry.setOrderQuantity(BigDecimal.ZERO));
            calculationService.calculateReceipt(receipt);
            receiptRepository.save(receipt);
            return receipt;
        }
    }

}
