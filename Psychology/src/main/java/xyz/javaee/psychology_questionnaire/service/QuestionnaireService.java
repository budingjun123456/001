package xyz.javaee.psychology_questionnaire.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import xyz.javaee.psychology_questionnaire.entity.Questionnaire;
import com.baomidou.mybatisplus.extension.service.IService;
import xyz.javaee.psychology_questionnaire.entity.QuestionsBank;

/**
 * @author Zexing Zhang
 * @date 2022/5/14 3:55 PM
 * @Description 心理量表服务接口层
 */
public interface QuestionnaireService extends IService<Questionnaire>{

        IPage<Questionnaire> getList(Page<Questionnaire> page);

        void deleteQuestionnaire(Integer questionnaireId);

        IPage<Questionnaire> findSomeQuestionnaires(Page<Questionnaire> questionnairePage, String questionTitleContent);

        QuestionsBank getARandomQuestion(Integer id);

        Integer addAQuestionnaire(Questionnaire questionnaire);
    }
