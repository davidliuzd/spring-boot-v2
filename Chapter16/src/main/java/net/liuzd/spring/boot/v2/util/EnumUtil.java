package net.liuzd.spring.boot.v2.util;

import javax.annotation.Nonnull;

import net.liuzd.spring.boot.v2.common.Enumerable;

public class EnumUtil {

    @SuppressWarnings("rawtypes")
    public static <E extends Enumerable> E of(@Nonnull Class<E> classType, int value) {
        for (E enumConstant : classType.getEnumConstants()) {
            if (value == enumConstant.getValue()) {
                return enumConstant;
            }
        }
        return null;
    }

}