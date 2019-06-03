package org.sut.cashmachine.dao.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.sut.cashmachine.model.user.RoleModel;

@Repository
public interface UserRolesRepository extends JpaRepository<RoleModel, Long> {
    RoleModel getRoleEntityByUid(String uid);
}
