package com.example.blog.po;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Blogger {
    //主键
    private Integer id;
    //登录名
    private String username;
    //密码
    private String password;
    //个人信息
    private String profile;
    //昵称
    private String nickname;
    //个性签名
    private String sign;
    //头像地址
    private String imageName;
}
