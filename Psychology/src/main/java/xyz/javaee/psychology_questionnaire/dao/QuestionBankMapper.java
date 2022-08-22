package xyz.javaee.psychology_questionnaire.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import xyz.javaee.psychology_questionnaire.entity.QuestionBank;
import java.util.List;


public interface QuestionBankMapper extends BaseMapper<QuestionBank> {
    List<QuestionBank> getAll();
}
