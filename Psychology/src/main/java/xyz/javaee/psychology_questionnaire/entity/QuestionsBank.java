package xyz.javaee.psychology_questionnaire.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.Data;


@ApiModel(value="xyz-javaee-psychology_questionnaire-entity-QuestionsBank")
@Data
@TableName(value = "psychologydb.questionsbank")
public class QuestionsBank implements Serializable {
    /**
     * 题库id
     */
    @TableId(value = "question_id", type = IdType.AUTO)
    @ApiModelProperty(value="题库中题目的id")
    private Integer questionId;

    /**
     * 题库中题目的类型id
     */
    @TableField(value = "question_type_id")
    @ApiModelProperty(value="题库中题目的类型id")
    private Integer questionTypeId;

    /**
     * 题目的内容
     */
    @TableField(value = "question_content")
    @ApiModelProperty(value="题目的内容")
    private String questionContent;

    /**
     * 选项类型 0表示逆向 1~5为非常同意~非常不同意  1为正向 1~5为非常不同意~非常同意
     */
    @TableField(value = "section_type")
    @ApiModelProperty(value="选项类型")
    private Integer sectionType;

}