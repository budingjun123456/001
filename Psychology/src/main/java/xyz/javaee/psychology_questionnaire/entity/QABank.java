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
 * 心理问答案例实体
 */

@ApiModel(value="xyz-javaee-psychology_questionnaire-entity-QABank")
@Data
@TableName(value = "psychologydb.qabank")
public class QABank implements Serializable {
    /**
     * id
     */
    @TableId(value = "qaid", type = IdType.AUTO)
    @ApiModelProperty(value="id")
    private Integer qaid;

    /**
     * 问题
     */
    @TableField(value = "q")
    @ApiModelProperty(value="问题")
    private String q;

    /**
     * 答案
     */
    @TableField(value = "a")
    @ApiModelProperty(value="答案")
    private String a;
}