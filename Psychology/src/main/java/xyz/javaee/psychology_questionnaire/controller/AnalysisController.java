package xyz.javaee.psychology_questionnaire.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.models.auth.In;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import xyz.javaee.psychology_questionnaire.entity.Analysis;
import xyz.javaee.psychology_questionnaire.entity.DTO.UserList;
import xyz.javaee.psychology_questionnaire.entity.Submit;
import xyz.javaee.psychology_questionnaire.entity.Warning;
import xyz.javaee.psychology_questionnaire.service.AnalysisService;
import xyz.javaee.psychology_questionnaire.service.SubmitService;
import xyz.javaee.psychology_questionnaire.service.WarningService;
import xyz.javaee.psychology_questionnaire.utils.Result;
import xyz.javaee.psychology_questionnaire.utils.ResultCode;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author Zexing Zhang
 * @date 2022/5/14 2:40 PM
 * @Description 分析结果控制器
 */
@RestController
@RequestMapping("/analysis")
@AllArgsConstructor
public class AnalysisController {
    private final AnalysisService analysisService;

    private final WarningService warningService;
    @PostMapping("/insertAnalysis")
    @ApiOperation("新增一条分析数据")
    public Result getConsultList(@RequestBody Analysis analysis){
        analysisService.insertAnalysis(analysis);
        return Result.ok();
    }
    @PostMapping("/counta")
    @ApiOperation("查看某用户是否做过某问卷")
    public Result counta(@RequestParam Integer userId,@RequestParam Integer questionnaireId){
        QueryWrapper<Analysis> analysisQueryWrapper=new QueryWrapper<>();
        analysisQueryWrapper.eq("userId",userId).eq("questionnaireId",questionnaireId);
        return Result.ok().data("data",analysisService.getBaseMapper().selectCount(analysisQueryWrapper));
    }

    /**
     * 获取未审阅用户分析结果列表
     * @return
     */
    @PostMapping("/getAllAnalysis")
    public Result findUserWarn() {
        QueryWrapper<Analysis> analysisQueryWrapper=new QueryWrapper<>();
        analysisQueryWrapper.eq("peruse",0).orderByDesc("score");
        List<Analysis> analysisList = analysisService.getBaseMapper().selectList(analysisQueryWrapper);
        return Result.ok().data("list", analysisList);
    }

    /**
     * 新增一条预警信息
     * @param
     * @return
     */
    @PostMapping("/addWarn")
    public Result addWarn(@RequestBody Warning warning){
        System.out.println(warning);
        warningService.getBaseMapper().insert(warning);
        return Result.ok();
    }
    /**
     * 修改预警信息查看状态
     * @param
     * @return
     */
    @PostMapping("/editWarn")
    public Result editWarn(Integer id){
        Warning warning= warningService.getBaseMapper().selectById(id);
        warning.setHascheck(1-warning.getHascheck());
        warningService.getBaseMapper().updateById(warning);
        return Result.ok();
    }
    /**
     * 获得最新未查看的预警信息
     * @param
     * @return
     */
    @PostMapping("/findWarn")
    public Result findWarn(){
        QueryWrapper<Warning> warningQueryWrapper=new QueryWrapper<>();
        warningQueryWrapper.eq("hascheck",0);
        Warning warning = warningService.getBaseMapper().selectOne(warningQueryWrapper);
        return Result.ok().data("warn",warning);
    }
    @PostMapping("/edit")
    @ApiOperation("修改状态")
    public Result edit(@RequestParam Integer id){
        Analysis analysis = analysisService.getBaseMapper().selectById(id);
        analysis.setPeruse(1-analysis.getPeruse());
        analysisService.getBaseMapper().updateById(analysis);
        return Result.ok();
    }

    @PostMapping("/count")
    public Result count(){
        QueryWrapper<Analysis> analysisQueryWrapper=new QueryWrapper<>();
        analysisQueryWrapper.eq("peruse",0);
        return Result.ok().data("total",analysisService.getBaseMapper().selectCount(analysisQueryWrapper));
    }
}
