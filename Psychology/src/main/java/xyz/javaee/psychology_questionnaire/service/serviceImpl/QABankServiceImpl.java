package xyz.javaee.psychology_questionnaire.service.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.javaee.psychology_questionnaire.dao.QABankMapper;
import xyz.javaee.psychology_questionnaire.entity.*;
import xyz.javaee.psychology_questionnaire.service.QABankService;


@Service
@AllArgsConstructor
public class QABankServiceImpl extends ServiceImpl<QABankMapper, QABank> implements QABankService {
    @Autowired
    private QABankMapper qaBankMapper;
    @Override
    public IPage<QABank> getList(Page<QABank> page, String title) {
        System.out.println("进入获取典型咨询问答对服务层");
        QueryWrapper<QABank> qaBankQueryWrapper=new QueryWrapper<>();
        if(null!=title&&title.trim().length()>0)
            qaBankQueryWrapper.like("q",title);
        IPage<QABank> page2 = qaBankMapper.getList(page,qaBankQueryWrapper);
        return page2;
    }

    @Override
    public void editQA(Integer id, String q, String a) {
        QueryWrapper<QABank> qaBankQueryWrapper=new QueryWrapper<>();
        qaBankQueryWrapper.eq("qaid",id);
        QABank qaBank=new QABank();
        qaBank.setQaid(id);
        qaBank.setQ(q);
        qaBank.setA(a);
        qaBankMapper.update(qaBank,qaBankQueryWrapper);
    }
}

