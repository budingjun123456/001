package xyz.javaee.psychology_questionnaire.service.serviceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import xyz.javaee.psychology_questionnaire.dao.ReplyAndSubmitMapper;
import xyz.javaee.psychology_questionnaire.dao.ReplyMapper;
import xyz.javaee.psychology_questionnaire.dao.SubmitMapper;
import xyz.javaee.psychology_questionnaire.entity.DTO.ReplyAndSubmit;
import xyz.javaee.psychology_questionnaire.entity.Reply;
import xyz.javaee.psychology_questionnaire.entity.Submit;
import xyz.javaee.psychology_questionnaire.service.ReplyAndSubmitService;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ReplyAndSubmitServiceImpl extends ServiceImpl<ReplyAndSubmitMapper, ReplyAndSubmit> implements ReplyAndSubmitService {

    private SubmitMapper submitMapper;
    private ReplyMapper replyMapper;
    @Override
    public List<ReplyAndSubmit> getUserReplyAndSubmit(Integer id) {
        List<Submit> lists=submitMapper.getUserAllSubmit(id);
        List<Reply> listr=replyMapper.getUserAllReply(id);
        List<ReplyAndSubmit> list= new ArrayList<>();
        for(int i=0;i<lists.size();++i){
            ReplyAndSubmit ras=new ReplyAndSubmit();
            ras.setSubmitId(lists.get(i).getSubmitId());
            ras.setSubmitTime(lists.get(i).getSubmitTime());
            ras.setSubmitContent(lists.get(i).getSubmitContent());
            list.add(ras);
        }
        for(int i=0;i<listr.size();++i){
            ReplyAndSubmit ras=new ReplyAndSubmit();
            ras.setReplyId(listr.get(i).getReplyId());
            ras.setReplyContent(listr.get(i).getReplyContent());
            ras.setReplyTime(listr.get(i).getReplyTime());
            ras.setUserId(listr.get(i).getUserId());
            ras.setAdministratorId(listr.get(i).getAdministratorId());
            list.add(ras);
        }
        return list;
    }
}
