package org.sut.cashmachine.rest.converter;

import org.sut.cashmachine.model.order.ReceiptModel;
import org.sut.cashmachine.rest.dto.ReceiptDto;

import java.time.format.DateTimeFormatter;

public class ReceiptConverter implements Converter<ReceiptModel, ReceiptDto> {

    @Override
    public ReceiptDto convert(ReceiptModel object) {
        return new ReceiptDto(object.getId(), object.getCashierId(), object.getStatus(), object.getTotal().toPlainString(), DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(object.getCreationTime().toLocalDateTime()));
    }
}
