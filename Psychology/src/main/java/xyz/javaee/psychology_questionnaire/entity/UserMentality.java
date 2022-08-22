package xyz.javaee.psychology_questionnaire.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;


@ApiModel(value="xyz-javaee-psychology_questionnaire-entity-UserMentality")
@Data
@TableName(value = "psychologydb.User_Mentality")
public class UserMentality implements Serializable {
    /**
     * 学生用户的唯一标识
     */
    @TableId(value = "userId", type = IdType.AUTO)
    @ApiModelProperty(value="学生用户的唯一标识")
    private Integer userid;

    /**
     * 标识唯一心理状态分类表
     */
    @TableField(value = "mentalityId")
    @ApiModelProperty(value="标识唯一心理状态分类表")
    private Integer mentalityid;

    /**
     * 获得心理状态分析的时间
     */
    @TableField(value = "get_time")
    @ApiModelProperty(value="获得心理状态分析的时间")
    private Date getTime;

    private static final long serialVersionUID = 1L;

    public static final String COL_USERID = "userId";

    public static final String COL_MENTALITYID = "mentalityId";

    public static final String COL_GET_TIME = "get_time";
}
