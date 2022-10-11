package com.ricky.manager.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ricky.manager.auth.interceptor.BaseContextHolder;
import com.ricky.manager.entity.LaborSubcontract;
import com.ricky.manager.exception.LogicException;
import com.ricky.manager.mapper.LaborSubcontractMapper;
import com.ricky.manager.service.LaborSubcontractService;
import com.ricky.manager.utils.DtoVoTransform;
import com.ricky.manager.vo.base.BaseListVO;
import com.ricky.manager.vo.labor.LaborSubcontractImportVO;
import com.ricky.manager.vo.labor.LaborSubcontractQueryCond;
import com.ricky.manager.vo.labor.LaborSubcontractVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * 劳务分包(LaborSubcontract)表服务实现类
 *
 * @author makejava
 * @since 2022-05-12 14:54:31
 */
@Slf4j
@Transactional
@Service
public class LaborSubcontractServiceImpl implements LaborSubcontractService {

    @Autowired
    private LaborSubcontractMapper laborSubcontractMapper;

    @Override
    public PageInfo<LaborSubcontract> queryPage(LaborSubcontractQueryCond cond) {
        cond.setIsDelete(0);
        PageHelper.startPage(cond.getPage(), cond.getPageSize());
        List<LaborSubcontract> laborSubcontractList = laborSubcontractMapper.queryList(cond);
        PageInfo<LaborSubcontract> pageInfo = new PageInfo<>(laborSubcontractList);
        return pageInfo;
    }

    @Override
    public List<LaborSubcontract> queryList(LaborSubcontractQueryCond cond) {
        return laborSubcontractMapper.queryList(cond);
    }

    @Override
    public void insert(LaborSubcontractVO vo) throws LogicException {
        LaborSubcontract record = this.queryByCode(vo.getCode());
        LaborSubcontract laborSubcontract = new LaborSubcontract();
        BeanUtils.copyProperties(vo, laborSubcontract, "id", "crtTime", "crtPerson", "updTime", "updPerson","isDelete");
        if (ObjectUtils.isEmpty(record)){
            laborSubcontract.setCrtTime(new Date());
            laborSubcontract.setCrtPerson(BaseContextHolder.getContext().getName());
            laborSubcontract.setIsDelete(0);
            laborSubcontractMapper.insert(laborSubcontract);
        }else {
            throw new LogicException("此编码劳务分包数据已存在!");
        }
    }

    @Override
    public void edit(LaborSubcontractVO vo) throws LogicException {
        Assert.notNull(vo.getId(), "劳务分包ID不能为空");
        LaborSubcontract record = this.queryById(vo.getId());
        BeanUtils.copyProperties(vo, record, "id", "crtTime", "crtPerson", "updTime", "updPerson","isDelete");
        if (!ObjectUtils.isEmpty(record)){
            record.setUpdTime(new Date());
            record.setUpdPerson(BaseContextHolder.getContext().getName());
            laborSubcontractMapper.updateByPrimaryKeySelective(record);
        }else {
            throw new LogicException("此编码劳务分包数据不存在!");
        }
    }

    @Override
    public void delete(BaseListVO vo) {
        for (Integer id: vo.getIds()) {
            LaborSubcontract record = laborSubcontractMapper.selectByPrimaryKey(id);
            if (!ObjectUtils.isEmpty(record)) {
                record.setIsDelete(1);
                laborSubcontractMapper.updateByPrimaryKeySelective(record);
            }
        }
    }

    @Override
    public LaborSubcontract queryByCode(String code) {
        Example example = new Example(LaborSubcontract.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("code", code);
        criteria.andEqualTo("isDelete", 0);
        List<LaborSubcontract> laborSubcontracts = laborSubcontractMapper.selectByExample(example);
        return CollectionUtils.isEmpty(laborSubcontracts) ? null: laborSubcontracts.get(0);
    }

    @Override
    public LaborSubcontract queryById(Integer id) {
        Example example = new Example(LaborSubcontract.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("id", id);
        criteria.andEqualTo("isDelete", 0);
        List<LaborSubcontract> laborSubcontracts = laborSubcontractMapper.selectByExample(example);
        return CollectionUtils.isEmpty(laborSubcontracts) ? null: laborSubcontracts.get(0);
    }

    @Override
    public List<String> importExcel(List<Object> laborSubcontractList) {
        List<LaborSubcontractImportVO> laborSubcontractExcelList = new ArrayList<>();
        DtoVoTransform.populateList(laborSubcontractList, laborSubcontractExcelList, LaborSubcontractImportVO.class);
        List<String> resultList = new ArrayList<>();
        for (LaborSubcontractImportVO vo : laborSubcontractExcelList) {
            Example example = new Example(LaborSubcontract.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("code", vo.getCode());
            criteria.andEqualTo("isDelete", 0);
            List<LaborSubcontract> records = laborSubcontractMapper.selectByExample(example);
            if (CollectionUtils.isEmpty(records)) {
                LaborSubcontract laborSubcontract = new LaborSubcontract();
                BeanUtils.copyProperties(vo, laborSubcontract, "id");
                laborSubcontract.setCrtTime(new Date());
                laborSubcontract.setCrtPerson(BaseContextHolder.getContext().getName());
                laborSubcontract.setIsDelete(0);
                laborSubcontractMapper.insert(laborSubcontract);
            } else {
                LaborSubcontract record = records.get(0);
                BeanUtils.copyProperties(vo, record, "id");
                laborSubcontractMapper.updateByPrimaryKeySelective(record);
            }
        }
        return resultList;
    }
}
