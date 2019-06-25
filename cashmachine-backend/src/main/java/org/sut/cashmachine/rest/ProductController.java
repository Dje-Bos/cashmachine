package org.sut.cashmachine.rest;

import org.hibernate.validator.constraints.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.sut.cashmachine.dao.product.ProductRepository;
import org.sut.cashmachine.model.product.ProductModel;
import org.sut.cashmachine.rest.converter.Converter;
import org.sut.cashmachine.rest.dto.PaginationResponseDTO;
import org.sut.cashmachine.rest.dto.ProductDTO;
import org.sut.cashmachine.rest.dto.SortDto;
import org.sut.cashmachine.rest.dto.SortOrder;
import org.sut.cashmachine.service.product.ProductService;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/products")
@Secured({"CASHIER", "MERCHANDISE"})
public class ProductController {
    private Converter<ProductModel, ProductDTO> productConverter;
    private ProductService productService;
    private ProductRepository repository;

    @Autowired
    public ProductController(Converter<ProductModel, ProductDTO> productConverter, ProductService productService, ProductRepository repository) {
        this.productConverter = productConverter;
        this.productService = productService;
        this.repository = repository;
    }

    @GetMapping(params = {"query"})
    public ResponseEntity<List<ProductDTO>> getProductsByQuery(@RequestParam("query") @Size(min = 3, max = 20) String query) {
        List<ProductModel> productsByQuery = repository.findProductsByQuery(query);
        List<ProductDTO> products = getConvertedProducts(productsByQuery);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @DeleteMapping(params = {"code"})
    public ResponseEntity deleteProductByCode(@RequestParam("code") @NotBlank String code) {
        repository.removeByCode(code);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(params = {"code"})
    public ResponseEntity<ProductDTO> updateProductByCode(@RequestParam("code") @NotBlank String code, @RequestBody ProductDTO productDTO) {
        return new ResponseEntity<>(productConverter.convert(productService.updateByCode(code, productDTO)), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO productDTO) {

        return new ResponseEntity<>(productConverter.convert(productService.createProduct(productDTO)), HttpStatus.OK);
    }

    @GetMapping(params = {"page", "size"})
    public ResponseEntity<PaginationResponseDTO<ProductDTO>> getProductsWithPagination(
            @RequestParam("page") @Range(max = Integer.MAX_VALUE) int page,
            @RequestParam("size") @Range(max = 100) int size,
            SortDto sort) {
        Sort productSort = Sort.by("name").ascending();
        productSort = getProductSortFromRequest(sort, productSort);
        Page<ProductModel> productsPage = repository.getProductsPage(PageRequest.of(page, size, productSort));

        return new ResponseEntity<>(new PaginationResponseDTO<>(getConvertedProducts(productsPage.getContent()), productsPage.getTotalElements()), HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<ProductDTO> getByCode(@RequestParam("code") String code) {

        return new ResponseEntity<>(productConverter.convert(repository.findProductByCode(code)), HttpStatus.OK);
    }


    private Sort getProductSortFromRequest(SortDto sort, Sort productSort) {
        if (sort != null) {
            productSort = Sort.by(sort.getSort()).ascending();
            if (sort.getSortOrder() == SortOrder.DESC) {
                productSort = Sort.by(sort.getSort()).descending();
            }
        }
        return productSort;
    }

    private List<ProductDTO> getConvertedProducts(List<ProductModel> content) {
        return content.stream().map(productConverter::convert).collect(Collectors.toList());
    }
}
