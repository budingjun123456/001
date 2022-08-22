package xyz.javaee.psychology_questionnaire.utils;
/**
 * @author Zexing Zhang
 * @date 2022/5/13 11:58 PM
 * @Description 自定义返回码实现类
 */
public enum ResultCode implements CustomizeResultCode{
    /* 成功 */
    SUCCESS(200, null),

    /* 默认失败 */
    COMMON_FAIL(999, "失败"),

    /* 参数错误：1000～1999 */
    PARAM_NOT_VALID(1001, "参数无效"),
    PARAM_IS_BLANK(1002, "参数为空"),
    PARAM_TYPE_ERROR(1003, "参数类型错误"),
    PARAM_NOT_COMPLETE(1004, "参数缺失"),

    /* 用户错误 */
    USER_NOT_LOGIN(2001, "用户未登录"),
    USER_ACCOUNT_EXPIRED(2002, "账号已过期"),
    USER_CREDENTIALS_ERROR(2003, "密码错误"),
    USER_CREDENTIALS_EXPIRED(2004, "密码过期"),
    USER_ACCOUNT_DISABLE(2005, "账号不可用"),
    USER_ACCOUNT_LOCKED(2006, "账号被锁定"),
    USER_ACCOUNT_NOT_EXIST(2007, "账号不存在"),
    USER_ACCOUNT_ALREADY_EXIST(2008, "账号已存在"),
    USER_ACCOUNT_USE_BY_OTHERS(2009, "账号下线"),
    USER_CAPTCHA_CODE_ERR(2010, "验证码错误"),
    USER_NO_USE(2011,"无效/已注销用户"),


    /*部门错误*/
    DEPARTMENT_NOT_EXIST(3007, "部门不存在"),
    DEPARTMENT_ALREADY_EXIST(3008, "部门已存在"),

    /* 业务错误 */
    NO_PERMISSION(3001, "没有权限"),
    NO_REDIS(3002, "REDIS运行异常"),
    DATABASE_ERROR(3003,"CRUD异常"),
    /*角色错误*/
    ROLE_ALREADY_EXIST(4001,"角色已存在"),

    /*运行时异常*/
    ARITHMETIC_EXCEPTION(9001,"算数异常");


    private Integer code;

    private String message;

    ResultCode(Integer code,String message){
        this.code = code;
        this.message = message;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
