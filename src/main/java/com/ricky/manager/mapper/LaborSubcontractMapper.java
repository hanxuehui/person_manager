package com.ricky.manager.mapper;

import com.ricky.manager.base.BaseMapper;
import com.ricky.manager.entity.LaborSubcontract;
import com.ricky.manager.vo.labor.LaborSubcontractQueryCond;

import java.util.List;

/**
 * 劳务分包(LaborSubcontract)表数据库访问层
 *
 * @author makejava
 * @since 2022-05-12 14:54:30
 */
public interface LaborSubcontractMapper extends BaseMapper<LaborSubcontract> {

    /**
     * 查询集合
     * @param cond
     * @return
     */
    List<LaborSubcontract> queryList(LaborSubcontractQueryCond cond);

}

