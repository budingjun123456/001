package xyz.javaee.psychology_questionnaire.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import xyz.javaee.psychology_questionnaire.entity.Submit;
import xyz.javaee.psychology_questionnaire.service.SubmitService;
import xyz.javaee.psychology_questionnaire.utils.Result;
import xyz.javaee.psychology_questionnaire.utils.ResultCode;

import java.util.List;

/**
 * 用户提交的问题控制器
 */
@RestController
@RequestMapping("/submit")
@AllArgsConstructor
@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
public class SubmitController {
    private final SubmitService submitService;

    /**
     * 获取用户提交到系统问题列表
     * @return
     */
    @GetMapping("/getConsultList")
    public Result getConsultList(){
        List<Submit> list=submitService.getConsultList();
        System.out.println(list);
        return Result.ok().data(list);
    }

    /**
     * 根据提问问题id删除对应提问
     * @param id
     * @return
     */
    @PostMapping("/deleteConsult")
    public Result deleteConsult(@RequestParam(required = true) Integer id){
        submitService.deleteConsultList(id);
        if(id<1){
            return Result.RCode(false, ResultCode.PARAM_NOT_VALID);
        }else {
            return Result.ok();
        }
    }
}
