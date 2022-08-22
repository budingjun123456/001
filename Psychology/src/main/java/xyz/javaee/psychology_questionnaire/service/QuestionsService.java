package xyz.javaee.psychology_questionnaire.service;

import xyz.javaee.psychology_questionnaire.entity.Questions;
import com.baomidou.mybatisplus.extension.service.IService;

public interface QuestionsService extends IService<Questions>{
    void insertAQuestion(Questions questions);

}
