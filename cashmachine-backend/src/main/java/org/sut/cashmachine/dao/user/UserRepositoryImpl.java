package org.sut.cashmachine.dao.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.sut.cashmachine.model.user.RoleModel;
import org.sut.cashmachine.model.user.UserModel;

import javax.persistence.EntityManager;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
@Transactional(readOnly = true)
public class UserRepositoryImpl implements UserRepository {

    private DataJpaUserRepository repository;
    private EntityManager entityManager;

    @Autowired
    public UserRepositoryImpl(DataJpaUserRepository repository, EntityManager entityManager) {
        this.repository = repository;
        this.entityManager = entityManager;
    }

    @Override
    public UserModel getUserByEmail(String email) {
        return repository.getUserByEmail(email);
    }

    @Override
    public boolean removeUserByEmail(String email) {
        return repository.removeUserByEmail(email) != 0;
    }

    @Override
    public UserModel getUserById(long id) {
        return repository.getUserById(id);
    }


    @Override
    @Transactional
    public UserModel save(UserModel userModel) {
        if (userModel.getRoles() != null) {
            Set<RoleModel> roles = userModel.getRoles().stream().map(entityManager::merge).collect(Collectors.toSet());
            userModel.setRoles(roles);
        }
        return repository.save(userModel);
    }
}
