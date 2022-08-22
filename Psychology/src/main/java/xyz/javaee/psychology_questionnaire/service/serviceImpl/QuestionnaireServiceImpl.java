package xyz.javaee.psychology_questionnaire.service.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import xyz.javaee.psychology_questionnaire.dao.QuestionnaireMapper;
import xyz.javaee.psychology_questionnaire.entity.Questionnaire;
import xyz.javaee.psychology_questionnaire.entity.QuestionsBank;
import xyz.javaee.psychology_questionnaire.service.QuestionnaireService;
/**
 * @author Zexing Zhang
 * @date 2022/5/14 3:52 PM
 * @Description 量表控制类
 */
@Service
public class QuestionnaireServiceImpl extends ServiceImpl<QuestionnaireMapper, Questionnaire> implements QuestionnaireService{
    @Autowired
    QuestionnaireMapper questionnaireMapper;
    @Override
    public IPage<Questionnaire> getList(Page<Questionnaire> page) {
        System.out.println("进入问卷服务层");
        QueryWrapper<Questionnaire> questionnaireQueryWrapper=new QueryWrapper<>();
        IPage<Questionnaire> questionnaireIPage= questionnaireMapper.selectPage(page,questionnaireQueryWrapper);
        return questionnaireIPage;
    }

    @Override
    public void deleteQuestionnaire(Integer questionnaireId) {
        System.out.println("进入删除问卷服务层");
        Questionnaire questionnaire=new Questionnaire();
        questionnaire.setQuestionnaireId(questionnaireId);
        questionnaireMapper.deleteById(questionnaire);
    }

    @Override
    public IPage<Questionnaire> findSomeQuestionnaires(Page<Questionnaire> questionnairePage, String questionTitleContent) {
        System.out.println("进入获取问卷列表服务层");
        QueryWrapper<Questionnaire>questionnaireQueryWrapper = new QueryWrapper<>();
        System.out.println("前端传入的查询关键字为"+questionTitleContent);
        if(null!=questionTitleContent&&questionTitleContent.trim().length()>0){
            questionnaireQueryWrapper.like("title",questionTitleContent);
        }
        IPage<Questionnaire> page2 = questionnaireMapper.getSomeList(questionnairePage,questionnaireQueryWrapper);
        return page2;
    }

    @Override
    public QuestionsBank getARandomQuestion(Integer id) {
        return questionnaireMapper.getaRandomQuestion(id);
    }

    @Override
    public Integer addAQuestionnaire(Questionnaire questionnaire) {
        this.baseMapper.insert(questionnaire);
        return questionnaire.getQuestionnaireId();
    }
}
