package xyz.javaee.psychology_questionnaire.entity.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class ReplyAndSubmit {
    /**
     * 管理员下发的回复id
     */
    private Integer replyId;
    /**
     * 管理员下发的回复
     */
    private String replyContent;
    /**
     * 管理员下发的回复时间
     */
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date replyTime;
    /**
     * 下发的回复的管理员id
     */
    private Integer administratorId;
    /**
     * 下发给的用户id
     */
    private Integer userId;
    /**
     * 用户提问的问题id
     */
    private Integer submitId;
    /**
     * 用户提问的问题时间
     */
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date submitTime;
    /**
     * 用户提问的问题内容
     */
    private String submitContent;
}
