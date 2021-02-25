package com.example.blog.mapper;

import com.example.blog.po.Blog;
import com.example.blog.po.BlogType;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

public interface BlogTypeMapper {
    //无参数查询所有博客类型列表
    @Select("select t2.id,t2.typeName,t2.orderNo,count(t1.id) as blogCount from t_blog t1 right join t_blogtype t2 on t1.typeId=t2.id group by t2.typeName order by t2.orderNo")
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "typeName",column = "typeName"),
            @Result(property = "orderNo",column = "orderNo"),
            @Result(property = "blogCount",column = "blogCount"),
            @Result(
                    property = "blogList",
                    column = "id",
                    javaType = List.class,
                    many=@Many(select = "com.example.blog.mapper.BlogMapper.findByTypeId")
            )
    })
    List<BlogType> countList();

    //根据id查询一条博客类型
    @Select("select * from t_blogtype where id=#{id}")
    BlogType findById(Integer id);

    //不固定参数查询博客类型列表
    @Select({"<script>",
            "select * from t_blogtype where 1=1",
            "<if test='typeName!=null and typeName!=\"\"'>",
            "and typeName=#{typeName} ",
            "</if>",
            "</script>"})
    List<BlogType> list(Map<String,Object> paramMap);

    //查询博客类型数
    @Select("select count(*) from t_blogtype")
    Long getTotal(Map<String,Object> paramMap);

    //添加
    @Insert("insert into t_blogtype values(null,#{typeName},#{orderNo})")
    Integer add(BlogType blogType);

    //修改
    @Update({"<script>",
            "update t_blogtype ",
            "<set>",
            "<if test='typeName!=null and typeName!=\"\"'>",
            "typeName=#{typeName},",
            "</if>",
            "<if test='orderNo!=null and orderNo!=\"\"'>",
            "orderNo=#{orderNo}",
            "</if>",
            "</set>",
            "where id=#{id}",
            "</script>"})
    Integer update(BlogType blogType);

    //删除
    @Delete("delete from t_blogtype where id=#{id}")
    Integer deleteById(Integer id);
}
