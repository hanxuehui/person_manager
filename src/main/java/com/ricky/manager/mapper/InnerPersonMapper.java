package com.ricky.manager.mapper;


import com.ricky.manager.base.BaseMapper;
import com.ricky.manager.entity.InnerPerson;
import com.ricky.manager.vo.inner.InnerPersonQueryCond;

import java.util.List;

/**
 * 内部人员(InnerPerson)表数据库访问层
 *
 * @author makejava
 * @since 2022-05-09 10:26:50
 */
public interface InnerPersonMapper extends BaseMapper<InnerPerson> {

    /**
     * 查询集合
     * @param cond
     * @return
     */
    List<InnerPerson> queryList(InnerPersonQueryCond cond);
}

