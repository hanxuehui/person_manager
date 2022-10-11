package com.ricky.manager.mapper;


import com.ricky.manager.base.BaseMapper;
import com.ricky.manager.entity.PersonDispatch;
import com.ricky.manager.vo.dispatch.PersonDispatchQueryCond;

import java.util.List;

/**
 * <p>
 * 人员调遣 Mapper 接口
 * </p>
 *
 * @author RickyCharles
 * @since 2022-05-13
 */
public interface PersonDispatchMapper extends BaseMapper<PersonDispatch> {
    /**
     * 查询集合
     * @param cond
     * @return
     */
    List<PersonDispatch> queryList(PersonDispatchQueryCond cond);
}
