package xyz.javaee.psychology_questionnaire.entity.VO;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
public class UserVO {

    /**
     * 学生用户的唯一标识
     */
    private Integer userid;
    /**
     * 管理员和青少年用户标识
     */
    private String  admin;
    /**
     * 学生姓名
     */
    private String name;

    /**
     * 学生电话号且为登录账号
     */
    private String telephone;

    /**
     * 心理状态id，如自杀危机
     */
    private Integer mentalityId;

    /**
     * 学生年龄
     */
    private Integer age;
}
