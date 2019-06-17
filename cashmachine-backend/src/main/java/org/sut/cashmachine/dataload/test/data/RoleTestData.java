package org.sut.cashmachine.dataload.test.data;

import org.sut.cashmachine.model.user.RoleModel;

import java.util.HashSet;
import java.util.Set;

public final class RoleTestData {
    public static final Set<RoleModel> ROLES = new HashSet<>();
    public static final RoleModel ADMIN_ROLE = new RoleModel("ADMIN");
    public static final RoleModel CASHIER_ROLE = new RoleModel("CASHIER");
    public static final RoleModel SENIOR_CASHIER_ROLE = new RoleModel("SENIOR_CASHIER");
    public static final RoleModel MERCHANDISE_ROLE = new RoleModel("MERCHANDISE");

    static {
        ROLES.addAll(Set.of(ADMIN_ROLE, CASHIER_ROLE, SENIOR_CASHIER_ROLE, MERCHANDISE_ROLE));
    }
}
