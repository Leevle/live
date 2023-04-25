package com.leevle.user.model;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class User {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    private String uuid;
    private String password;
    private String username;
    private Boolean role;
}
