package xyz.javaee.psychology_questionnaire.utils;

import lombok.Data;
import java.util.HashMap;
import java.util.Map;


/**
 * @author Zexing Zhang
 * @date 2022/5/13 11:50 PM
 * @Description 公共返回结果封装类
 */
@Data
public class Result {
    private Result() {

    }
    //是否成功
    private Boolean success;
    //返回码
    private Integer code;
    //返回消息
    private String message;
    //返回数据
    private Object data = new HashMap<String, Object>();

    public static Result ok() {
        Result result = new Result();
        result.setSuccess(true);
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result;
    }

    public static Result error() {
        Result result = new Result();
        result.setSuccess(false);
        result.setCode(ResultCode.COMMON_FAIL.getCode());
        result.setMessage(ResultCode.COMMON_FAIL.getMessage());
        return result;
    }

    public static Result RCode(boolean success, ResultCode resultCode) {
        Result result = new Result();
        result.setSuccess(success);
        result.setCode(resultCode.getCode());
        result.setMessage(resultCode.getMessage());
        return result;
    }

    public Result message(String message) {
        this.setMessage(message);
        return this;
    }

    public Result code(Integer code) {
        this.setCode(code);
        return this;
    }

    public Result data(Object value) {
        this.setData(value);
        return this;
    }

    public Result data(String key, Object value) {
        if (this.data instanceof HashMap) {
            //>1次添加数据
            ((HashMap) this.data).put(key, value);
        } else {
            //第一次添加数据
            HashMap<String, Object> data = new HashMap<>();
            data.put(key, value);
            this.data = data;
        }
        return this;
    }

    public Result data(Map<String, Object> map) {
        this.setData(map);
        return this;
    }


}
