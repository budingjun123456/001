package xyz.javaee.psychology_questionnaire.service.serviceImpl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import xyz.javaee.psychology_questionnaire.entity.Reply;
import xyz.javaee.psychology_questionnaire.dao.ReplyMapper;
import xyz.javaee.psychology_questionnaire.service.ReplyService;


@Service
@AllArgsConstructor
public class ReplyServiceImpl extends ServiceImpl<ReplyMapper, Reply> implements ReplyService {

    private ReplyMapper replyMapper;

    @Override
    public List<Reply> getNotBackReply(Integer id) {
        return replyMapper.getNotBackReply(id);
    }

    @Override
    public List<Reply> getUserAllReply(Integer id) {
        return replyMapper.getUserAllReply(id);
    }

    @Override
    public void addReply(Reply reply) {
        replyMapper.addReply(reply);
    }
}

