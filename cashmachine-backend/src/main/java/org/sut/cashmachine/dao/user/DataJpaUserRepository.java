package org.sut.cashmachine.dao.user;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.sut.cashmachine.model.user.UserModel;

import javax.transaction.Transactional;

public interface DataJpaUserRepository extends CrudRepository<UserModel, Long> {

    UserModel getUserByEmail(String email);

    @Transactional
    @Modifying
    int removeUserByEmail(String email);

    UserModel getUserById(long id);
}
