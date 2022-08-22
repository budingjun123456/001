package xyz.javaee.psychology_questionnaire.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.models.auth.In;
import lombok.Data;


@ApiModel(value="xyz-javaee-psychology_questionnaire-entity-Questions")
@Data
@TableName(value = "psychologydb.questions")
public class Questions {
    /**
     * 问题唯一标识
     */
    @TableId(value = "question_id", type = IdType.AUTO)
    @ApiModelProperty(value="问题唯一标识")
    private Integer questionId;

    /**
     * 问题所属问卷
     */
    @TableField(value = "questionnaire_id")
    @ApiModelProperty(value="问题所属问卷")
    private Integer questionnaireId;

    /**     * 问题内容
     */
    @TableField(value = "content")
    @ApiModelProperty(value="问题内容")
    private String content;

    /**
     * 问题顺序 1（1~5为非常不同意到非常同意）0相反
     */
    @TableField(value = "question_order")
    @ApiModelProperty(value="问题顺序 1（1~5为非常不同意到非常同意）0相反")
    private Integer questionOrder;

    /**
     * 问题类型序号
     */
    @TableField(value = "question_type")
    @ApiModelProperty(value="问题类型序号")
    private Integer questionType;

    public static final String COL_QUESTION_ID = "question_id";

    public static final String COL_QUESTIONNAIRE_ID = "questionnaire_id";

    public static final String COL_CONTENT = "content";

    public static final String COL_QUESTION_ORDER = "question_order";

    public static final String COL_QUESTION_TYPE = "question_type";

}