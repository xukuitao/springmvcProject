package com.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/12/12.
 */
public interface SysEventMapper  {
    List<Long> selectIdPage(@Param("cm") Map<String, Object> map);
}
