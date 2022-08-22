package xyz.javaee.psychology_questionnaire.service;

import io.swagger.models.auth.In;
import xyz.javaee.psychology_questionnaire.entity.Submit;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;


public interface SubmitService extends IService<Submit>{
    List<Submit> getConsultList();
    void deleteConsultList(Integer id);
    void addSubmit(Submit submit);
    List getAll();
}
