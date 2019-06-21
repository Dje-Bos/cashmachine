package org.sut.cashmachine.dao.receipt;

import data.ReceiptTestData;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.data.querydsl.QSort;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.sut.cashmachine.dao.user.DataJpaUserRepository;
import org.sut.cashmachine.dao.user.UserRepository;
import org.sut.cashmachine.dao.user.UserRepositoryImpl;
import org.sut.cashmachine.dataload.test.data.RoleTestData;
import org.sut.cashmachine.dataload.test.data.UserTestData;
import org.sut.cashmachine.model.order.ReceiptModel;
import org.sut.cashmachine.model.user.AuthType;
import org.sut.cashmachine.model.user.UserModel;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

@TestPropertySource(locations = {"/test-db.properties"})
@Sql(scripts = {"classpath:data-h2.sql"})
@DataJpaTest
@RunWith(SpringJUnit4ClassRunner.class)
public class ReceiptRepositoryTest {
    @Autowired
    DataJpaReceiptRepository receiptRepository;
    @Autowired
    DataJpaUserRepository userRepository;


    ReceiptRepository repository;

    @Before
    public void setUp() throws Exception {
        repository = new ReceiptRepositoryImpl(receiptRepository, userRepository);
    }

    @Test
    public void testFindWithPagination() {
        Page<ReceiptModel> page = repository.findAll(PageRequest.of(0, 2, Sort.by("creationTime").descending()));
        List<ReceiptModel> models = page.getContent();
        assertEquals(List.of(ReceiptTestData.RECEIPT_3, ReceiptTestData.RECEIPT_2).toString(), models.toString());
        page = repository.findAll(page.nextPageable());
        assertEquals(List.of(ReceiptTestData.RECEIPT_1, ReceiptTestData.RECEIPT_0).toString(), page.getContent().toString());
    }

    @Test
    public void testFindById() {
        ReceiptModel receiptModel = repository.getById(ReceiptTestData.RECEIPT_0.getId());

        assertEquals(ReceiptTestData.RECEIPT_0.toString(), receiptModel.toString());
    }

    @Test
    public void testCreateNew() {
        ReceiptModel model = repository.createNew(1);
        assertEquals(model.getCashier().getId().longValue(), 1);

        ReceiptModel actual = receiptRepository.getById(model.getId());

        assertEquals(model.getCashier().getId().longValue(), 1);
        assertEquals(model.getId(), model.getId());

    }

}
