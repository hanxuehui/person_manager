package com.ricky.manager.service;

import com.github.pagehelper.PageInfo;
import com.ricky.manager.entity.LaborSubcontract;
import com.ricky.manager.entity.OverhaulPerson;
import com.ricky.manager.exception.LogicException;
import com.ricky.manager.vo.base.BaseListVO;
import com.ricky.manager.vo.labor.LaborSubcontractQueryCond;
import com.ricky.manager.vo.overhaul.OverhaulPersonQueryCond;
import com.ricky.manager.vo.overhaul.OverhaulPersonVO;

import java.util.List;

/**
 * <p>
 * 检修人员 服务类
 * </p>
 *
 * @author RickyCharles
 * @since 2022-05-13
 */
public interface OverhaulPersonService {
    /**
     * 分页查询数据
     * @param cond 查询条件
     * @return
     */
    PageInfo<OverhaulPerson> queryPage(OverhaulPersonQueryCond cond);

    /**
     * 查询数据
     * @param cond 查询条件
     * @return
     */
    List<OverhaulPerson> queryList(OverhaulPersonQueryCond cond);

    /**
     * 新增数据
     * @param vo 实例对象
     * @throws LogicException
     */
    void insert(OverhaulPersonVO vo) throws LogicException;

    /**
     * 编辑数据
     * @param vo 实例对象
     * @throws LogicException
     */
    void edit(OverhaulPersonVO vo) throws LogicException;

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
    OverhaulPerson queryByCode(String code);

    /**
     * 通过id查询劳务外包信息
     * @param id
     * @return
     */
    OverhaulPerson queryById(Integer id);

    /**
     * Excel数据导入
     * @param overhaulPersonList
     * @return
     */
    List<String> importExcel(List<Object> overhaulPersonList);
}
