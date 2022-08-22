package xyz.javaee.psychology_questionnaire.service.serviceImpl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import xyz.javaee.psychology_questionnaire.entity.Answer;
import xyz.javaee.psychology_questionnaire.dao.AnswerMapper;
import xyz.javaee.psychology_questionnaire.service.AnswerService;

@Service
public class AnswerServiceImpl extends ServiceImpl<AnswerMapper, Answer> implements AnswerService{

}
