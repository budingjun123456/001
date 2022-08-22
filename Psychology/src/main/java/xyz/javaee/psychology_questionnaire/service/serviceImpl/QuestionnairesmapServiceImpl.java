package xyz.javaee.psychology_questionnaire.service.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.javaee.psychology_questionnaire.dao.QuestionnairesmapMapper;
import xyz.javaee.psychology_questionnaire.entity.Groupsmap;
import xyz.javaee.psychology_questionnaire.entity.Questionnaire;
import xyz.javaee.psychology_questionnaire.entity.Questionnairesmap;
import xyz.javaee.psychology_questionnaire.service.GroupsmapService;
import xyz.javaee.psychology_questionnaire.service.QuestionnaireService;
import xyz.javaee.psychology_questionnaire.service.QuestionnairesmapService;
import xyz.javaee.psychology_questionnaire.service.UserService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 用户组服务层实现
 */
@Service
public class QuestionnairesmapServiceImpl extends ServiceImpl<QuestionnairesmapMapper, Questionnairesmap> implements QuestionnairesmapService {
    @Autowired
    UserService userService;
    @Autowired
    GroupsmapService groupsmapService;
    @Autowired
    QuestionnaireService questionnaireService;
    @Override
    public void createMap(Integer groupId, Integer questionnaireId) {
        Questionnairesmap questionnairesmap=new Questionnairesmap();
        questionnairesmap.setQuestionnaireId(questionnaireId);
        questionnairesmap.setGroupId(groupId);
        this.baseMapper.insert(questionnairesmap);
    }

    @Override
    public List<Questionnaire> findMyQuestionnaires(Integer userId,Integer admin) {
        List<Questionnaire> questionnaireList=new ArrayList<>();
        if(admin==1){
            QueryWrapper<Questionnaire> questionnaireQueryWrapper=new QueryWrapper<>();
            questionnaireQueryWrapper.eq("anministrator_id",userId);
            questionnaireList=this.questionnaireService.getBaseMapper().selectList(questionnaireQueryWrapper);
        }else{
            QueryWrapper<Groupsmap> groupsmapQueryWrapper=new QueryWrapper<>();
            groupsmapQueryWrapper.eq("userId",userId);
            QueryWrapper<Questionnairesmap> questionnairesmapQueryWrapper=new QueryWrapper<>();
            List<Groupsmap> groupsmapList=this.groupsmapService.getBaseMapper().selectList(groupsmapQueryWrapper);
            int len=groupsmapList.size();
            Set<Integer> integerSet=new HashSet<>();
            for(int i=0;i<len;i++){
                Integer groupId = groupsmapList.get(i).getGroupId();
                integerSet.add(groupId);
            }
            List<Questionnairesmap>questionnairesmapList=new ArrayList<>();
            for (Integer groupId:integerSet){
                questionnairesmapQueryWrapper.eq("groupId",groupId);
                questionnairesmapList.addAll(this.baseMapper.selectList(questionnairesmapQueryWrapper));
            }
            for(Questionnairesmap questionnairesmap:questionnairesmapList){
                questionnaireList.add(this.questionnaireService.getBaseMapper().selectById(questionnairesmap.getQuestionnaireId()));
            }
        }
        return questionnaireList;
    }
}
