package xyz.javaee.psychology_questionnaire.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import lombok.Data;


@ApiModel(value="xyz-javaee-psychology_questionnaire-entity-Questionnaire")
@Data
@TableName(value = "psychologydb.questionnaire")
public class Questionnaire {
    /**
     * 问卷唯一标识
     */
    @TableId(value = "questionnaire_id", type = IdType.AUTO)
    @ApiModelProperty(value="问卷唯一标识")
    private Integer questionnaireId;

    /**
     * 问卷被创建的管理员
     */
    @TableField(value = "anministrator_id")
    @ApiModelProperty(value="问卷被创建的管理员")
    private Integer anministratorId;

    /**
     * 问卷标题
     */
    @TableField(value = "title")
    @ApiModelProperty(value="问卷标题")
    private String title;

    /**
     * 问卷设定的开始时间
     */
    @TableField(value = "start_time")
    @ApiModelProperty(value="问卷设定的开始时间")
    private Date startTime;

    /**
     * 问卷设定的结束时间
     */
    @TableField(value = "end_time")
    @ApiModelProperty(value="问卷设定的结束时间")
    private Date endTime;

    /**
     * 问卷创建时间
     */
    @TableField(value = "create_time")
    @ApiModelProperty(value="问卷创建时间")
    private Date createTime;

    /**
     * 是否为有效问卷，0为无效，1为有效
     */
    @TableField(value = "valid_flag")
    @ApiModelProperty(value="是否为有效问卷，0为无效，1为有效")
    private Boolean validFlag;

    /**
     * 问卷备注
     */
    @TableField(value = "remark")
    @ApiModelProperty(value="问卷备注")
    private String remark;

    public static final String COL_QUESTIONNAIRE_ID = "questionnaire_id";

    public static final String COL_ANMINISTRATOR_ID = "anministrator_id";

    public static final String COL_TITLE = "title";

    public static final String COL_START_TIME = "start_time";

    public static final String COL_END_TIME = "end_time";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_VALID_FLAG = "valid_flag";

    public static final String COL_REMARK = "remark";
}