package xyz.javaee.psychology_questionnaire.entity.DTO;

import lombok.Data;

import java.util.List;


/**
 * @author Zexing Zhang
 * @date 2022/5/14 1:53 AM
 * @Description 用户列表DTO
 */
@Data
public class UserList {
    /**
     * 学生用户的唯一标识
     */
    private Integer userid;

    /**
     * 学生姓名
     */
    private String name;

    /**
     * 学生电话号且为登录账号
     */
    private String telephone;

    /**
     * 学生年龄
     */
    private Integer age;

    /**
     * 心理状态内容，如自杀危机
     */
    private String mentalityTypeStr;

}
