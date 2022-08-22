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
@ApiModel(value="xyz-javaee-psychology_questionnaire-entity-Questionnairesmap")
@Data
@TableName(value = "psychologydb.questionnairesmap")
public class Questionnairesmap {
    /**
     * 问卷和用户组对应id
     */
    @TableId(value = "mapId", type = IdType.AUTO)
    @ApiModelProperty(value="问卷和用户组对应id")
    private Integer mapId;

    /**
     * 用户组名称
     */
    @TableField(value = "questionnaireId")
    @ApiModelProperty(value="问卷id")
    private Integer questionnaireId;

    /**
     * 创建用户组的管理员id
     */
    @TableField(value = "groupId")
    @ApiModelProperty(value="用户组id")
    private Integer groupId;

    public static final String COL_MAP_ID = "mapId";

    public static final String COL_QUESTIONNAIRE_ID = "questionnaireId";

    public static final String COL_GROUP_ID = "groupId";

}