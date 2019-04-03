package net.liuzd.spring.boot.v2.entity.enums;

import net.liuzd.spring.boot.v2.common.Enumerable;

@SuppressWarnings("rawtypes")
public enum UserStatusEnum implements Enumerable {

    BAN(0, "禁止"), NORMAL(1, "正常"),;

    UserStatusEnum(int value, String key) {
        this.value = value;
        this.key = key;
    }

    private int    value;
    private String key;

    /**
     * @param code
     * @return
     */
    public static UserStatusEnum get(Integer code) {
        switch (code) {
            case 0:
                return BAN;
            case 1:
                return NORMAL;
            default:
                return BAN;
        }
    }

    @Override
    public String getKey() {
        return this.key;
    }

    @Override
    public int getValue() {
        return this.value;
    }
}
