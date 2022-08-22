package xyz.javaee.psychology_questionnaire.service.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import xyz.javaee.psychology_questionnaire.dao.QuestionsBankMapper;
import xyz.javaee.psychology_questionnaire.entity.QuestionsBank;
import xyz.javaee.psychology_questionnaire.entity.Solution;
import xyz.javaee.psychology_questionnaire.service.QuestionsBankService;

import javax.annotation.Resource;


@Service
public class QuestionsBankServiceImpl extends ServiceImpl<QuestionsBankMapper, QuestionsBank> implements QuestionsBankService {

    @Resource
    QuestionsBankMapper questionsBankMapper;

    @Override
    public IPage<QuestionsBank> getList(Page<QuestionsBank> page, QuestionsBank questionsBank) {
        System.out.println("进入获取题目列表服务层");
        QueryWrapper<QuestionsBank>questionsBankQueryWrapper = new QueryWrapper<>();
        Integer questionsBankQuestionTypeId=questionsBank.getQuestionTypeId();
        System.out.println("前端传入的查询关键字为"+questionsBankQuestionTypeId);
        if(questionsBankQuestionTypeId>0){
            questionsBankQueryWrapper.eq("question_type_id",questionsBankQuestionTypeId);
        }
        IPage<QuestionsBank> page2 = questionsBankMapper.getList(page,questionsBankQueryWrapper);
        return page2;
    }

    @Override
    public void deleteQuestion(Integer questionId) {
        System.out.println("进入删除题目服务层");
        QuestionsBank questionsBank=new QuestionsBank();
        questionsBank.setQuestionId(questionId);
        questionsBankMapper.deleteById(questionsBank);
    }

    @Override
    public Integer addQuestion(QuestionsBank questionsBank) {
        QueryWrapper<QuestionsBank> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("question_id",questionsBank.getQuestionId());
        Integer count = Math.toIntExact(this.baseMapper.selectCount(queryWrapper));
        if(count==0){
            this.baseMapper.insert(questionsBank);
            return count;
        }
        return 1;
    }

    @Override
    public Integer editQuestion(QuestionsBank questionsBank) {
        QueryWrapper<QuestionsBank> questionsBankQueryWrapper=new QueryWrapper<>();
        questionsBankQueryWrapper.eq("question_id",questionsBank.getQuestionId());
        Integer count=Math.toIntExact(this.baseMapper.selectCount(questionsBankQueryWrapper));
        if(count!=0){
            this.baseMapper.update(questionsBank,questionsBankQueryWrapper);
            return count;
        }
        return 0;
    }

    @Override
    public IPage<QuestionsBank> findQuestions(Page<QuestionsBank> questionsBankPage, String questionContent,Integer questionTypeId) {
        System.out.println("进入获取方案列表服务层");
        QueryWrapper<QuestionsBank>questionsBankQueryWrapper = new QueryWrapper<>();
        System.out.println("前端传入的查询关键字为"+questionContent);
        if(null!=questionContent&&questionContent.trim().length()>0){
            questionsBankQueryWrapper.like("question_content",questionContent);
        }
        questionsBankQueryWrapper.eq("question_type_id",questionTypeId);
        IPage<QuestionsBank> page2 = questionsBankMapper.getList(questionsBankPage,questionsBankQueryWrapper);
        return page2;
    }
}
