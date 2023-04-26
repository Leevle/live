package com.leevle.live.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.leevle.live.model.Live;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface LiveMapper extends BaseMapper<Live> {
    @Select("select uuid,push_code from live where client_id!='' and ban=0")
    List<Live> getOnlineLive();


}
