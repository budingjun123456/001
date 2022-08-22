package xyz.javaee.psychology_questionnaire.service.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import xyz.javaee.psychology_questionnaire.entity.DTO.UserList;
import xyz.javaee.psychology_questionnaire.entity.Solution;
import xyz.javaee.psychology_questionnaire.dao.SolutionMapper;
import xyz.javaee.psychology_questionnaire.entity.User;
import xyz.javaee.psychology_questionnaire.service.SolutionService;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SolutionServiceImpl extends ServiceImpl<SolutionMapper, Solution> implements SolutionService{

    @Resource
    SolutionMapper solutionMapper;

    @Override
    public IPage<Solution> getProgrammeManagementList(Page<Solution> page,Solution solution) {
        System.out.println("进入获取方案列表服务层");
        QueryWrapper<Solution>solutionQueryWrapper = new QueryWrapper<>();
        String solutionTitle=solution.getSolutionTitle();
        System.out.println("前端传入的查询关键字为"+solutionTitle);
        if(null!=solutionTitle&&solutionTitle.trim().length()>0){
            solutionQueryWrapper.like("solution_title",solutionTitle);
        }
        IPage<Solution> page2 = solutionMapper.getProgrammeManagementList(page,solutionQueryWrapper);
        return page2;
    }

    @Override
    public Integer editSolution(Solution solution) {
        QueryWrapper<Solution> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("solution_id",solution.getSolutionId());
        Integer count = Math.toIntExact(this.baseMapper.selectCount(queryWrapper));
        if(count!=0){
            this.baseMapper.update(solution,queryWrapper);
            return count;
        }
        return 0;
    }

    @Override
    public Integer addSolution(Solution solution) {
        QueryWrapper<Solution> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("solution_title",solution.getSolutionTitle());
        Integer count = Math.toIntExact(this.baseMapper.selectCount(queryWrapper));
        System.err.println(count);
        if(count==0){
            this.baseMapper.insert(solution);
            return count;
        }
        return 1;
    }

    @Override
    public Integer deleteSolution(Integer id) {
        QueryWrapper<Solution> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("solution_id",id);
        Integer count = Math.toIntExact(this.baseMapper.selectCount(queryWrapper));
        if (count!=0){
            this.baseMapper.delete(queryWrapper);
            return count;
        }
        return 0;
    }

    @Override
    public List<Solution> getAllSolution() {
        QueryWrapper<Solution> solutionQueryWrapper=new QueryWrapper<>();
        return this.baseMapper.selectList(solutionQueryWrapper);
    }
}
