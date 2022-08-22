package xyz.javaee.psychology_questionnaire.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 题库实体
 */

@ApiModel(value="xyz-javaee-psychology_questionnaire-entity-QuestionBank")
@Data
@TableName(value = "psychologydb.questionbank")
public class QuestionBank implements Serializable {
    /**
     * 题目类型id
     */
    @TableId(value = "question_type_id", type = IdType.AUTO)
    @ApiModelProperty(value="题目类型id")
    private Integer questionTypeId;

    /**
     * 类型名称
     */
    @TableField(value = "question_type")
    @ApiModelProperty(value="类型名称")
    private String questionType;

    /**
     * 类型描述
     */
    @TableField(value = "description")
    @ApiModelProperty(value="类型描述")
    private String description;

    /**
     * 类型权重
     */
    @TableField(value = "weight")
    @ApiModelProperty(value="类型权重")
    private Double weight;
    public static final String QUESTION_TYPE_ID = "question_type_id";

    public static final String QUESTION_TYPE = "question_type";

    public static final String DESCRIPTION = "description";

    public static final String WEIGHT = "weight";
}