package xyz.javaee.psychology_questionnaire.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@ApiModel(value="xyz-javaee-psychology_questionnaire-entity-Search")
@Data
@TableName(value = "psychologydb.`search`")
public class Search {
    /**
     * 关键词检索唯一标识
     */
    @TableId(value = "search_id", type = IdType.AUTO)
    @ApiModelProperty(value="关键词检索唯一标识")
    private Integer searchId;

    /**
     * 要检索的关键词
     */
    @TableField(value = "key_word")
    @ApiModelProperty(value="要检索的关键词")
    private String keyWord;

    /**
     * 可解决此关键词所用的应对方案标识，如果有多组方案，id之间用& 连接处理
     */
    @TableField(value = "solutionId_str")
    @ApiModelProperty(value="可解决此关键词所用的应对方案标识，如果有多组方案，id之间用& 连接处理")
    private String solutionidStr;

    public static final String COL_SEARCH_ID = "search_id";

    public static final String COL_KEY_WORD = "key_word";

    public static final String COL_SOLUTIONID_STR = "solutionId_str";
}