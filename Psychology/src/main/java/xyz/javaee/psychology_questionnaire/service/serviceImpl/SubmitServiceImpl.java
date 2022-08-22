package xyz.javaee.psychology_questionnaire.service.serviceImpl;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import xyz.javaee.psychology_questionnaire.entity.Submit;
import xyz.javaee.psychology_questionnaire.dao.SubmitMapper;
import xyz.javaee.psychology_questionnaire.service.SubmitService;

import java.util.List;

@Service
@AllArgsConstructor
public class SubmitServiceImpl extends ServiceImpl<SubmitMapper, Submit> implements SubmitService{

    private SubmitMapper submitMapper;

    @Override
    public List<Submit> getConsultList() {return submitMapper.getConsultList();}
    @Override
    public void deleteConsultList(Integer id) {
        submitMapper.delete(id);
    }

    @Override
    public void addSubmit(Submit submit) {
        this.baseMapper.insert(submit);
    }

    @Override
    public List getAll() {
        return submitMapper.getAll();
    }
}
