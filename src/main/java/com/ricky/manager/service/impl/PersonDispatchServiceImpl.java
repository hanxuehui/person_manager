package com.ricky.manager.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ricky.manager.auth.interceptor.BaseContextHolder;
import com.ricky.manager.entity.PersonDispatch;
import com.ricky.manager.exception.LogicException;
import com.ricky.manager.mapper.PersonDispatchMapper;
import com.ricky.manager.service.PersonDispatchService;
import com.ricky.manager.utils.DtoVoTransform;
import com.ricky.manager.vo.base.BaseListVO;
import com.ricky.manager.vo.dispatch.PersonDispatchImportVO;
import com.ricky.manager.vo.dispatch.PersonDispatchQueryCond;
import com.ricky.manager.vo.dispatch.PersonDispatchVO;
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
 * 人员调遣 服务实现类
 * </p>
 *
 * @author RickyCharles
 * @since 2022-05-13
 */
@Slf4j
@Transactional
@Service
public class PersonDispatchServiceImpl implements PersonDispatchService {
    @Autowired
    private PersonDispatchMapper personDispatchMapper;

    @Override
    public PageInfo<PersonDispatch> queryPage(PersonDispatchQueryCond cond) {
        cond.setIsDelete(0);
        PageHelper.startPage(cond.getPage(), cond.getPageSize());
        List<PersonDispatch> personDispatchList = personDispatchMapper.queryList(cond);
        PageInfo<PersonDispatch> pageInfo = new PageInfo<>(personDispatchList);
        return pageInfo;
    }

    @Override
    public List<PersonDispatch> queryList(PersonDispatchQueryCond cond) {
        return personDispatchMapper.queryList(cond);
    }

    @Override
    public void insert(PersonDispatchVO vo) throws LogicException {
        PersonDispatch record = this.queryByCode(vo.getCode());
        PersonDispatch personDispatch = new PersonDispatch();
        BeanUtils.copyProperties(vo, personDispatch, "id", "crtTime", "crtPerson", "updTime", "updPerson","isDelete");
        if (ObjectUtils.isEmpty(record)){
            personDispatch.setCrtTime(new Date());
            personDispatch.setCrtPerson(BaseContextHolder.getContext().getName());
            personDispatch.setIsDelete(0);
            personDispatchMapper.insert(personDispatch);
        }else {
            throw new LogicException("此编码人员调遣数据已存在!");
        }
    }

    @Override
    public void edit(PersonDispatchVO vo) throws LogicException {
        Assert.notNull(vo.getId(), "人员调遣ID不能为空");
        PersonDispatch record = this.queryById(vo.getId());
        BeanUtils.copyProperties(vo, record, "id", "crtTime", "crtPerson", "updTime", "updPerson","isDelete");
        if (!ObjectUtils.isEmpty(record)){
            record.setUpdTime(new Date());
            record.setUpdPerson(BaseContextHolder.getContext().getName());
            personDispatchMapper.updateByPrimaryKeySelective(record);
        }else {
            throw new LogicException("此编码人员调遣数据不存在!");
        }
    }

    @Override
    public void delete(BaseListVO vo) {
        for (Integer id: vo.getIds()) {
            PersonDispatch record = personDispatchMapper.selectByPrimaryKey(id);
            if (!ObjectUtils.isEmpty(record)) {
                record.setIsDelete(1);
                personDispatchMapper.updateByPrimaryKeySelective(record);
            }
        }
    }

    @Override
    public PersonDispatch queryByCode(String code) {
        Example example = new Example(PersonDispatch.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("code", code);
        criteria.andEqualTo("isDelete", 0);
        List<PersonDispatch> personDispatchList = personDispatchMapper.selectByExample(example);
        return CollectionUtils.isEmpty(personDispatchList) ? null: personDispatchList.get(0);
    }

    @Override
    public PersonDispatch queryById(Integer id) {
        Example example = new Example(PersonDispatch.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("id", id);
        criteria.andEqualTo("isDelete", 0);
        List<PersonDispatch> personDispatchList = personDispatchMapper.selectByExample(example);
        return CollectionUtils.isEmpty(personDispatchList) ? null: personDispatchList.get(0);
    }

    @Override
    public List<String> importExcel(List<Object> personDispatchList) {
        List<PersonDispatchImportVO> personDispatchExcelList = new ArrayList<>();
        DtoVoTransform.populateList(personDispatchList, personDispatchExcelList, PersonDispatchImportVO.class);
        List<String> resultList = new ArrayList<>();
        for (PersonDispatchImportVO vo : personDispatchExcelList) {
            Example example = new Example(PersonDispatch.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("code", vo.getCode());
            criteria.andEqualTo("isDelete", 0);
            List<PersonDispatch> records = personDispatchMapper.selectByExample(example);
            if (CollectionUtils.isEmpty(records)) {
                PersonDispatch personDispatch = new PersonDispatch();
                BeanUtils.copyProperties(vo, personDispatch, "id");
                personDispatch.setCrtTime(new Date());
                personDispatch.setCrtPerson(BaseContextHolder.getContext().getName());
                personDispatch.setIsDelete(0);
                personDispatchMapper.insert(personDispatch);
            } else {
                PersonDispatch record = records.get(0);
                BeanUtils.copyProperties(vo, record, "id");
                personDispatchMapper.updateByPrimaryKeySelective(record);
            }
        }
        return resultList;
    }
}
