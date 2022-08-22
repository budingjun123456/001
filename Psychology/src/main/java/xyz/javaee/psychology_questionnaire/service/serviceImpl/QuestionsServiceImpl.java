package xyz.javaee.psychology_questionnaire.service.serviceImpl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import xyz.javaee.psychology_questionnaire.dao.QuestionsMapper;
import xyz.javaee.psychology_questionnaire.entity.Questions;
import xyz.javaee.psychology_questionnaire.service.QuestionsService;

@Service
public class QuestionsServiceImpl extends ServiceImpl<QuestionsMapper, Questions> implements QuestionsService{
    @Override
    public void insertAQuestion(Questions questions) {
        this.baseMapper.insert(questions);
    }
}
