package org.sut.cashmachine.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.sut.cashmachine.model.order.ReceiptEntryModel;
import org.sut.cashmachine.model.order.ReceiptModel;
import org.sut.cashmachine.model.product.ProductModel;
import org.sut.cashmachine.rest.converter.AbstractPopulatingConverter;
import org.sut.cashmachine.rest.converter.Converter;
import org.sut.cashmachine.rest.dto.ProductDTO;
import org.sut.cashmachine.rest.dto.ReceiptDTO;
import org.sut.cashmachine.rest.dto.ReceiptEntryDTO;
import org.sut.cashmachine.rest.populator.impl.ProductPopulator;
import org.sut.cashmachine.rest.populator.impl.ReceiptEntryPopulator;
import org.sut.cashmachine.rest.populator.impl.ReceiptPopulator;

import java.util.Arrays;

@Configuration
public class CashMachineBeansConfig {

    @Bean
    public Converter<ReceiptModel, ReceiptDTO> getReceiptConverter(ReceiptPopulator receiptPopulator) {
        AbstractPopulatingConverter<ReceiptModel, ReceiptDTO> receiptConverter = new AbstractPopulatingConverter<>(ReceiptDTO.class) {};
        receiptConverter.setPopulators(Arrays.asList(receiptPopulator));
        return receiptConverter;
    }

    @Bean
    public Converter<ProductModel, ProductDTO> getProductConverter(ProductPopulator productPopulator) {
        AbstractPopulatingConverter<ProductModel, ProductDTO> productConverter = new AbstractPopulatingConverter<>(ProductDTO.class) {};
        productConverter.setPopulators(Arrays.asList(productPopulator));
        return productConverter;
    }

    @Bean
    public Converter<ReceiptEntryModel, ReceiptEntryDTO> getReceiptEntryConverter(ReceiptEntryPopulator receiptEntryPopulator) {
        AbstractPopulatingConverter<ReceiptEntryModel, ReceiptEntryDTO> receiptEntryConverter = new AbstractPopulatingConverter<>(ReceiptEntryDTO.class) {};
        receiptEntryConverter.setPopulators(Arrays.asList(receiptEntryPopulator));
        return receiptEntryConverter;
    }
}
