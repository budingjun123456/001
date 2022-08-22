package xyz.javaee.psychology_questionnaire.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@ApiModel(value="xyz-javaee-psychology_questionnaire-entity-Answer")
@Data
@TableName(value = "psychologydb.answer")
public class Answer {
    /**
     * 作答问卷问题的唯一标识
     */
    @TableId(value = "answer_id", type = IdType.AUTO)
    @ApiModelProperty(value="作答问卷问题的唯一标识")
    private Integer answerId;

    /**
     * 作答者学生唯一标识
     */
    @TableField(value = "user_id")
    @ApiModelProperty(value="作答者学生唯一标识")
    private Integer userId;

    /**
     * 作答记录json一次存一套题
     */
    @TableField(value = "answer_str")
    @ApiModelProperty(value="作答记录json一次存一套题")
    private String answerStr;

    public static final String COL_ANSWER_ID = "answer_id";

    public static final String COL_USER_ID = "user_id";

    public static final String COL_ANSWER_STR = "answer_str";
}
