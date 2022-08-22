package xyz.javaee.psychology_questionnaire.service.serviceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import xyz.javaee.psychology_questionnaire.dao.AnalysisMapper;
import xyz.javaee.psychology_questionnaire.dao.QuestionsMapper;
import xyz.javaee.psychology_questionnaire.entity.Analysis;
import xyz.javaee.psychology_questionnaire.entity.Questions;
import xyz.javaee.psychology_questionnaire.service.AnalysisService;
import xyz.javaee.psychology_questionnaire.service.QuestionsService;

@Service
public class AnalysisServiceImpl extends ServiceImpl<AnalysisMapper, Analysis> implements AnalysisService {
    @Override
    public void insertAnalysis(Analysis analysis) {
        this.baseMapper.insert(analysis);
    }
}
