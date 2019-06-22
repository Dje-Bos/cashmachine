package org.sut.cashmachine.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.sut.cashmachine.model.order.ReceiptEntryModel;
import org.sut.cashmachine.rest.converter.Converter;
import org.sut.cashmachine.rest.dto.ReceiptEntryDTO;
import org.sut.cashmachine.service.receipt.ReceiptEntryService;

import javax.validation.constraints.NotNull;

@Controller
@RequestMapping("/api/receipt-entries")
@Secured({"ADMIN", "CASHIER", "SENIOR_CASHIER"})
public class ReceiptEntryController {
    private ReceiptEntryService receiptEntryService;
    private Converter<ReceiptEntryModel, ReceiptEntryDTO> receiptEntryConverter;

    @Autowired
    public ReceiptEntryController(ReceiptEntryService receiptEntryService, Converter<ReceiptEntryModel, ReceiptEntryDTO> receiptEntryConverter) {
        this.receiptEntryService = receiptEntryService;
        this.receiptEntryConverter = receiptEntryConverter;
    }

    @PutMapping("/{id}")
    public ResponseEntity updateEntryQuantityForReceipt(@PathVariable("id") @NotNull long receiptEntryId, @RequestBody ReceiptEntryDTO entry) {
        ReceiptEntryModel updatedReceiptEntry = receiptEntryService.updateReceiptEntryQuantity(receiptEntryId, entry.getQuantity());

        return new ResponseEntity<>(receiptEntryConverter.convert(updatedReceiptEntry), HttpStatus.OK);
    }
}
