package xyz.javaee.psychology_questionnaire.service.serviceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import xyz.javaee.psychology_questionnaire.dao.QuestionBankMapper;
import xyz.javaee.psychology_questionnaire.entity.QuestionBank;
import xyz.javaee.psychology_questionnaire.service.QuestionBankService;

import java.util.List;


@Service
@AllArgsConstructor
public class QuestionBankServiceImpl extends ServiceImpl<QuestionBankMapper, QuestionBank> implements QuestionBankService {

    private QuestionBankMapper questionBankMapper;

    @Override
    public List<QuestionBank> getALL() {
        return questionBankMapper.getAll();
    }
}

