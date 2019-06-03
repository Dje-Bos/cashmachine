package org.sut.cashmachine.dao.user;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.sut.cashmachine.dao.order.ReceiptEntryRepository;
import org.sut.cashmachine.dao.order.ReceiptRepository;
import org.sut.cashmachine.dao.product.ProductRepository;
import org.sut.cashmachine.dataload.test.data.ProductTestData;
import org.sut.cashmachine.dataload.test.data.ReceiptTestData;
import org.sut.cashmachine.dataload.test.data.UserTestData;
import org.sut.cashmachine.model.order.ReceiptEntryModel;
import org.sut.cashmachine.model.order.ReceiptModel;
import org.sut.cashmachine.model.order.ReceiptModelPK;
import org.sut.cashmachine.model.product.ProductModel;
import org.sut.cashmachine.model.user.UserModel;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@DataJpaTest
@TestPropertySource(locations = {"/test-db.properties"})
public class DefaultUserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ReceiptEntryRepository receiptEntryRepository;

    @Autowired
    private ReceiptRepository receiptRepository;

    @Before
    public void setUp() {

    }

    @Test
    public void createOrUpdateUser() {
        ProductModel product = new ProductModel("Petrushka", "899");
        product.setCode("code");
        product.setName("name");
        productRepository.save(product);
        assertNotNull(product.getId());

        UserModel userModel = new UserModel();
        userModel.setEmail("zatovw");
        userModel.setLastName("zatovw");
        userModel.setNickName("zatovw");
        userModel.setFirstName("zatovw");

        userRepository.save(userModel);
        assertNotNull(userModel.getId());
    }

    @Test
    public void shouldCreateReceipt() {
        ReceiptModel receipt = receiptRepository.save(new ReceiptModel(UserTestData.ADMIN));

        assertNotNull(receipt.getId());
    }

    @Test
    public void shouldCreateReceiptEntry() {
        ProductModel product = productRepository.save(ProductTestData.PARSLEY);
        ReceiptModel receipt = receiptRepository.save(ReceiptTestData.FIRST_ADMIN_RECEIPT);
        ReceiptEntryModel receiptEntry = new ReceiptEntryModel();
        ReceiptModelPK receiptModelPK = new ReceiptModelPK();
        receiptModelPK.setProductId(product.getId());
        receiptModelPK.setReceiptId(receipt.getId());
        receiptEntry.setPk(receiptModelPK);
        receiptEntry.setOrderNumber(1);
        receiptEntry.setOrderQuantity(BigDecimal.ONE);
        receiptEntry.setTotal(BigDecimal.TEN);
        ReceiptEntryModel savedReceiptEntry = receiptEntryRepository.save(receiptEntry);

        assertEquals(savedReceiptEntry, receiptEntryRepository.findByProductAndReceipt(product, receipt));
    }

}