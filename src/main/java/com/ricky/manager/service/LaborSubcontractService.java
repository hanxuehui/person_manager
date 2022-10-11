package com.ricky.manager.service;


import com.github.pagehelper.PageInfo;
import com.ricky.manager.entity.InnerPerson;
import com.ricky.manager.entity.LaborSubcontract;
import com.ricky.manager.exception.LogicException;
import com.ricky.manager.vo.base.BaseListVO;
import com.ricky.manager.vo.inner.InnerPersonQueryCond;
import com.ricky.manager.vo.labor.LaborSubcontractQueryCond;
import com.ricky.manager.vo.labor.LaborSubcontractVO;

import java.util.List;

/**
 * 劳务分包(LaborSubcontract)表服务接口
 *
 * @author makejava
 * @since 2022-05-12 14:54:31
 */
public interface LaborSubcontractService {
    /**
     * 分页查询数据
     * @param cond 查询条件
     * @return
     */
    PageInfo<LaborSubcontract> queryPage(LaborSubcontractQueryCond cond);

    /**
     * 查询数据
     * @param cond 查询条件
     * @return
     */
    List<LaborSubcontract> queryList(LaborSubcontractQueryCond cond);

    /**
     * 新增数据
     * @param vo 实例对象
     * @throws LogicException
     */
    void insert(LaborSubcontractVO vo) throws LogicException;

    /**
     * 编辑数据
     * @param vo 实例对象
     * @throws LogicException
     */
    void edit(LaborSubcontractVO vo) throws LogicException;

    /**
     * 删除数据
     * @param vo 实例对象
     * @throws LogicException
     */
    void delete(BaseListVO vo);

    /**
     * 通过code查询劳务外包信息
     * @param code
     * @return
     */
    LaborSubcontract queryByCode(String code);

    /**
     * 通过code查询劳务外包信息
     * @param id
     * @return
     */
    LaborSubcontract queryById(Integer id);

    /**
     * Excel数据导入
     * @param laborSubcontractList
     * @return
     */
    List<String> importExcel(List<Object> laborSubcontractList);

}
