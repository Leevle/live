package com.leevle.live.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Live {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String uuid;
    private String pushCode;
    private String pushToken;
    private Boolean ban;
    private String clientId;
    private String streamId;
}
