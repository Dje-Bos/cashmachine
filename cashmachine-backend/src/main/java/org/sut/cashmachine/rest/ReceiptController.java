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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.sut.cashmachine.dao.product.ProductRepository;
import org.sut.cashmachine.dao.receipt.ReceiptEntryRepository;
import org.sut.cashmachine.dao.receipt.DataJpaReceiptRepository;
import org.sut.cashmachine.dao.receipt.ReceiptRepository;
import org.sut.cashmachine.dao.user.UserRepository;
import org.sut.cashmachine.model.order.ReceiptEntryModel;
import org.sut.cashmachine.model.order.ReceiptModel;
import org.sut.cashmachine.model.product.ProductModel;
import org.sut.cashmachine.rest.converter.ReceiptConverter;
import org.sut.cashmachine.rest.dto.CreateReceiptEntryRequest;
import org.sut.cashmachine.rest.dto.Error;
import org.sut.cashmachine.rest.dto.ReceiptDto;
import org.sut.cashmachine.rest.dto.ReceiptPageableResponse;
import org.sut.cashmachine.security.CurrentUser;
import org.sut.cashmachine.security.UserPrincipal;

import javax.validation.Valid;
import javax.validation.constraints.Digits;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/receipt")
@Secured({"ADMIN","CASHIER","SENIOR_CASHIER"})
public class ReceiptController {
    private static final ReceiptConverter CONVERTER = new ReceiptConverter();
    private ReceiptRepository receiptRepository;
    private ProductRepository productRepository;

    @Autowired
    public ReceiptController(ReceiptRepository receiptRepository, ReceiptEntryRepository receiptEntryRepository, ProductRepository productRepository) {
        this.receiptRepository = receiptRepository;
        this.productRepository = productRepository;
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

    @PostMapping
    @Transactional
    public ResponseEntity<ReceiptDto> createNewReceipt(@CurrentUser UserPrincipal userPrincipal) {
        return new ResponseEntity<>(CONVERTER.convert(receiptRepository.createNew(userPrincipal.getId())), HttpStatus.CREATED);
    }

    @GetMapping(params = {"page", "size"})
    public ResponseEntity<ReceiptPageableResponse> getReceiptsWithPagination(@RequestParam("page") @Range(max = Integer.MAX_VALUE) int page, @RequestParam("size") @Range(max = 100) int size) {
        Page<ReceiptModel> receiptModelPage = receiptRepository.findAll(PageRequest.of(page, size, Sort.by("creationTime").descending()));

        return new ResponseEntity<>(new ReceiptPageableResponse(receiptModelPage.getContent().stream().map(CONVERTER::convert).collect(Collectors.toList()), receiptModelPage.getTotalElements()), HttpStatus.OK);
    }

    @PostMapping("/{id}")
    @Transactional
    public ResponseEntity addEntry(@RequestBody @Valid CreateReceiptEntryRequest request, @PathVariable("id") long receiptId) {
        ReceiptModel receipt = receiptRepository.getWithEntries(receiptId);
        if (receipt == null) {
            return new ResponseEntity(new Error("Receipt with id " + receipt + " was not found", ""), HttpStatus.UNPROCESSABLE_ENTITY);
        }
        ProductModel product = productRepository.findProductByCode(request.getProductCode());
        if (product == null) {
            return new ResponseEntity(new Error("Product with code " + request.getProductCode() + " was not found", ""), HttpStatus.UNPROCESSABLE_ENTITY);
        }
        if (product.getInStock() < request.getQuantity()) {
            return new ResponseEntity(new Error("Product with code " + request.getProductCode() + " stock count = " + product.getInStock() + " is not sufficient (requested + " + request.getQuantity()+")", ""), HttpStatus.UNPROCESSABLE_ENTITY);
        }
        if (receipt.getReceiptEnitities() == null) {
            receipt.setReceiptEnitities(new HashSet<>());
        }
        if (receipt.getReceiptEnitities().stream().map(ReceiptEntryModel::getProduct).map(ProductModel::getId).anyMatch(id -> id.equals(product.getId()))) {
            return new ResponseEntity(new Error("Product with code " + request.getProductCode() + " already exist in receipt", ""), HttpStatus.UNPROCESSABLE_ENTITY);
        }
        receipt.getReceiptEnitities().add(new ReceiptEntryModel(product, receipt, BigDecimal.valueOf(request.getQuantity()), BigDecimal.valueOf(request.getQuantity()).multiply(BigDecimal.valueOf(product.getPrice()))));
        List<BigDecimal> totals = receipt.getReceiptEnitities().stream().map(ReceiptEntryModel::getTotal).collect(Collectors.toList());
        BigDecimal newTotal = BigDecimal.ZERO;
        for (BigDecimal total : totals) {
            newTotal = newTotal.add(total);
        }
        receipt.setTotal(newTotal);
        product.setInStock(product.getInStock() - request.getQuantity());
        ReceiptModel saved = receiptRepository.save(receipt);
        productRepository.save(product);
        ReceiptDto dto = CONVERTER.convert(saved);
        return new ResponseEntity(dto, HttpStatus.OK);
    }

}
