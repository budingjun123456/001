package xyz.javaee.psychology_questionnaire.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import xyz.javaee.psychology_questionnaire.entity.Solution;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;


public interface SolutionService extends IService<Solution>{

    IPage<Solution> getProgrammeManagementList(Page<Solution> page,Solution solution);

    Integer editSolution(Solution solution);

    Integer addSolution(Solution solution);

    Integer deleteSolution(Integer id);


    List<Solution> getAllSolution();
}
