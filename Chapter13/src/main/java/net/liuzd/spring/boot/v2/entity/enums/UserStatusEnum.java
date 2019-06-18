package net.liuzd.spring.boot.v2.entity.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;


public enum UserStatusEnum {

    BAN(0, "禁止"), NORMAL(1, "正常"),;

    UserStatusEnum(int code, String descp) {
        this.code = code;
        this.descp = descp;
    }

    @EnumValue
    private final int    code;
    private final String descp;

    public int getCode() {
        return code;
    }

    public String getDescp() {
        return descp;
    }
}
