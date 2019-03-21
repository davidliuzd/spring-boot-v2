package net.liuzd.spring.boot.v2.constants;

public enum ErrCodeEnum {
   
    SYS_ERROR("S0000001", "系统异常"),
    LOGIN_FAIL("U0000001", "用户名或密码不正确"),
    NO_USER("U000002", "用户不存在"),
    ;

    ErrCodeEnum(String code, String msg){
        this.code = code;
        this.msg = msg;
    }

    public String code;
    public String msg;
}
