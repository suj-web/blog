package com.example.blog.mapper;

import com.example.blog.po.Blog;
import com.example.blog.po.Blogger;
import org.apache.ibatis.annotations.*;

public interface BloggerMapper {
    @Select("select * from t_blogger where username=#{username} and password=#{password}")
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "username",column = "username"),
            @Result(property = "password",column = "password"),
            @Result(property = "profile",column = "profile"),
            @Result(property = "nickname",column = "nickname"),
            @Result(property = "sign",column = "sign"),
            @Result(property = "imageName",column = "imageName")
    })
    Blogger findByUsernameAndPassword(@Param("username") String username,@Param("password") String password);

    @Select("select * from t_blogger where username=#{username}")
    Blogger findByUsername(String username);

    @Select("select * from t_blogger where id=#{id}")
    Blogger findById(Integer id);

    @Update({"<script>",
            "update t_blogger ",
            "<set>",
            "<if test='username!=null and username!=\"\"'>",
            "username=#{username},",
            "</if>",
            "<if test='password!=null and password!=\"\"'>",
            "password=#{password},",
            "</if>",
            "<if test='profile!=null and profile!=\"\"'>",
            "profile=#{profile},",
            "</if>",
            "<if test='nickname!=null and nickname!=\"\"'>",
            "nickname=#{nickname},",
            "</if>",
            "<if test='sign!=null and sign!=\"\"'>",
            "sign=#{sign},",
            "</if>",
            "<if test='imageName!=null and imageName!=\"\"'>",
            "imageName=#{imageName}",
            "</if>",
            "</set>",
            "where id=#{id}",
            "</script>"
    })
    Integer update(Blogger blogger);
}
