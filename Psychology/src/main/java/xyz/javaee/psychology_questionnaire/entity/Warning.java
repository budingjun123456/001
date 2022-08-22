package xyz.javaee.psychology_questionnaire.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Zexing Zhang
 * @date 2022/5/14 6:42 PM
 * @Description 预警信息实体类
 */
@Data
@TableName(value = "psychologydb.warning")
public class Warning {

    @TableId(value = "warningId", type = IdType.AUTO)
    private Integer groupId;

    @TableField(value = "status")
    private String status;

    @TableField(value = "score")
    private Double score;

    @TableField(value = "name")
    private String name;

    @TableField(value = "telephone")
    private String telephone;

    @TableField(value = "hascheck")
    private Integer hascheck;
}