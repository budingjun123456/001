package xyz.javaee.psychology_questionnaire.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import lombok.Data;


@ApiModel(value="xyz-javaee-psychology_questionnaire-entity-Submit")
@Data
@TableName(value = "psychologydb.submit")
public class Submit {
    private String name;
    private String mentalityType;
    /**
     * 用户提交问题的唯一标识
     */
    @TableId(value = "submitId", type = IdType.AUTO)
    @ApiModelProperty(value="用户提交问题的唯一标识")
    private Integer submitId;

    /**
     * 用户提交问题的时间
     */
    @TableField(value = "submitTime")
    @ApiModelProperty(value="用户提交问题的时间")
    private Date submitTime;

    /**
     * 用户提交问题的内容
     */
    @TableField(value = "submitContent")
    @ApiModelProperty(value="用户提交问题的内容")
    private String submitContent;

    /**
     * 提交问题的用户标识
     */
    @TableField(value = "userId")
    @ApiModelProperty(value="提交问题的用户标识")
    private Integer userId;

    /**
     * 用户提交问题的标题
     */
    @TableField(value = "type")
    @ApiModelProperty(value="用户提交问题的来源")
    private Integer type;

    public static final String COL_SUBMITID = "submitId";

    public static final String COL_SUBMITTIME = "submitTime";

    public static final String COL_SUBMITCONTENT = "submitContent";

    public static final String COL_USERID = "userId";

    public static final String COL_TYPE = "type";
}