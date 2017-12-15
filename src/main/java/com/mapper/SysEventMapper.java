package com.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.bean.SysEvent;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/12/12.
 */
public interface SysEventMapper extends BaseMapper<SysEvent> {
    List<Long> selectIdPage(Page<Long> page, @Param("cm") Map<String, Object> map);
}
