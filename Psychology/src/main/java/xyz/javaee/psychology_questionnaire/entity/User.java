package xyz.javaee.psychology_questionnaire.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.Data;

/**
 * @author Zexing Zhang
 * @date 2022/5/14 12:47 AM
 * @Description 用户表对应实体类
 */
@Data
@TableName(value = "psychologydb.`user`")
public class User implements Serializable {
    /**
     * 学生用户的唯一标识
     */
    @TableId(value = "userId", type = IdType.AUTO)
    private Integer userid;

    /**
     * 是否是管理员
     */
    @TableField(value = "admin")
    private Integer admin;

    /**
     * 学生姓名
     */
    @TableField(value = "name"  , condition = SqlCondition.LIKE)
    private String name;

    /**
     * 学生电话号且为登录账号
     */
    @TableField(value = "telephone"  , condition = SqlCondition.LIKE)
    private String telephone;

    /**
     * 学生年龄
     */
    @TableField(value = "age")
    private Integer age;

    /**
     * 学生登录密码，加密为md5格式后保存
     */
    @TableField(value = "password")
    private String password;
    /**
     * 标识唯一心理状态分类表
     */
    @TableField(value = "mentalityId")
    private Integer mentalityId;

    public static final String COL_TELEPHONE = "telephone";
}
