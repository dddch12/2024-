package com.cq.cd.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Data
@TableName("users")
public class User  {
    @TableId(type = IdType.AUTO)
    private Integer userId;
    private String userName;
    private String passWord;
    private String email;
    private String displayName;
    private LocalDate userCreatedData;
    private LocalDate lastLogin;
    private String profilePicture;
    private String userStatus;
    private String permissionCode;
    private Integer grade;
    private  Integer visible;
    private Integer chat;

    @TableField(exist = false)
    private List<Review> Reviews;

//    @TableField(exist = false)
//    private final Collection<? extends GrantedAuthority> authorities;
//
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return authorities;
//    }
}
