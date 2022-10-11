package com.ricky.manager.service;


import com.github.pagehelper.PageInfo;
import com.ricky.manager.entity.PersonDispatch;
import com.ricky.manager.entity.ProfessionSubcontract;
import com.ricky.manager.exception.LogicException;
import com.ricky.manager.vo.base.BaseListVO;
import com.ricky.manager.vo.dispatch.PersonDispatchQueryCond;
import com.ricky.manager.vo.profession.ProfessionSubcontractQueryCond;
import com.ricky.manager.vo.profession.ProfessionSubcontractVO;

import java.io.IOException;
import java.util.List;

/**
 * <p>
 * 专业分包 服务类
 * </p>
 *
 * @author RickyCharles
 * @since 2022-05-13
 */
public interface ProfessionSubcontractService {
    /**
     * 分页查询数据
     * @param cond 查询条件
     * @return
     */
    PageInfo<ProfessionSubcontract> queryPage(ProfessionSubcontractQueryCond cond);

    /**
     * 查询数据
     * @param cond 查询条件
     * @return
     */
    List<ProfessionSubcontract> queryList(ProfessionSubcontractQueryCond cond);

    /**
     * 新增数据
     * @param vo 实例对象
     * @throws LogicException
     */
    void insert(ProfessionSubcontractVO vo) throws LogicException, IOException;

    /**
     * 编辑数据
     * @param vo 实例对象
     * @throws LogicException
     */
    void edit(ProfessionSubcontractVO vo) throws LogicException, IOException;

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
    ProfessionSubcontract queryByCode(String code);

    /**
     * 通过id查询劳务外包信息
     * @param id
     * @return
     */
    ProfessionSubcontract queryById(Integer id);

    /**
     * Excel数据导入
     * @param professionSubcontractList
     * @return
     */
    List<String> importExcel(List<Object> professionSubcontractList);
}
