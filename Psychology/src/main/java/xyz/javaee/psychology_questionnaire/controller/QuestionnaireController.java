package xyz.javaee.psychology_questionnaire.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import xyz.javaee.psychology_questionnaire.entity.QuestionBank;
import xyz.javaee.psychology_questionnaire.entity.Questionnaire;
import xyz.javaee.psychology_questionnaire.entity.Questions;
import xyz.javaee.psychology_questionnaire.entity.QuestionsBank;
import xyz.javaee.psychology_questionnaire.service.*;
import xyz.javaee.psychology_questionnaire.utils.Result;

import java.util.List;

/**
 * 问卷控制器
 */
@RestController
@Api(tags = "问卷")
@RequestMapping("/questionnaire")
@AllArgsConstructor
@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
public class QuestionnaireController {
    private final QuestionnaireService questionnaireService;
    private final QuestionnairesmapService questionnairesmapService;
    private final QuestionsService questionsService;
    @PostMapping("/getList")
    @ApiOperation("获取所有的问卷")
    public Result getAllQuestionnaires(@RequestParam(required = false, defaultValue = "1") Integer current,
                                             @RequestParam(required = false, defaultValue = "6") Integer size) {
        System.out.println("进入获取问卷控制器"+current.toString());
        Page<Questionnaire> page=new Page<>(current,size);
        IPage<Questionnaire> questionnaireIPage = questionnaireService.getList(page);
        return Result.ok().data("questionnaires", questionnaireIPage.getRecords()).data("total", questionnaireIPage.getTotal());
    }
    @PostMapping("/getListCount")
    @ApiOperation("获取所有的问卷")
    public Result getAllQuestionnairesCount() {
        QueryWrapper<Questionnaire> questionnaireQueryWrapper=new QueryWrapper<>();
        long count=questionnaireService.getBaseMapper().selectCount(questionnaireQueryWrapper);
        return Result.ok().data("total", count);
    }
    @ApiOperation(value = "删除题目", notes = "删除题目信息")
    @PostMapping("/deleteQuestionnarie")
    public Result deleteQuestion(@RequestParam Integer questionnaireId) {
        try {
            System.out.println("进入删除问卷控制层");
            System.out.println(questionnaireId);
            questionnaireService.deleteQuestionnaire(questionnaireId);
            return Result.ok().message("删除成功！");
        }catch (NullPointerException e){
            System.out.println(e);
            return Result.error().message("删除失败!");
        }
    }
//    @ApiOperation(value = "创建问题", notes = "创建问题")
//    @PostMapping("/addQuestion")
//    public Result addQuestion(@RequestBody QuestionsBank questionsBank) {
//        System.out.println("进入创建问题控制层，接受前端的参数为");
//        System.out.println(questionsBank);
//        Integer count = questionsBankService.addQuestion(questionsBank);
//        if (count == 0) {
//            return Result.ok().data("data", questionsBank).message("添加成功！");
//        }
//        return Result.error().message("该方案名称已存在！");
//    }
//    @ApiOperation(value = "修改问题",notes = "修改问题内容")
//    @PostMapping("/editQuestion")
//    public Result editQuestion(@RequestBody QuestionsBank questionsBank) {
//        Integer count = questionsBankService.editQuestion(questionsBank);
//        if (count != 0) {
//            return Result.ok().data("data", questionsBank).message("修改成功！");
//        }
//        return Result.error();
//    }
    @ApiOperation(value = "模糊查询问卷",notes = "模糊查询问卷")
    @PostMapping("/findSomeQuestionnaires")
    public Result findSomeQuestionnaires(@RequestParam(required = false, defaultValue = "1") Integer current,
                                @RequestParam(required = false, defaultValue = "6") Integer size,
                                @RequestParam String questionTitleContent) {
        Page<Questionnaire> questionnairePage=new Page<>(current,size);
        IPage<Questionnaire> questionnaireIPage=questionnaireService.findSomeQuestionnaires(questionnairePage,questionTitleContent);
        return Result.ok().data("questionnaires", questionnaireIPage.getRecords()).data("total",questionnaireIPage.getTotal());
    }
    @ApiOperation(value = "随机抽一题",notes = "在题库组id（以题目类型分类的组）中随机找一道题目")
    @PostMapping("/getARandomQuestion")
    public Result getARandomQuestion(@RequestParam Integer id) {
        QuestionsBank questionsBank=questionnaireService.getARandomQuestion(id);
        System.out.println("随机的一道题为:");
        System.out.println(questionsBank);
        return Result.ok().data("question", questionsBank);
    }
    @ApiOperation(value = "新增问卷",notes = "新增问卷")
    @PostMapping("/addAQuestionnaire")
    public Result addAQuestionnaire(@RequestBody Questionnaire questionnaire ) {

        return Result.ok().data("questionnaireId", questionnaireService.addAQuestionnaire(questionnaire));
    }
    @ApiOperation(value = "获取所属用户的问卷",notes = "获取所属用户的问卷")
    @PostMapping("/findMyQuestionnaires")
    public Result findMyQuestionnaires(@RequestParam Integer userId,@RequestParam Integer admin) {
//        System.out.println();
        return Result.ok().data("questionnaireId", questionnairesmapService.findMyQuestionnaires(userId,admin));
    }
    @ApiOperation(value = "获取问卷对应的题目",notes = "获取问卷对应的题目")
    @PostMapping("/findQuestions")
    public Result findQuestions(@RequestParam Integer questionnaireId) {
        QueryWrapper<Questions>questionsQueryWrapper=new QueryWrapper<>();
        questionsQueryWrapper.eq("questionnaire_id",questionnaireId);
        return Result.ok().data("questionnaireId", questionsService.getBaseMapper().selectList(questionsQueryWrapper));
    }
}
