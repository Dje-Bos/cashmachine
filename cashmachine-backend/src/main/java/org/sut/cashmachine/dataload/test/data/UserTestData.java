package org.sut.cashmachine.dataload.test.data;

import org.sut.cashmachine.model.user.UserModel;

import java.util.HashSet;
import java.util.Set;

public final class UserTestData {
    public static final Set<UserModel> USERS = new HashSet<>();
    public static final UserModel ADMIN = new UserModel( "{noop}password","zatovw@gmail.com", Set.of(RoleTestData.ADMIN_ROLE));
    public static final UserModel CASHIER = new UserModel("cashier", "user@gmail.com", Set.of(RoleTestData.CASHIER_ROLE));
    static {
        USERS.add(new UserModel("sana", "goodgame@ru", true));
        USERS.add(new UserModel("santa", "funnyman@gmail.com", false));
        USERS.add(new UserModel("pudge", "freshmeat@gmail.com", true));
        USERS.add(new UserModel("nick_fury", "nick.fury@gmail.com", true));
        USERS.add(CASHIER);
        USERS.add(ADMIN);
        USERS.add(new UserModel("immachnine", "machine@gmail.com", true));
        USERS.add(new UserModel("debi-lyou", "debi.lyou@ukr.net", true));
        USERS.add(new UserModel("podlivich", "podlivich@mail.ru", true));
    }
}
