package com.ricky.manager.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ricky.manager.auth.interceptor.BaseContextHolder;
import com.ricky.manager.entity.OverhaulPerson;
import com.ricky.manager.exception.LogicException;
import com.ricky.manager.mapper.OverhaulPersonMapper;
import com.ricky.manager.service.OverhaulPersonService;
import com.ricky.manager.utils.DtoVoTransform;
import com.ricky.manager.vo.base.BaseListVO;
import com.ricky.manager.vo.overhaul.OverhaulPersonImportVO;
import com.ricky.manager.vo.overhaul.OverhaulPersonQueryCond;
import com.ricky.manager.vo.overhaul.OverhaulPersonVO;
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
 * <p>
 * 检修人员 服务实现类
 * </p>
 *
 * @author RickyCharles
 * @since 2022-05-13
 */
@Slf4j
@Transactional
@Service
public class OverhaulPersonServiceImpl implements OverhaulPersonService {
    @Autowired
    private OverhaulPersonMapper overhaulPersonMapper;

    @Override
    public PageInfo<OverhaulPerson> queryPage(OverhaulPersonQueryCond cond) {
        cond.setIsDelete(0);
        PageHelper.startPage(cond.getPage(), cond.getPageSize());
        List<OverhaulPerson> overhaulPersonList = overhaulPersonMapper.queryList(cond);
        PageInfo<OverhaulPerson> pageInfo = new PageInfo<>(overhaulPersonList);
        return pageInfo;
    }

    @Override
    public List<OverhaulPerson> queryList(OverhaulPersonQueryCond cond) {
        return overhaulPersonMapper.queryList(cond);
    }

    @Override
    public void insert(OverhaulPersonVO vo) throws LogicException {
        OverhaulPerson record = this.queryByCode(vo.getCode());
        OverhaulPerson overhaulPerson = new OverhaulPerson();
        BeanUtils.copyProperties(vo, overhaulPerson, "id", "crtTime", "crtPerson", "updTime", "updPerson","isDelete");
        if (ObjectUtils.isEmpty(record)){
            overhaulPerson.setCrtTime(new Date());
            overhaulPerson.setCrtPerson(BaseContextHolder.getContext().getName());
            overhaulPerson.setIsDelete(0);
            overhaulPersonMapper.insert(overhaulPerson);
        }else {
            throw new LogicException("此编码检修人员数据已存在!");
        }
    }

    @Override
    public void edit(OverhaulPersonVO vo) throws LogicException {
        Assert.notNull(vo.getId(), "检修人员ID不能为空");
        OverhaulPerson record = this.queryById(vo.getId());
        BeanUtils.copyProperties(vo, record, "id", "crtTime", "crtPerson", "updTime", "updPerson","isDelete");
        if (!ObjectUtils.isEmpty(record)){
            record.setUpdTime(new Date());
            record.setUpdPerson(BaseContextHolder.getContext().getName());
            overhaulPersonMapper.updateByPrimaryKeySelective(record);
        }else {
            throw new LogicException("此编码检修人员数据不存在!");
        }
    }

    @Override
    public void delete(BaseListVO vo) {
        for (Integer id: vo.getIds()) {
            OverhaulPerson record = overhaulPersonMapper.selectByPrimaryKey(id);
            if (!ObjectUtils.isEmpty(record)) {
                record.setIsDelete(1);
                overhaulPersonMapper.updateByPrimaryKeySelective(record);
            }
        }
    }

    @Override
    public OverhaulPerson queryByCode(String code) {
        Example example = new Example(OverhaulPerson.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("code", code);
        criteria.andEqualTo("isDelete", 0);
        List<OverhaulPerson> overhaulPersons = overhaulPersonMapper.selectByExample(example);
        return CollectionUtils.isEmpty(overhaulPersons) ? null: overhaulPersons.get(0);
    }

    @Override
    public OverhaulPerson queryById(Integer id) {
        Example example = new Example(OverhaulPerson.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("id", id);
        criteria.andEqualTo("isDelete", 0);
        List<OverhaulPerson> overhaulPersons = overhaulPersonMapper.selectByExample(example);
        return CollectionUtils.isEmpty(overhaulPersons) ? null: overhaulPersons.get(0);
    }

    @Override
    public List<String> importExcel(List<Object> overhaulPersonList) {
        List<OverhaulPersonImportVO> overhaulPersonExcelList = new ArrayList<>();
        DtoVoTransform.populateList(overhaulPersonList, overhaulPersonExcelList, OverhaulPersonImportVO.class);
        List<String> resultList = new ArrayList<>();
        for (OverhaulPersonImportVO vo : overhaulPersonExcelList) {
            Example example = new Example(OverhaulPerson.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("code", vo.getCode());
            criteria.andEqualTo("isDelete", 0);
            List<OverhaulPerson> records = overhaulPersonMapper.selectByExample(example);
            if (CollectionUtils.isEmpty(records)) {
                OverhaulPerson overhaulPerson = new OverhaulPerson();
                BeanUtils.copyProperties(vo, overhaulPerson, "id");
                overhaulPerson.setCrtTime(new Date());
                overhaulPerson.setCrtPerson(BaseContextHolder.getContext().getName());
                overhaulPerson.setIsDelete(0);
                overhaulPersonMapper.insert(overhaulPerson);
            } else {
                OverhaulPerson record = records.get(0);
                BeanUtils.copyProperties(vo, record, "id");
                overhaulPersonMapper.updateByPrimaryKeySelective(record);
            }
        }
        return resultList;
    }

}
