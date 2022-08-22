package xyz.javaee.psychology_questionnaire.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.Data;


@ApiModel(value="xyz-javaee-psychology_questionnaire-entity-Mentality")
@Data
@TableName(value = "psychologydb.mentality")
public class Mentality implements Serializable {
    /**
     * 心理状态唯一标识
     */
    @TableId(value = "mentality_id", type = IdType.AUTO)
    @ApiModelProperty(value="心理状态唯一标识")
    private Integer mentalityId;

    /**
     * 心理状态内容，如自杀危机
     */
    @TableField(value = "mentality_type")
    @ApiModelProperty(value="心理状态内容，如自杀危机")
    private String mentalityType;

    /**
     * 当时获得的系统生成自我心理纾解方案
     */
    @TableField(value = "self_solution")
    @ApiModelProperty(value="当时获得的系统生成自我心理纾解方案")
    private Integer selfSolution;

    private static final long serialVersionUID = 1L;

    public static final String COL_MENTALITY_ID = "mentality_id";

    public static final String COL_MENTALITY_TYPE = "mentality_type";

    public static final String COL_SELF_SOLUTION = "self_solution";
}