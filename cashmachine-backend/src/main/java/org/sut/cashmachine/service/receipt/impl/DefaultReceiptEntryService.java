package org.sut.cashmachine.service.receipt.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.sut.cashmachine.dao.receipt.ReceiptEntryRepository;
import org.sut.cashmachine.model.order.ReceiptEntryModel;
import org.sut.cashmachine.service.product.StockService;
import org.sut.cashmachine.service.receipt.CalculationService;
import org.sut.cashmachine.service.receipt.ReceiptEntryService;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Objects;

@Service
public class DefaultReceiptEntryService implements ReceiptEntryService {
    private ReceiptEntryRepository receiptEntryRepository;
    private StockService stockService;
    private CalculationService calculationService;

    @Autowired
    public DefaultReceiptEntryService(ReceiptEntryRepository receiptEntryRepository, StockService stockService, CalculationService calculationService) {
        this.receiptEntryRepository = receiptEntryRepository;
        this.stockService = stockService;
        this.calculationService = calculationService;
    }

    @Override
    @Transactional
    public ReceiptEntryModel updateReceiptEntryQuantity(Long entryId, BigDecimal newQuantity) {
        Objects.requireNonNull(entryId);
        Objects.requireNonNull(newQuantity);

        ReceiptEntryModel receiptEntryForId = receiptEntryRepository.getOne(entryId);
        stockService.returnToStock(receiptEntryForId.getProduct(), receiptEntryForId.getOrderQuantity());
        stockService.getFromStock(receiptEntryForId.getProduct(), newQuantity);
        receiptEntryForId.setOrderQuantity(newQuantity);
        calculationService.calculateReceipt(receiptEntryForId.getReceipt());

        return receiptEntryRepository.save(receiptEntryForId);
    }

    @Override
    @Transactional
    public void cancelReceiptEntry(Long entryId) {
        Objects.requireNonNull(entryId);

        ReceiptEntryModel receiptEntryForId = receiptEntryRepository.getOne(entryId);
        receiptEntryForId.setOrderQuantity(BigDecimal.ZERO);
        stockService.returnToStock(receiptEntryForId.getProduct(), receiptEntryForId.getOrderQuantity());
        calculationService.calculateReceipt(receiptEntryForId.getReceipt());

        receiptEntryRepository.save(receiptEntryForId);
    }
}
