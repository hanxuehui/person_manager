package com.ricky.manager.mapper;


import com.ricky.manager.base.BaseMapper;
import com.ricky.manager.entity.ProfessionSubcontract;
import com.ricky.manager.vo.profession.ProfessionSubcontractQueryCond;

import java.util.List;

/**
 * <p>
 * 专业分包 Mapper 接口
 * </p>
 *
 * @author RickyCharles
 * @since 2022-05-13
 */
public interface ProfessionSubcontractMapper extends BaseMapper<ProfessionSubcontract> {
    /**
     * 查询集合
     * @param cond
     * @return
     */
    List<ProfessionSubcontract> queryList(ProfessionSubcontractQueryCond cond);
}
