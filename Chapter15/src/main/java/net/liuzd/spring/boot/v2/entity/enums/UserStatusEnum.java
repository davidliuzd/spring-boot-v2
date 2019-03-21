package net.liuzd.spring.boot.v2.entity.enums;

/**
 * @date 2018/8/31
 */
public enum UserStatusEnum {

    BAN(0, "禁止"), NORMAL(1, "正常"),;

    UserStatusEnum(int code, String descp) {
        this.code = code;
        this.descp = descp;
    }

    private final int    code;
    private final String descp;

    public int getCode() {
        return code;
    }

    public String getDescp() {
        return descp;
    }
}
