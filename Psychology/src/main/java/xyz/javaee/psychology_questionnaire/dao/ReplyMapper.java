package xyz.javaee.psychology_questionnaire.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import xyz.javaee.psychology_questionnaire.entity.Reply;

import java.util.List;


public interface ReplyMapper extends BaseMapper<Reply> {
    List<Reply> getNotBackReply(Integer id);
    List<Reply> getUserAllReply(Integer id);
    void addReply(Reply reply);
}
