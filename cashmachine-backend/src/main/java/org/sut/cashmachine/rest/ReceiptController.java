package org.sut.cashmachine.rest;

import org.hibernate.validator.constraints.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.sut.cashmachine.dao.receipt.ReceiptRepository;
import org.sut.cashmachine.model.order.ReceiptModel;
import org.sut.cashmachine.rest.converter.Converter;
import org.sut.cashmachine.rest.dto.*;
import org.sut.cashmachine.security.CurrentUser;
import org.sut.cashmachine.security.UserPrincipal;
import org.sut.cashmachine.service.receipt.ReceiptService;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.stream.Collectors;

import static org.sut.cashmachine.constant.CashMachineConstants.EMPTY_STRING;

@RestController
@RequestMapping("/api/receipt")
@Secured({"ADMIN", "CASHIER", "SENIOR_CASHIER"})
public class ReceiptController {
    private Converter<ReceiptModel, ReceiptDTO> receiptConverter;
    private ReceiptRepository receiptRepository;
    private ReceiptService receiptService;

    @Autowired
    public ReceiptController(Converter<ReceiptModel, ReceiptDTO> receiptConverter, ReceiptRepository receiptRepository, ReceiptService receiptService) {
        this.receiptConverter = receiptConverter;
        this.receiptRepository = receiptRepository;
        this.receiptService = receiptService;
    }

    @GetMapping("/{id}")
    @Transactional
    public ResponseEntity<ReceiptDTO> getReceiptById(@PathVariable Long id) {
        ResponseEntity<ReceiptDTO> responseEntity;
        ReceiptModel receipt = receiptRepository.getById(id);
        if (receipt != null) {
            responseEntity = new ResponseEntity<>(receiptConverter.convert(receipt), HttpStatus.OK);
        } else {
            responseEntity = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<ReceiptDTO> createNewReceipt(@CurrentUser UserPrincipal userPrincipal) {
        return new ResponseEntity<>(receiptConverter.convert(receiptRepository.createNew(userPrincipal.getId())), HttpStatus.CREATED);
    }

    @GetMapping(params = {"page", "size"})
    public ResponseEntity<ReceiptPageableResponseDTO> getReceiptsWithPagination(@RequestParam("page") @Range(max = Integer.MAX_VALUE) int page, @RequestParam("size") @Range(max = 100) int size) {
        Page<ReceiptModel> receiptModelPage = receiptRepository.findAll(PageRequest.of(page, size, Sort.by("creationTime").descending()));

        return new ResponseEntity<>(new ReceiptPageableResponseDTO(receiptModelPage.getContent().stream().peek(e -> e.setReceiptEntities(null)).map(receiptConverter::convert).collect(Collectors.toList()), receiptModelPage.getTotalElements()), HttpStatus.OK);
    }

    @PostMapping("/{id}")
    @Transactional
    public ResponseEntity addEntry(@RequestBody @Valid CreateReceiptEntryRequestDTO request, @PathVariable("id") long receiptId) {
        ReceiptEntryDTO receiptEntryDTO = new ReceiptEntryDTO();
        receiptEntryDTO.setQuantity(BigDecimal.valueOf(request.getQuantity()));
        receiptEntryDTO.setProductCode(request.getProductCode());
        ReceiptModel receipt = receiptService.addProductToReceipt(receiptId, receiptEntryDTO);
        ReceiptDTO dto = receiptConverter.convert(receipt);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity updateReceiptStatus(@PathVariable("id") long receiptId, @RequestBody StatusDTO status) {
        ReceiptModel receipt = receiptRepository.getWithEntries(receiptId);
        if (receipt == null) {
            return new ResponseEntity<>(new ErrorDTO("Receipt with id " + receiptId + " was not found", EMPTY_STRING), HttpStatus.UNPROCESSABLE_ENTITY);
        }
        receipt.setStatus(status.getStatus());
        receiptRepository.save(receipt);
        return new ResponseEntity<>(receiptConverter.convert(receipt), HttpStatus.OK);
    }
}
