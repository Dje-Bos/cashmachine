package org.sut.cashmachine.service.receipt.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.sut.cashmachine.dao.receipt.ReceiptRepository;
import org.sut.cashmachine.model.order.ReceiptEntryModel;
import org.sut.cashmachine.model.order.ReceiptModel;
import org.sut.cashmachine.service.receipt.CalculationService;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.Set;

@Service
public class DefaultCalculationService implements CalculationService {
    private ReceiptRepository receiptRepository;

    @Autowired
    public DefaultCalculationService(ReceiptRepository receiptRepository) {
        this.receiptRepository = receiptRepository;
    }

    @Override
    @Transactional
    public void calculateReceipt(ReceiptModel receipt) {
        Set<ReceiptEntryModel> receiptEntities = receipt.getReceiptEntities();
        calculateEntries(receiptEntities);
        calculateReceiptInternal(receipt);
        receiptRepository.save(receipt);
    }

    private void calculateReceiptInternal(ReceiptModel receipt) {
        Optional<BigDecimal> entriesTotal = receipt.getReceiptEntities().stream().map(ReceiptEntryModel::getTotal).reduce(BigDecimal::add);
        entriesTotal.ifPresent(receipt::setTotal);

    }

    private void calculateEntries(Set<ReceiptEntryModel> receiptEntities) {
        receiptEntities.forEach(entry -> entry.setTotal(entry.getOrderQuantity().multiply(BigDecimal.valueOf(entry.getProduct().getPrice()))));
    }
}
