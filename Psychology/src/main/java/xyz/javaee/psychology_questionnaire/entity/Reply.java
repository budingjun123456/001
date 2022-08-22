package xyz.javaee.psychology_questionnaire.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;


@ApiModel(value="xyz-javaee-psychology_questionnaire-entity-Reply")
@Data
@TableName(value = "psychologydb.reply")
public class Reply implements Serializable {
    /**
     * 管理员回复的唯一标识
     */
    @TableId(value = "reply_id", type = IdType.AUTO)
    @ApiModelProperty(value="管理员回复的唯一标识")
    private Integer replyId;

    /**
     * 回复的内容
     */
    @TableField(value = "reply_content")
    @ApiModelProperty(value="回复的内容")
    private String replyContent;

    /**
     * 回复的时间
     */
    @TableField(value = "reply_time")
    @ApiModelProperty(value="回复的时间")
    private Date replyTime;

    /**
     * 回复的管理员
     */
    @TableField(value = "administrator_id")
    @ApiModelProperty(value="回复的管理员")
    private Integer administratorId;

    /**
     * 回复的问题
     */
    @TableField(value = "user_id")
    @ApiModelProperty(value="回复给谁")
    private Integer userId;

    /**
     * 是否推送回
     */
    @TableField(value = "is_back")
    @ApiModelProperty(value="是否推送回")
    private Boolean isBack;

    private static final long serialVersionUID = 1L;

    public static final String COL_REPLY_ID = "reply_id";

    public static final String COL_REPLY_CONTENT = "reply_content";

    public static final String COL_REPLY_TIME = "reply_time";

    public static final String COL_ADMINISTRATOR_ID = "administrator_id";

    public static final String COL_USER_ID = "user_id";

    public static final String COL_IS_BACK = "is_back";
}