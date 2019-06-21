package org.sut.cashmachine.dao.user;

import org.springframework.stereotype.Repository;
import org.sut.cashmachine.model.user.UserModel;

public interface UserRepository {

    UserModel getUserByEmail(String email);

    boolean removeUserByEmail(String email);

    UserModel getUserById(long id);

    UserModel save(UserModel userModel);
}
