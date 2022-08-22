package xyz.javaee.psychology_questionnaire.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import xyz.javaee.psychology_questionnaire.entity.Solution;


public interface SolutionMapper extends BaseMapper<Solution> {
    IPage<Solution> getProgrammeManagementList(Page<Solution> page, @Param(Constants.WRAPPER) QueryWrapper<Solution> queryWrapper);

}




