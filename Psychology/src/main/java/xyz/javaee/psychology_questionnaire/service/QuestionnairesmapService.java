package xyz.javaee.psychology_questionnaire.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.swagger.models.auth.In;
import xyz.javaee.psychology_questionnaire.entity.Groupsmap;
import xyz.javaee.psychology_questionnaire.entity.Questionnaire;
import xyz.javaee.psychology_questionnaire.entity.Questionnairesmap;
import xyz.javaee.psychology_questionnaire.entity.User;

import java.util.List;

/**
 * 用户组服务接口层
 */
public interface QuestionnairesmapService extends IService<Questionnairesmap>{

    void createMap(Integer groupId, Integer questionnaireId);
    List<Questionnaire> findMyQuestionnaires(Integer userId,Integer admin);
}
