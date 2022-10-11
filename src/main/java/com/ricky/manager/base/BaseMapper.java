package com.ricky.manager.base;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @author RickyCharles
 * @apiNote
 */
public interface BaseMapper<T> extends Mapper<T>,MySqlMapper<T> {

}
