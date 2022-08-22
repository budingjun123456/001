package xyz.javaee.psychology_questionnaire.utils;

/**
 * @author Zexing Zhang
 * @date 2022/5/14 12:00 AM
 * @Description 自定义返回码接口类
 */
public interface CustomizeResultCode {

    /**
     * 获取错误状态码
     * @return 错误状态码
     */
    Integer getCode();

    /**
     * 获取错误信息
     * @return 错误信息
     */

    String getMessage();
}
