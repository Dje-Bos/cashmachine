package org.sut.cashmachine.dao.user;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.sut.cashmachine.dao.receipt.ReceiptEntryRepository;
import org.sut.cashmachine.dao.receipt.DataJpaReceiptRepository;
import org.sut.cashmachine.dao.product.DataJpaProductRepository;
import org.sut.cashmachine.dataload.test.data.ProductTestData;
import org.sut.cashmachine.dataload.test.data.ReceiptTestData;
import org.sut.cashmachine.dataload.test.data.UserTestData;
import org.sut.cashmachine.model.order.ReceiptEntryModel;
import org.sut.cashmachine.model.order.ReceiptModel;
import org.sut.cashmachine.model.product.ProductModel;
import org.sut.cashmachine.model.user.AuthType;
import org.sut.cashmachine.model.user.UserModel;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@DataJpaTest
@TestPropertySource(locations = {"classpath:test-db.properties"})
public class DefaultUserRepositoryTest {

    @Autowired
    private DataJpaUserRepository dataJpaUserRepository;

    @Autowired
    private DataJpaProductRepository productRepository;

    @Autowired
    private ReceiptEntryRepository receiptEntryRepository;

    @Autowired
    private DataJpaReceiptRepository receiptRepository;

    @Before
    public void setUp() {

    }

    @Test
    public void createOrUpdateUser() {
        ProductModel product = new ProductModel("Petrushka", "899");
        product.setCode("code");
        product.setName("name");
        product.setUnit("unit");
        product.setInStock(3.0);
        product.setPrice(48.0);
        productRepository.save(product);
        assertNotNull(product.getId());

        UserModel userModel = new UserModel();
        userModel.setEmail("zatovw@gmail.com");
        userModel.setName("zatovw");
        userModel.setAuth(AuthType.GOOGLE);

        dataJpaUserRepository.save(userModel);
        assertNotNull(userModel.getId());
    }

    @Test
    public void shouldCreateReceipt() {
        ReceiptModel receipt = receiptRepository.save(new ReceiptModel(UserTestData.ADMIN));

        assertNotNull(receipt.getId());
    }

    @Test
    @Ignore
    public void shouldCreateReceiptEntry() {
        ProductModel product = productRepository.save(ProductTestData.PARSLEY);
        ReceiptModel receipt = receiptRepository.save(ReceiptTestData.FIRST_ADMIN_RECEIPT);
        ReceiptEntryModel receiptEntry = new ReceiptEntryModel();
        receiptEntry.setOrderQuantity(BigDecimal.ONE);
        receiptEntry.setProduct(product);
        receiptEntry.setReceipt(receipt);
        receiptEntry.setTotal(BigDecimal.TEN);
        ReceiptEntryModel savedReceiptEntry = receiptEntryRepository.save(receiptEntry);

        assertEquals(savedReceiptEntry, receiptEntryRepository.findByProductIdAndReceiptId(product.getId(), receipt.getId()));
    }

}