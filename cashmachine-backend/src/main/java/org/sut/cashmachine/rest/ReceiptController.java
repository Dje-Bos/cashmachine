package org.sut.cashmachine.rest;

import org.hibernate.validator.constraints.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.sut.cashmachine.dao.receipt.ReceiptEntryRepository;
import org.sut.cashmachine.dao.receipt.DataJpaReceiptRepository;
import org.sut.cashmachine.dao.receipt.ReceiptRepository;
import org.sut.cashmachine.model.order.ReceiptModel;
import org.sut.cashmachine.rest.converter.ReceiptConverter;
import org.sut.cashmachine.rest.dto.ReceiptDto;
import org.sut.cashmachine.rest.dto.ReceiptPageableResponse;

import javax.validation.constraints.Digits;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/receipt")
@Secured({"ADMIN","CASHIER","SENIOR_CASHIER"})
public class ReceiptController {
    private static final ReceiptConverter CONVERTER = new ReceiptConverter();
    private ReceiptRepository receiptRepository;
    private ReceiptEntryRepository receiptEntryRepository;

    @Autowired
    public ReceiptController(ReceiptRepository receiptRepository, ReceiptEntryRepository receiptEntryRepository) {
        this.receiptRepository = receiptRepository;
        this.receiptEntryRepository = receiptEntryRepository;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReceiptDto> getReceiptById(@PathVariable Long id) {
        ResponseEntity<ReceiptDto> responseEntity;
        ReceiptModel receipt = receiptRepository.getById(id);
        if (receipt != null) {
            responseEntity = new ResponseEntity<>(CONVERTER.convert(receipt), HttpStatus.OK);
        }
        else {
            responseEntity = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }

    @GetMapping(params = {"page", "size"})
    public ResponseEntity<ReceiptPageableResponse> getReceiptsWithPagination(@RequestParam("page") @Range(max = Integer.MAX_VALUE) int page, @RequestParam("size") @Range(max = 100) int size) {
        Page<ReceiptModel> receiptModelPage = receiptRepository.findAll(PageRequest.of(page, size, Sort.by("creationTime").descending()));

        return new ResponseEntity<>(new ReceiptPageableResponse(receiptModelPage.getContent().stream().map(CONVERTER::convert).collect(Collectors.toList()), receiptModelPage.getTotalElements()), HttpStatus.OK);
    }

}
