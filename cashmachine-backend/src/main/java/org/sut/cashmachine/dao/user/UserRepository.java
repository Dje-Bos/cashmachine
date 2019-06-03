package org.sut.cashmachine.dao.user;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.sut.cashmachine.model.user.UserModel;

@Repository
public interface UserRepository extends CrudRepository<UserModel, Long> {

    UserModel getUserByEmail(String email);

    UserModel getUserByNickName(String nickname);

    boolean removeUserByEmail(String email);

}
