package com.ricky.manager.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ricky.manager.auth.interceptor.BaseContextHolder;
import com.ricky.manager.config.fdfs.FdfsConfig;
import com.ricky.manager.entity.InnerPerson;
import com.ricky.manager.exception.LogicException;
import com.ricky.manager.mapper.InnerPersonMapper;
import com.ricky.manager.service.InnerPersonService;
import com.ricky.manager.utils.DtoVoTransform;
import com.ricky.manager.vo.base.BaseListVO;
import com.ricky.manager.vo.inner.InnerPersonImportVO;
import com.ricky.manager.vo.inner.InnerPersonQueryCond;
import com.ricky.manager.vo.inner.InnerPersonVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import tk.mybatis.mapper.entity.Example;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 内部人员(InnerPerson)表服务实现类
 *
 * @author makejava
 * @since 2022-05-09 10:26:51
 */
@Slf4j
@Transactional
@Service
public class InnerPersonServiceImpl implements InnerPersonService {

    @Autowired
    private InnerPersonMapper innerPersonMapper;

    @Autowired
    private FdfsConfig fdfsConfig;

    @Value("${fdfs.file_address}")
    private String FILE_ADDRESS;

    @Override
    public PageInfo<InnerPerson> queryPage(InnerPersonQueryCond cond) {
        PageHelper.startPage(cond.getPage(), cond.getPageSize());
        List<InnerPerson> InnerPersonList = innerPersonMapper.queryList(cond);
        PageInfo<InnerPerson> pageInfo = new PageInfo<>(InnerPersonList);
        return pageInfo;
    }

    @Override
    public List<InnerPerson> queryList(InnerPersonQueryCond cond) {
        return innerPersonMapper.queryList(cond);
    }

    @Override
    public void insert(InnerPersonVO vo) throws LogicException, IOException {
        handleResumeFile(vo);
        InnerPerson record = this.queryByCode(vo.getCode());
        InnerPerson InnerPerson = new InnerPerson();
        BeanUtils.copyProperties(vo, InnerPerson, "id", "crtTime", "crtPerson", "updTime", "updPerson", "isDelete");
        if (ObjectUtils.isEmpty(record)) {
            InnerPerson.setCrtTime(new Date());
            InnerPerson.setCrtPerson(BaseContextHolder.getContext().getName());
            InnerPerson.setIsDelete(0);
            innerPersonMapper.insert(InnerPerson);
        } else {
            throw new LogicException("此编码内部人员数据已存在!");
        }
    }

    @Override
    public void edit(InnerPersonVO vo) throws LogicException, IOException {
        handleResumeFile(vo);
        Assert.notNull(vo.getId(), "内部人员ID不能为空");
        InnerPerson record = this.queryById(vo.getId());
        BeanUtils.copyProperties(vo, record, "id", "crtTime", "crtPerson", "updTime", "updPerson", "isDelete");
        if (!ObjectUtils.isEmpty(record)) {
            record.setUpdTime(new Date());
            record.setUpdPerson(BaseContextHolder.getContext().getName());
            innerPersonMapper.updateByPrimaryKeySelective(record);
        } else {
            throw new LogicException("此编码内部人员数据不存在!");
        }
    }

    private void handleResumeFile(InnerPersonVO vo) throws IOException {
        if (!ObjectUtils.isEmpty(vo.getResumeFile())){
            String[] upload = fdfsConfig.upload(vo.getResumeFile());
            if (!ObjectUtils.isEmpty(upload) && upload.length == 2){
                String resumeUrl = FILE_ADDRESS + upload[0] + "/" + upload[upload.length - 1];
                vo.setResume(resumeUrl);
            }
        }
    }

    @Override
    public void delete(BaseListVO vo) {
        for (Integer id : vo.getIds()) {
            InnerPerson record = innerPersonMapper.selectByPrimaryKey(id);
            if (!ObjectUtils.isEmpty(record)) {
                record.setIsDelete(1);
                innerPersonMapper.updateByPrimaryKeySelective(record);
            }
        }
    }

    @Override
    public InnerPerson queryByCode(String code) {
        Example example = new Example(InnerPerson.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("code", code);
        criteria.andEqualTo("isDelete", 0);
        List<InnerPerson> InnerPersons = innerPersonMapper.selectByExample(example);
        return CollectionUtils.isEmpty(InnerPersons) ? null : InnerPersons.get(0);
    }

    @Override
    public InnerPerson queryById(Integer id) {
        Example example = new Example(InnerPerson.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("id", id);
        criteria.andEqualTo("isDelete", 0);
        List<InnerPerson> InnerPersons = innerPersonMapper.selectByExample(example);
        return CollectionUtils.isEmpty(InnerPersons) ? null : InnerPersons.get(0);
    }

    @Override
    public List<String> importExcel(List<Object> innerPersonList) {
        List<InnerPersonImportVO> innerPersonExcelList = new ArrayList<>();
        DtoVoTransform.populateList(innerPersonList, innerPersonExcelList, InnerPersonImportVO.class);
        List<String> resultList = new ArrayList<>();
        for (InnerPersonImportVO vo : innerPersonExcelList) {
            Example example = new Example(InnerPerson.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("code", vo.getCode());
            criteria.andEqualTo("isDelete", 0);
            List<InnerPerson> records = innerPersonMapper.selectByExample(example);
            if (CollectionUtils.isEmpty(records)) {
                InnerPerson innerPerson = new InnerPerson();
                BeanUtils.copyProperties(vo, innerPerson, "id");
                innerPerson.setCrtTime(new Date());
                innerPerson.setCrtPerson(BaseContextHolder.getContext().getName());
                innerPerson.setIsDelete(0);
                innerPersonMapper.insert(innerPerson);
            } else {
                InnerPerson record = records.get(0);
                BeanUtils.copyProperties(vo, record, "id");
                innerPersonMapper.updateByPrimaryKeySelective(record);
            }
        }
        return resultList;
    }
}
