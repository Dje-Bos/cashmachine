package org.sut.cashmachine.dataload.test.loader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.sut.cashmachine.dao.receipt.ReceiptEntryRepository;
import org.sut.cashmachine.dao.receipt.DataJpaReceiptRepository;
import org.sut.cashmachine.dao.product.ProductRepository;
import org.sut.cashmachine.dao.user.UserRepository;
import org.sut.cashmachine.dao.user.UserRolesRepository;
import org.sut.cashmachine.dataload.test.data.ProductTestData;
import org.sut.cashmachine.dataload.test.data.ReceiptTestData;
import org.sut.cashmachine.dataload.test.data.RoleTestData;
import org.sut.cashmachine.dataload.test.data.UserTestData;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

public class TestDataLoader implements ApplicationRunner {

    private UserRepository userRepository;
    private UserRolesRepository rolesRepository;
    private ProductRepository productRepository;
    private DataJpaReceiptRepository receiptRepository;
    private ReceiptEntryRepository receiptEntryRepository;

    private EntityManager entityManager;

    public TestDataLoader(UserRepository userRepository, UserRolesRepository rolesRepository, ProductRepository productRepository, DataJpaReceiptRepository receiptRepository, ReceiptEntryRepository receiptEntryRepository) {
        this.userRepository = userRepository;
        this.rolesRepository = rolesRepository;
        this.productRepository = productRepository;
        this.receiptRepository = receiptRepository;
        this.receiptEntryRepository = receiptEntryRepository;
    }

    @Override
    public void run(ApplicationArguments args) {
        loadRoles();
        loadUsers();
        loadProducts();
        loadReceipts();
    }

    private void loadUsers() {
        UserTestData.USERS.forEach(u-> userRepository.save(u));
    }

    private void loadRoles() {
        rolesRepository.saveAll(RoleTestData.ROLES);
    }

    private void loadReceipts() {
        UserTestData.USERS.forEach(entityManager::refresh);
        receiptRepository.saveAll(ReceiptTestData.RECEIPTS);
    }

    private void loadProducts() {
        productRepository.saveAll(ProductTestData.PRODUCTS);
    }

    protected UserRepository getUserRepository() {
        return userRepository;
    }

    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    protected UserRolesRepository getRepository() {
        return rolesRepository;
    }

    public void setRepository(UserRolesRepository rolesRepository) {
        this.rolesRepository = rolesRepository;
    }
}
