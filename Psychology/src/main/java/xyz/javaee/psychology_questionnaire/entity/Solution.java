package xyz.javaee.psychology_questionnaire.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.Data;


@ApiModel(value="xyz-javaee-psychology_questionnaire-entity-Solution")
@Data
@TableName(value = "psychologydb.solution")
public class Solution implements Serializable {
    /**
     * 应对方案的唯一标识
     */
    @TableId(value = "solution_id", type = IdType.AUTO)
    @ApiModelProperty(value="应对方案的唯一标识")
    private Integer solutionId;

    /**
     * 应对方案的内容
     */
    @TableField(value = "solution_content")
    @ApiModelProperty(value="应对方案的内容")
    private String solutionContent;

    /**
     * 上传/撰写该应对方案的管理员
     */
    @TableField(value = "anministrator_id")
    @ApiModelProperty(value="上传/撰写该应对方案的管理员")
    private Integer anministratorId;

    /**
     * 方案标题
     */
    @TableField(value = "solution_title")
    @ApiModelProperty(value="方案标题")
    private String solutionTitle;

    private static final long serialVersionUID = 1L;

    public static final String COL_SOLUTION_ID = "solution_id";

    public static final String COL_SOLUTION_CONTENT = "solution_content";

    public static final String COL_ANMINISTRATOR_ID = "anministrator_id";

    public static final String COL_SOLUTION_TITLE = "solution_title";
}