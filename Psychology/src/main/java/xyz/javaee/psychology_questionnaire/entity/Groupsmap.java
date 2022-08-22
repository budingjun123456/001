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
@ApiModel(value="xyz-javaee-psychology_questionnaire-entity-Groupsmap")
@Data
@TableName(value = "psychologydb.groupsmap")
public class Groupsmap {
    /**
     * 用户组唯一标识
     */
    @TableId(value = "mapId", type = IdType.AUTO)
    @ApiModelProperty(value="匹配唯一标识")
    private Integer mapId;

    /**
     * 用户组名称
     */
    @TableField(value = "userId")
    @ApiModelProperty(value="对应用户组中用户的id")
    private Integer userId;

    /**
     * 创建用户组的管理员id
     */
    @TableField(value = "groupId")
    @ApiModelProperty(value="用户组id")
    private Integer groupId;

    public static final String COL_MAP_ID = "mapId";

    public static final String COL_USER_ID = "userId";

    public static final String COL_GROUP_ID = "groupId";

}