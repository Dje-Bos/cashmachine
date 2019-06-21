package org.sut.cashmachine.dao.user;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.sut.cashmachine.CashmachineApplication;
import org.sut.cashmachine.dataload.test.data.RoleTestData;
import org.sut.cashmachine.dataload.test.data.UserTestData;
import org.sut.cashmachine.model.user.AuthType;
import org.sut.cashmachine.model.user.UserModel;

import javax.persistence.EntityManager;
import java.util.Set;

import static org.junit.Assert.*;

@TestPropertySource(locations = {"classpath:test-db.properties"})
@DataJpaTest
@Sql(scripts = {"classpath:data-h2.sql"})
@RunWith(SpringJUnit4ClassRunner.class)
public class UserRepositoryTest {
    @Autowired
    DataJpaUserRepository dataJpaUserRepository;
    @Autowired
    EntityManager entityManager;
    @Autowired
    UserRolesRepository userRolesRepository;


    UserRepository userRepository;

    @Before
    public void setUp() throws Exception {
        userRepository = new UserRepositoryImpl(dataJpaUserRepository, userRolesRepository, entityManager);
    }

    @Test
    public void testSaveUserWithRoles() {
        UserModel saved = userRepository.save(new UserModel("Batman", "pass", AuthType.GOOGLE, "batman@gotham.city", null, true, null, Set.of(RoleTestData.CASHIER_ROLE, RoleTestData.MERCHANDISE_ROLE)));
        UserModel user = userRepository.getUserByEmail("batman@gotham.city");

        assertNotNull(user);
        user.setId(saved.getId());
        assertEquals(saved.toString(), user.toString());

    }

    @Test
    public void testGetUserByEmail() {
        UserModel user = userRepository.getUserByEmail("email@example.com");
        assertEquals(UserTestData.SAVED_USER.toString(), user.toString());
    }

    @Test
    public void testGetUserById() {
        UserModel user = userRepository.getUserById(UserTestData.SAVED_USER.getId());

        assertEquals(UserTestData.SAVED_USER.toString(), user.toString());
    }

    @Test
    public void testRemoveUserByEmail() {
        boolean removed = userRepository.removeUserByEmail("iam@batman.com");
        assertTrue(removed);
        UserModel user = userRepository.getUserById(2);

        assertNull(user);
    }

}
