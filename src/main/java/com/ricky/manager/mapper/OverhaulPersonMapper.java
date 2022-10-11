package com.ricky.manager.mapper;

import com.ricky.manager.base.BaseMapper;
import com.ricky.manager.entity.OverhaulPerson;
import com.ricky.manager.vo.overhaul.OverhaulPersonQueryCond;

import java.util.List;

/**
 * <p>
 * 检修人员 Mapper 接口
 * </p>
 *
 * @author RickyCharles
 * @since 2022-05-13
 */
public interface OverhaulPersonMapper extends BaseMapper<OverhaulPerson> {
    /**
     * 查询集合
     * @param cond
     * @return
     */
    List<OverhaulPerson> queryList(OverhaulPersonQueryCond cond);
}
