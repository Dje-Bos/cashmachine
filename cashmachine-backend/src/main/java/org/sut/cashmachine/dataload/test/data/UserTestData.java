package org.sut.cashmachine.dataload.test.data;

import org.sut.cashmachine.model.user.AuthType;
import org.sut.cashmachine.model.user.UserModel;

import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

public final class UserTestData {
    public static final Set<UserModel> USERS = new HashSet<>();
    public static final UserModel ADMIN = new UserModel( "{noop}password","zatow@gmail.com", Set.of());
    public static final UserModel CASHIER = new UserModel("cashier", "user@gmail.com", Set.of());
    public static final UserModel SAVED_USER = new UserModel("name", null, AuthType.GOOGLE, "email@example.com", OffsetDateTime.from(ZonedDateTime.parse("2019-05-17T18:47:52", DateTimeFormatter.ISO_LOCAL_DATE_TIME.withZone(ZoneId.systemDefault()))), true, null, Set.of());
    static {
        SAVED_USER.setId(1L);
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
