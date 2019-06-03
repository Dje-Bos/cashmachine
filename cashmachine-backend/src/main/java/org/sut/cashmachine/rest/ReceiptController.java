package org.sut.cashmachine.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.sut.cashmachine.dao.order.ReceiptEntryRepository;
import org.sut.cashmachine.dao.order.ReceiptRepository;
import org.sut.cashmachine.model.order.ReceiptModel;

import java.util.Optional;

@RestController
@RequestMapping("/api/receipt")
public class ReceiptController {

    private ReceiptRepository receiptRepository;
    private ReceiptEntryRepository receiptEntryRepository;

    @Autowired
    public ReceiptController(ReceiptRepository receiptRepository, ReceiptEntryRepository receiptEntryRepository) {
        this.receiptRepository = receiptRepository;
        this.receiptEntryRepository = receiptEntryRepository;
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public  ResponseEntity<ReceiptModel> getReceiptById(@PathVariable Long id) {
        ResponseEntity<ReceiptModel> responseEntity;
        Optional<ReceiptModel> receiptOpt = receiptRepository.findById(id);
        if (receiptOpt.isPresent()) {
            responseEntity = new ResponseEntity<>(receiptOpt.get(), HttpStatus.FOUND);
        }
        else {
            responseEntity = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }

}
