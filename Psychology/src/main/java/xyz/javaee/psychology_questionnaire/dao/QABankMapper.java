package xyz.javaee.psychology_questionnaire.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import xyz.javaee.psychology_questionnaire.entity.QABank;


public interface QABankMapper extends BaseMapper<QABank> {

    IPage<QABank> getList(Page<QABank> page,@Param(Constants.WRAPPER)  QueryWrapper<QABank> qaBankQueryWrapper);
}
