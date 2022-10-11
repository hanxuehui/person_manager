package com.ricky.manager.service;

import com.github.pagehelper.PageInfo;
import com.ricky.manager.entity.InnerPerson;
import com.ricky.manager.exception.LogicException;
import com.ricky.manager.vo.base.BaseListVO;
import com.ricky.manager.vo.inner.InnerPersonQueryCond;
import com.ricky.manager.vo.inner.InnerPersonVO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * 内部人员(InnerPerson)表服务接口
 *
 * @author makejava
 * @since 2022-05-09 10:26:51
 */
public interface InnerPersonService {
    /**
     * 分页查询数据
     * @param cond 查询条件
     * @return
     */
    PageInfo<InnerPerson> queryPage(InnerPersonQueryCond cond);

    /**
     * 查询数据
     * @param cond 查询条件
     * @return
     */
    List<InnerPerson> queryList(InnerPersonQueryCond cond);

    /**
     * 新增数据
     * @param vo 实例对象
     * @throws LogicException
     */
    void insert(InnerPersonVO vo) throws LogicException, IOException;

    /**
     * 编辑数据
     * @param vo 实例对象
     * @throws LogicException
     */
    void edit(InnerPersonVO vo) throws LogicException, IOException;

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
    InnerPerson queryByCode(String code);

    /**
     * 通过id查询劳务外包信息
     * @param id
     * @return
     */
    InnerPerson queryById(Integer id);



    /**
     * Excel数据导入
     * @param innerPersonList
     * @return
     */
    List<String> importExcel(List<Object> innerPersonList);

}
