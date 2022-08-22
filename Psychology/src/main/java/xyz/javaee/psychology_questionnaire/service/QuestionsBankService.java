package xyz.javaee.psychology_questionnaire.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import xyz.javaee.psychology_questionnaire.entity.QuestionsBank;
import xyz.javaee.psychology_questionnaire.entity.Solution;


public interface QuestionsBankService extends IService<QuestionsBank>{

    IPage<QuestionsBank> getList(Page<QuestionsBank> page, QuestionsBank questionsBank);

    void deleteQuestion(Integer questionId);

    Integer addQuestion(QuestionsBank questionsBank);

    Integer editQuestion(QuestionsBank questionsBank);

    IPage<QuestionsBank> findQuestions(Page<QuestionsBank> questionsBankPage, String questionContent,Integer questionTypeId);
}
