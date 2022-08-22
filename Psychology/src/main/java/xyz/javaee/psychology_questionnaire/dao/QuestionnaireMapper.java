package xyz.javaee.psychology_questionnaire.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.context.annotation.Bean;
import xyz.javaee.psychology_questionnaire.entity.Questionnaire;
import xyz.javaee.psychology_questionnaire.entity.QuestionsBank;

/**
 * @author Zexing Zhang
 * @date 2022/5/14 3:52 PM
 * @Description 心理量表持久层
 */
@Mapper
public interface QuestionnaireMapper extends BaseMapper<Questionnaire> {

    IPage<Questionnaire> getSomeList(Page<Questionnaire> questionnairePage,@Param("ew") QueryWrapper<Questionnaire> questionnaireQueryWrapper);

    QuestionsBank getaRandomQuestion(Integer id);
}