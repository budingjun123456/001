package xyz.javaee.psychology_questionnaire.service;

import com.baomidou.mybatisplus.extension.service.IService;
import xyz.javaee.psychology_questionnaire.entity.Analysis;
import xyz.javaee.psychology_questionnaire.entity.Questions;

public interface AnalysisService extends IService<Analysis>{

    void insertAnalysis(Analysis analysis);

}
