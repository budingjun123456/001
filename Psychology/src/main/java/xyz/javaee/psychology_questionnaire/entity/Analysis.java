package xyz.javaee.psychology_questionnaire.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.sql.Timestamp;
import java.util.Date;

/**
 * @author Zexing Zhang
 * @date 2022/5/14 2:36 PM
 * @Description 分析结果实体类
 */
@Data
@TableName(value = "psychologydb.analysis")
public class Analysis {
    /**
     * 心理状态分析id
     */
    @TableId(value = "analysisId", type = IdType.AUTO)
    @ApiModelProperty(value="心理状态分析id")
    private Integer analysisId;

    /**
     * 被分析的用户id
     */
    @TableField(value = "userId")
    @ApiModelProperty(value="被分析的用户id")
    private Integer userId;

    /**
     * 分析结果心理状态id
     */
    @TableField(value = "mentalityId")
    @ApiModelProperty(value="分析结果心理状态id")
    private Integer mentalityId;

    /**
     * 分析得到的评分
     */
    @TableField(value = "score")
    @ApiModelProperty(value="分析得到的评分")
    private Double score;

    /**
     * 分析时的时间戳
     */
    @TableField(value = "time")
    @ApiModelProperty(value="分析时的时间戳")
    private Timestamp time;
    /**
     * 记录是否被审阅
     */
    @TableField(value = "peruse")
    @ApiModelProperty(value="记录是否被审阅\t\n")
    private Integer peruse;
    /**
     * 获得此次分析的问卷id
     */
    @TableField(value = "questionnaireId")
    @ApiModelProperty(value="获得此次分析的问卷id")
    private Integer questionnaireId;
}