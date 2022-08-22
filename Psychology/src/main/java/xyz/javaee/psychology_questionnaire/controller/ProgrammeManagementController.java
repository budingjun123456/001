package xyz.javaee.psychology_questionnaire.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import xyz.javaee.psychology_questionnaire.entity.Solution;
import xyz.javaee.psychology_questionnaire.service.SolutionService;
import xyz.javaee.psychology_questionnaire.utils.Result;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

@RestController
@RequestMapping("/programmeManagement")
@Api(value = "方案管理模块", tags = "方案管理接口")
public class ProgrammeManagementController {

    @Resource
    SolutionService solutionService;

    /**
     * 查询Solution列表
     *
     * @param current
     * @param size
     * @param solution
     * @return
     */
    @PostMapping ("/getProgrammeManagementList")
    @ApiOperation(value = "获取方案列表", notes = "获取方案列表信息")
    public Result getProgrammeManagementList(@RequestParam(required = false, defaultValue = "1") Integer current,
                                             @RequestParam(required = false, defaultValue = "6") Integer size,
                                             @RequestBody Solution solution) {
        System.out.println("进入获取方案控制器");
        Page<Solution> page=new Page<>(current,size);
        IPage<Solution> solutionIPage = solutionService.getProgrammeManagementList(page,solution);
        System.out.println("获取到的结果：");
        System.out.println(solutionIPage);
        System.out.println(solutionIPage.getRecords());
        System.out.println(solutionIPage.getTotal());
        return Result.ok().data("solutions", solutionIPage.getRecords()).data("total", solutionIPage.getTotal());
    }

    @PostMapping("/getAllSolution")
    @ApiOperation(value = "所有方案",notes = "获得所有方案信息")
    private Result getAllSolution(){
        List<Solution>solutionList=solutionService.getAllSolution();
        return Result.ok().data("data", solutionList);
    }

    @ApiOperation(value = "修改方案",notes = "修改方案信息")
    @PostMapping("/editSolution")
    private Result editSolution(@RequestBody Solution solution) {
        Integer count = solutionService.editSolution(solution);
        if (count != 0) {
            return Result.ok().data("data", solution).message("修改成功！");
        }
        return Result.error();
    }

    @ApiOperation(value = "添加方案", notes = "添加方案信息")
    @PostMapping("/addSolution")
    private Result addSolution(@RequestBody Solution solution) {
        Integer count = solutionService.addSolution(solution);
        if (count == 0) {
            return Result.ok().data("data", solution).message("添加成功！");
        }
        return Result.error().message("该方案名称已存在！");
    }

    @ApiOperation(value = "删除方案", notes = "删除方案信息")
    @PostMapping("/deleteSolution")
    private Result deleteSolution(@RequestParam Integer solutionId) {
        Integer count = solutionService.deleteSolution(solutionId);
        if (count!=0){
            return Result.ok().message("删除成功！");
        }
        return Result.error().message("删除删除失败!");
    }


}
