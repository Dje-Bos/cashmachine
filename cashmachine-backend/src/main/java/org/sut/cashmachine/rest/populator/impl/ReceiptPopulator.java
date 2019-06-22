package org.sut.cashmachine.rest.populator.impl;

import org.springframework.stereotype.Component;
import org.sut.cashmachine.model.order.ReceiptModel;
import org.sut.cashmachine.rest.dto.ReceiptDTO;
import org.sut.cashmachine.rest.dto.ReceiptEntryDTO;
import org.sut.cashmachine.rest.populator.Populator;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ReceiptPopulator implements Populator<ReceiptModel, ReceiptDTO> {

    @Override
    public void populate(ReceiptModel source, ReceiptDTO target) {
        List<ReceiptEntryDTO> receiptEntries;
        if (source.getReceiptEntities() == null) {
            receiptEntries = null;
        } else {
            receiptEntries = source.getReceiptEntities()
                    .stream()
                    .map(e -> new ReceiptEntryDTO(e.getId(), e.getProduct().getCode(), e.getProduct().getName(), e.getTotal(), e.getOrderQuantity()))
                    .collect(Collectors.toList());
        }
        target.setId(source.getId());
        if (source.getCashier() != null) {
            target.setCashierName(source.getCashier().getName());
        }
        target.setStatus(source.getStatus());
        target.setTotal(source.getTotal().toPlainString());
        target.setCreationTime(DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(source.getCreationTime().toLocalDateTime()));
        target.setEntries(receiptEntries);
    }
}
