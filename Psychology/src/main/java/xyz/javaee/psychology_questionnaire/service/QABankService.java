package xyz.javaee.psychology_questionnaire.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import xyz.javaee.psychology_questionnaire.entity.QABank;


public interface QABankService extends IService<QABank> {
    IPage<QABank> getList(Page<QABank> page, String title);
    void editQA(Integer id,String q,String a);
}

