package org.sut.cashmachine.util;

import java.util.Objects;

public class StringUtil {
    public static boolean isNotBlank(String s) {

        return s != null && !s.isBlank();
    }
    public static boolean isBlank(String s) {
        return !isNotBlank(s);
    }

}
