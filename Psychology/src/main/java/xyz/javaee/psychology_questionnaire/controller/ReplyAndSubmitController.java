package xyz.javaee.psychology_questionnaire.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import xyz.javaee.psychology_questionnaire.dao.QABankMapper;
import xyz.javaee.psychology_questionnaire.entity.DTO.ReplyAndSubmit;
import xyz.javaee.psychology_questionnaire.entity.Groups;
import xyz.javaee.psychology_questionnaire.entity.QABank;
import xyz.javaee.psychology_questionnaire.entity.Reply;
import xyz.javaee.psychology_questionnaire.entity.Submit;
import xyz.javaee.psychology_questionnaire.entity.VO.UserVO;
import xyz.javaee.psychology_questionnaire.service.QABankService;
import xyz.javaee.psychology_questionnaire.service.ReplyAndSubmitService;
import xyz.javaee.psychology_questionnaire.service.ReplyService;
import xyz.javaee.psychology_questionnaire.service.SubmitService;
import xyz.javaee.psychology_questionnaire.utils.Result;
import xyz.javaee.psychology_questionnaire.utils.ResultCode;

import java.util.List;

/**
 * 用户提交的问题控制器
 */
@RestController
@Api(tags = "问题与回复")
@RequestMapping("/replyandsubmit")
@AllArgsConstructor
@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
public class ReplyAndSubmitController {
    private final ReplyAndSubmitService replyAndSubmitService;
    private final ReplyService replyService;
    private final SubmitService submitService;
    private final QABankService qaBankService;
    private final QABankMapper qaBankMapper;
    @PostMapping("/getAllList")
    @ApiOperation("获取用户所有发送和获得的答复列表")
    public Result getConsultList(@RequestParam(required = true) Integer id){
        List<ReplyAndSubmit> list=replyAndSubmitService.getUserReplyAndSubmit(id);
        System.out.println(list);
        return Result.ok().data(list);
    }
    @PostMapping("/addReply")
    @ApiOperation("回复入库")
    public Result addReply(@ApiParam("管理员回复消息") @RequestBody Reply reply){
        replyService.addReply(reply);
        System.out.println(reply);
        return Result.ok().data("数据已入库");
    }
    @PostMapping("/addSubmit")
    @ApiOperation("咨询入库")
    public Result addSubmit(@ApiParam("用户咨询消息") @RequestBody Submit submit){
        submitService.addSubmit(submit);
        System.out.println(submit);
        return Result.ok().data("数据已入库");
    }
    @PostMapping("/getQAs")
    @ApiOperation("获取典型咨询问答对")
    public Result getQAs(@RequestParam(required = false, defaultValue = "1") Integer current,
                         @RequestParam(required = false, defaultValue = "6") Integer size,
                         @ApiParam("查询条件") @RequestParam String title){
        System.out.println(current);
        System.out.println("获取典型咨询问答对");
        Page<QABank> page=new Page<>(current,size);
        IPage<QABank> qaBankIPage = qaBankService.getList(page,title);
        return Result.ok().data("qa", qaBankIPage.getRecords()).data("total", qaBankIPage.getTotal());
    }
    @PostMapping("/editQA")
    @ApiOperation("编辑典型咨询问答对")
    public Result editQA(@RequestParam Integer id,@RequestParam String q,@RequestParam String a){
        System.out.println("编辑典型咨询问答对");
        qaBankService.editQA(id,q,a);
        return Result.ok().data("编辑成功");
    }
    @PostMapping("/deleteQA")
    @ApiOperation("删除典型咨询问答对")
    public Result deleteQA(@RequestParam Integer id){
        System.out.println("删除典型咨询问答对");
        qaBankMapper.deleteById(id);
        return Result.ok().data("删除成功");
    }
    @PostMapping("/addQA")
    @ApiOperation("增加典型咨询问答对")
    public Result addQA(@RequestParam String q,@RequestParam String a){
        System.out.println("增加典型咨询问答对");
        QABank qaBank=new QABank();
        qaBank.setQ(q);
        qaBank.setA(a);
        qaBankMapper.insert(qaBank);
        return Result.ok().data("插入成功");
    }
}
