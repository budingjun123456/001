package xyz.javaee.psychology_questionnaire.service;

import xyz.javaee.psychology_questionnaire.entity.Reply;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface ReplyService extends IService<Reply> {
    List<Reply> getNotBackReply(Integer id);
    List<Reply> getUserAllReply(Integer id);
    void addReply(Reply reply);
}

