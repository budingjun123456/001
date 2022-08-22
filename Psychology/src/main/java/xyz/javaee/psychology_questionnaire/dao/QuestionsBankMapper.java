package xyz.javaee.psychology_questionnaire.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import xyz.javaee.psychology_questionnaire.entity.QuestionsBank;

/**
 * 题库的持久层
 */
public interface QuestionsBankMapper extends BaseMapper<QuestionsBank> {
    /**
     * 获取该类型下的某页数据
     * @param page
     * @param queryWrapper
     * @return
     */
    IPage<QuestionsBank> getList(Page<QuestionsBank> page, @Param(Constants.WRAPPER) QueryWrapper<QuestionsBank> queryWrapper);

    IPage<QuestionsBank> getQuestions(Page<QuestionsBank> page, QueryWrapper<QuestionsBank> questionsBankQueryWrapper);
}




