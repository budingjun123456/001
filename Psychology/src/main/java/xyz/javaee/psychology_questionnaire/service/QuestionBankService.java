package xyz.javaee.psychology_questionnaire.service;

import com.baomidou.mybatisplus.extension.service.IService;
import xyz.javaee.psychology_questionnaire.entity.QuestionBank;

import java.util.List;


public interface QuestionBankService extends IService<QuestionBank> {
    List<QuestionBank> getALL();
}

