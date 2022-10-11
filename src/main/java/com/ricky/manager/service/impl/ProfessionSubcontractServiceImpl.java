package com.ricky.manager.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ricky.manager.auth.interceptor.BaseContextHolder;
import com.ricky.manager.config.fdfs.FdfsConfig;
import com.ricky.manager.entity.ProfessionSubcontract;
import com.ricky.manager.exception.LogicException;
import com.ricky.manager.mapper.ProfessionSubcontractMapper;
import com.ricky.manager.service.ProfessionSubcontractService;
import com.ricky.manager.utils.DtoVoTransform;
import com.ricky.manager.vo.base.BaseListVO;
import com.ricky.manager.vo.profession.ProfessionSubcontractImportVO;
import com.ricky.manager.vo.profession.ProfessionSubcontractQueryCond;
import com.ricky.manager.vo.profession.ProfessionSubcontractVO;
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
 * <p>
 * 专业分包 服务实现类
 * </p>
 *
 * @author RickyCharles
 * @since 2022-05-13
 */
@Slf4j
@Transactional
@Service
public class ProfessionSubcontractServiceImpl implements ProfessionSubcontractService {

    @Autowired
    private ProfessionSubcontractMapper professionSubcontractMapper;

    @Autowired
    private FdfsConfig fdfsConfig;

    @Value("${fdfs.file_address}")
    private String FILE_ADDRESS;

    @Override
    public PageInfo<ProfessionSubcontract> queryPage(ProfessionSubcontractQueryCond cond) {
        cond.setIsDelete(0);
        PageHelper.startPage(cond.getPage(), cond.getPageSize());
        List<ProfessionSubcontract> professionSubcontractList = professionSubcontractMapper.queryList(cond);
        PageInfo<ProfessionSubcontract> pageInfo = new PageInfo<>(professionSubcontractList);
        return pageInfo;
    }

    @Override
    public List<ProfessionSubcontract> queryList(ProfessionSubcontractQueryCond cond) {
        return professionSubcontractMapper.queryList(cond);
    }

    @Override
    public void insert(ProfessionSubcontractVO vo) throws LogicException, IOException {
        handleCertificateFile(vo);
        ProfessionSubcontract record = this.queryByCode(vo.getCode());
        ProfessionSubcontract professionSubcontract = new ProfessionSubcontract();
        BeanUtils.copyProperties(vo, professionSubcontract, "id", "crtTime", "crtPerson", "updTime", "updPerson","isDelete");
        if (ObjectUtils.isEmpty(record)){
            professionSubcontract.setCrtTime(new Date());
            professionSubcontract.setCrtPerson(BaseContextHolder.getContext().getName());
            professionSubcontract.setIsDelete(0);
            professionSubcontractMapper.insert(professionSubcontract);
        }else {
            throw new LogicException("此编码专业分包数据已存在!");
        }
    }

    @Override
    public void edit(ProfessionSubcontractVO vo) throws LogicException, IOException {
        handleCertificateFile(vo);
        Assert.notNull(vo.getId(), "专业分包ID不能为空");
        ProfessionSubcontract record = this.queryById(vo.getId());
        BeanUtils.copyProperties(vo, record, "id", "crtTime", "crtPerson", "updTime", "updPerson","isDelete");
        if (!ObjectUtils.isEmpty(record)){
            record.setUpdTime(new Date());
            record.setUpdPerson(BaseContextHolder.getContext().getName());
            professionSubcontractMapper.updateByPrimaryKeySelective(record);
        }else {
            throw new LogicException("此编码专业分包数据不存在!");
        }
    }

    private void handleCertificateFile(ProfessionSubcontractVO vo) throws IOException {
        if (!ObjectUtils.isEmpty(vo.getCertificateFile())){
            String[] upload = fdfsConfig.upload(vo.getCertificateFile());
            if (!ObjectUtils.isEmpty(upload) && upload.length == 2){
                String resumeUrl = FILE_ADDRESS + upload[0] + "/" + upload[upload.length - 1];
                vo.setCertificate(resumeUrl);
            }
        }
    }

    @Override
    public void delete(BaseListVO vo) {
        for (Integer id: vo.getIds()) {
            ProfessionSubcontract record = professionSubcontractMapper.selectByPrimaryKey(id);
            if (!ObjectUtils.isEmpty(record)) {
                record.setIsDelete(1);
                professionSubcontractMapper.updateByPrimaryKeySelective(record);
            }
        }
    }

    @Override
    public ProfessionSubcontract queryByCode(String code) {
        Example example = new Example(ProfessionSubcontract.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("code", code);
        criteria.andEqualTo("isDelete", 0);
        List<ProfessionSubcontract> professionSubcontracts = professionSubcontractMapper.selectByExample(example);
        return CollectionUtils.isEmpty(professionSubcontracts) ? null: professionSubcontracts.get(0);
    }

    @Override
    public ProfessionSubcontract queryById(Integer id) {
        Example example = new Example(ProfessionSubcontract.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("id", id);
        criteria.andEqualTo("isDelete", 0);
        List<ProfessionSubcontract> professionSubcontracts = professionSubcontractMapper.selectByExample(example);
        return CollectionUtils.isEmpty(professionSubcontracts) ? null: professionSubcontracts.get(0);
    }

    @Override
    public List<String> importExcel(List<Object> professionSubcontractList) {
        List<ProfessionSubcontractImportVO> professionSubcontractExcelList = new ArrayList<>();
        DtoVoTransform.populateList(professionSubcontractList, professionSubcontractExcelList, ProfessionSubcontractImportVO.class);
        List<String> resultList = new ArrayList<>();
        for (ProfessionSubcontractImportVO vo : professionSubcontractExcelList) {
            Example example = new Example(ProfessionSubcontract.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("code", vo.getCode());
            criteria.andEqualTo("isDelete", 0);
            List<ProfessionSubcontract> records = professionSubcontractMapper.selectByExample(example);
            if (CollectionUtils.isEmpty(records)) {
                ProfessionSubcontract professionSubcontract = new ProfessionSubcontract();
                BeanUtils.copyProperties(vo, professionSubcontract, "id");
                professionSubcontract.setCrtTime(new Date());
                professionSubcontract.setCrtPerson(BaseContextHolder.getContext().getName());
                professionSubcontract.setIsDelete(0);
                professionSubcontractMapper.insert(professionSubcontract);
            } else {
                ProfessionSubcontract record = records.get(0);
                BeanUtils.copyProperties(vo, record, "id", "certificate");
                professionSubcontractMapper.updateByPrimaryKeySelective(record);
            }
        }
        return resultList;
    }
}
