package xyz.javaee.psychology_questionnaire.service;

import com.baomidou.mybatisplus.extension.service.IService;
import xyz.javaee.psychology_questionnaire.entity.DTO.ReplyAndSubmit;

import java.util.List;

public interface ReplyAndSubmitService extends IService<ReplyAndSubmit> {

    List<ReplyAndSubmit> getUserReplyAndSubmit(Integer id);
}