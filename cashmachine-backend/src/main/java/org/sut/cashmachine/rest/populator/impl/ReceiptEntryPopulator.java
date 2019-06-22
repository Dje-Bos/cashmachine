package org.sut.cashmachine.rest.populator.impl;

import org.springframework.stereotype.Component;
import org.sut.cashmachine.model.order.ReceiptEntryModel;
import org.sut.cashmachine.rest.dto.ReceiptEntryDTO;
import org.sut.cashmachine.rest.populator.Populator;

@Component
public class ReceiptEntryPopulator implements Populator<ReceiptEntryModel, ReceiptEntryDTO> {

    @Override
    public void populate(ReceiptEntryModel source, ReceiptEntryDTO target) {
        target.setId(source.getId());
        if (source.getProduct() != null) {
            target.setProductCode(source.getProduct().getCode());
            target.setProductName(source.getProduct().getName());
        }
        target.setQuantity(source.getOrderQuantity());
        target.setTotal(source.getTotal());
    }
}
