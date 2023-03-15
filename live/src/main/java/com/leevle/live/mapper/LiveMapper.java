package com.leevle.live.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.leevle.live.model.Live;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface LiveMapper extends BaseMapper<Live> {
    @Update("update live set client_id=#{client_id} where push_id=#{push_id}")
    int updateClientByPushId(String client_id,String push_id);
}
