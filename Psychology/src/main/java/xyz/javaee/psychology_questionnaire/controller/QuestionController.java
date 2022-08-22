package xyz.javaee.psychology_questionnaire.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import xyz.javaee.psychology_questionnaire.entity.QuestionBank;
import xyz.javaee.psychology_questionnaire.entity.Questions;
import xyz.javaee.psychology_questionnaire.entity.QuestionsBank;
import xyz.javaee.psychology_questionnaire.entity.Solution;
import xyz.javaee.psychology_questionnaire.service.QuestionBankService;
import xyz.javaee.psychology_questionnaire.service.QuestionsBankService;
import xyz.javaee.psychology_questionnaire.service.QuestionsService;
import xyz.javaee.psychology_questionnaire.utils.Result;
import xyz.javaee.psychology_questionnaire.utils.ResultCode;

import java.util.List;

/**
 * 问题控制器
 */
@RestController
@Api(tags = "问题")
@RequestMapping("/question")
@AllArgsConstructor
@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
public class QuestionController {
    private final QuestionsService questionsService;
    private final QuestionBankService questionBankService;
    private final QuestionsBankService questionsBankService;
    @PostMapping("/getTypeList")
    @ApiOperation("获取所有已分类的问题类型")
    public Result getList(){
        System.out.println("进入问题控制器");
        List<QuestionBank> list= questionBankService.getALL();
        return Result.ok().data(list);
    }
    @PostMapping("/getList")
    @ApiOperation("获取所有对应分类的问题")
    public Result getTypeQuestions(@RequestParam(required = false, defaultValue = "1") Integer current,
                                             @RequestParam(required = false, defaultValue = "6") Integer size,
                                             @RequestBody QuestionsBank questionsBank) {
        System.out.println("进入获取方案控制器"+current.toString());
        Page<QuestionsBank> page=new Page<>(current,size);
        IPage<QuestionsBank> questionsBankIPage = questionsBankService.getList(page,questionsBank);
        System.out.println("获取到的结果：");
        System.out.println(questionsBankIPage);
        System.out.println(questionsBankIPage.getRecords());
        System.out.println(questionsBankIPage.getTotal());
        return Result.ok().data("questionsBank", questionsBankIPage.getRecords()).data("total", questionsBankIPage.getTotal());
    }
    @PostMapping("/count")
    @ApiOperation("获取所有对应分类的问题")
    public Result count() {
        QueryWrapper<QuestionsBank> questionsBankQueryWrapper=new QueryWrapper<>();
        questionsBankQueryWrapper.eq("section_type",0).or().eq("section_type",1);
        return Result.ok().data("total", questionsBankService.getBaseMapper().selectCount(questionsBankQueryWrapper));
    }
    @ApiOperation(value = "删除题目", notes = "删除题目信息")
    @PostMapping("/deleteQuestion")
    public Result deleteQuestion(@RequestParam Integer questionId) {
        try {
            System.out.println("进入删除题目控制层");
            System.out.println(questionId);
            questionsBankService.deleteQuestion(questionId);
            return Result.ok().message("删除成功！");
        }catch (NullPointerException e){
            System.out.println(e);
            return Result.error().message("删除删除失败!");
        }
    }
    @ApiOperation(value = "创建问题", notes = "创建问题")
    @PostMapping("/addQuestion")
    public Result addQuestion(@RequestBody QuestionsBank questionsBank) {
        System.out.println("进入创建问题控制层，接受前端的参数为");
        System.out.println(questionsBank);
        Integer count = questionsBankService.addQuestion(questionsBank);
        if (count == 0) {
            return Result.ok().data("data", questionsBank).message("添加成功！");
        }
        return Result.error().message("该方案名称已存在！");
    }
    @ApiOperation(value = "修改问题",notes = "修改问题内容")
    @PostMapping("/editQuestion")
    public Result editQuestion(@RequestBody QuestionsBank questionsBank) {
        Integer count = questionsBankService.editQuestion(questionsBank);
        if (count != 0) {
            return Result.ok().data("data", questionsBank).message("修改成功！");
        }
        return Result.error();
    }
    @ApiOperation(value = "模糊查询问题",notes = "模糊查询问题")
    @PostMapping("/findQuestions")
    public Result findQuestions(@RequestParam(required = false, defaultValue = "1") Integer current,
                                @RequestParam(required = false, defaultValue = "6") Integer size,
                                @RequestParam Integer questionTypeId,
                                @RequestParam String questionContent) {
        Page<QuestionsBank> questionsBankPage=new Page<>(current,size);
        IPage<QuestionsBank> questionsBankIPage=questionsBankService.findQuestions(questionsBankPage,questionContent,questionTypeId);
        return Result.ok().data("questions", questionsBankIPage.getRecords()).data("total",questionsBankIPage.getTotal());
    }
    @ApiOperation(value = "新增问卷中问题库",notes = "新增问卷中问题库")
    @PostMapping("/insertAQuestion")
    public Result insertAQuestion(@RequestBody Questions questions) {
        try {
            questionsService.insertAQuestion(questions);
            return Result.RCode(true,ResultCode.SUCCESS);
        }catch (Exception e){
            return Result.RCode(true,ResultCode.DATABASE_ERROR);
        }
    }
}
