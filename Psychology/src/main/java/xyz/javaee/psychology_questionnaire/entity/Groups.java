package xyz.javaee.psychology_questionnaire.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 用户组
 */
@ApiModel(value="xyz-javaee-psychology_questionnaire-entity-Groups")
@Data
@TableName(value = "psychologydb.groups")
public class Groups {
    /**
     * 用户组唯一标识
     */
    @TableId(value = "groupId", type = IdType.AUTO)
    @ApiModelProperty(value="用户组唯一标识")
    private Integer groupId;

    /**
     * 用户组名称
     */
    @TableField(value = "groupName")
    @ApiModelProperty(value="用户组名称")
    private String groupName;

    /**
     * 创建用户组的管理员id
     */
    @TableField(value = "adminId")
    @ApiModelProperty(value="创建用户组的管理员id")
    private Integer adminId;

    public static final String COL_GROUP_ID = "groupId";

    public static final String COL_GROUP_NAME = "groupName";

    public static final String COL_ADMIN_ID = "adminId";

}