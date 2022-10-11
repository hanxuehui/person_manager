package com.ricky.manager.service;


import com.github.pagehelper.PageInfo;
import com.ricky.manager.entity.OverhaulPerson;
import com.ricky.manager.entity.PersonDispatch;
import com.ricky.manager.exception.LogicException;
import com.ricky.manager.vo.base.BaseListVO;
import com.ricky.manager.vo.dispatch.PersonDispatchQueryCond;
import com.ricky.manager.vo.dispatch.PersonDispatchVO;
import com.ricky.manager.vo.overhaul.OverhaulPersonQueryCond;

import java.util.List;

/**
 * <p>
 * 人员调遣 服务类
 * </p>
 *
 * @author RickyCharles
 * @since 2022-05-13
 */
public interface PersonDispatchService {
    /**
     * 分页查询数据
     * @param cond 查询条件
     * @return
     */
    PageInfo<PersonDispatch> queryPage(PersonDispatchQueryCond cond);

    /**
     * 查询数据
     * @param cond 查询条件
     * @return
     */
    List<PersonDispatch> queryList(PersonDispatchQueryCond cond);

    /**
     * 新增数据
     * @param vo 实例对象
     * @throws LogicException
     */
    void insert(PersonDispatchVO vo) throws LogicException;

    /**
     * 编辑数据
     * @param vo 实例对象
     * @throws LogicException
     */
    void edit(PersonDispatchVO vo) throws LogicException;

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
    PersonDispatch queryByCode(String code);

    /**
     * 通过id查询劳务外包信息
     * @param id
     * @return
     */
    PersonDispatch queryById(Integer id);

    /**
     * Excel数据导入
     * @param personDispatchList
     * @return
     */
    List<String> importExcel(List<Object> personDispatchList);
}
