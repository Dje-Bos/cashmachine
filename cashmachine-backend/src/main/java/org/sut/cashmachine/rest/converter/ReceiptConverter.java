package org.sut.cashmachine.rest.converter;

import org.sut.cashmachine.model.order.ReceiptEntryModel;
import org.sut.cashmachine.model.order.ReceiptModel;
import org.sut.cashmachine.rest.dto.ReceiptDto;
import org.sut.cashmachine.rest.dto.ReceiptEntryDto;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ReceiptConverter implements Converter<ReceiptModel, ReceiptDto> {

    @Override
    public ReceiptDto convert(ReceiptModel object) {
        List<ReceiptEntryDto> receiptEnitities = object.getReceiptEnitities() == null ? null: object.getReceiptEnitities().stream().map(e->new ReceiptEntryDto(e.getId(), e.getProduct().getCode(), e.getProduct().getName(), e.getTotal(), e.getOrderQuantity())).collect(Collectors.toList());
        return new ReceiptDto(object.getId(), object.getCashier().getName(), object.getStatus(), object.getTotal().toPlainString(), DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(object.getCreationTime().toLocalDateTime()), receiptEnitities);
    }
}
