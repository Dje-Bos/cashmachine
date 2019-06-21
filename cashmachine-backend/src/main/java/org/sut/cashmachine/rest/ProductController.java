package org.sut.cashmachine.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.sut.cashmachine.dao.product.ProductRepository;
import org.sut.cashmachine.model.product.ProductModel;
import org.sut.cashmachine.rest.converter.ProductConverter;
import org.sut.cashmachine.rest.dto.ProductDto;

import javax.validation.constraints.Size;
import java.util.List;
import java.util.stream.Collectors;
@RestController
@RequestMapping("/api/products")
@Secured({"ADMIN","CASHIER","SENIOR_CASHIER", "MERCHANDISE"})
public class ProductController {
    private static final ProductConverter CONVERTER = new ProductConverter();
    private ProductRepository repository;

    @Autowired
    public ProductController(ProductRepository repository) {
        this.repository = repository;
    }

    @GetMapping(params = {"query"})
    public ResponseEntity<List<ProductDto>> getProductsByQuery(@RequestParam("query") @Size(min = 3, max = 20) String query) {
        List<ProductModel> productsByQuery = repository.findProductsByQuery(query);
        List<ProductDto> dtos = productsByQuery.stream().map(CONVERTER::convert).collect(Collectors.toList());
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }
}
