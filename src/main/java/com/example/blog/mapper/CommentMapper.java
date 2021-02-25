package com.example.blog.mapper;

import com.example.blog.po.Comment;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface CommentMapper {

    @Select("select * from t_comment where blogId=#{blogId}")
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "blogId",column="blogId"),
            @Result(property = "parentId",column = "parentId"),
            @Result(property = "commenterName",column = "commenterName"),
            @Result(property = "commenterEmail",column = "commenterEmail"),
            @Result(property = "content",column = "content"),
            @Result(property = "commentDate",column = "commentDate"),
            @Result(property = "adminComment",column = "adminComment"),
            @Result(
                    property = "parentComment",
                    column = "parentId",
                    javaType = Comment.class,
                    one = @One(select = "com.example.blog.mapper.CommentMapper.findById")
            )
    })
    List<Comment> list(Integer blogId);

    @Select("select * from t_comment where blogId=#{blogId} and parentId=0")
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "blogId",column="blogId"),
            @Result(property = "parentId",column = "parentId"),
            @Result(property = "commenterName",column = "commenterName"),
            @Result(property = "commenterEmail",column = "commenterEmail"),
            @Result(property = "content",column = "content"),
            @Result(property = "commentDate",column = "commentDate"),
            @Result(property = "adminComment",column = "adminComment"),
            @Result(
                    property = "commentList",
                    column = "id",
                    javaType = List.class,
                    many = @Many(select = "com.example.blog.mapper.CommentMapper.findByParentId")
            )
    })
    List<Comment> findByBlogId(Integer blogId);

    @Select("select * from t_comment where parentId=#{parentId}")
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "blogId",column="blogId"),
            @Result(property = "parentId",column = "parentId"),
            @Result(property = "commenterName",column = "commenterName"),
            @Result(property = "commenterEmail",column = "commenterEmail"),
            @Result(property = "content",column = "content"),
            @Result(property = "commentDate",column = "commentDate"),
            @Result(property = "adminComment",column = "adminComment"),
            @Result(
                    property = "commentList",
                    column = "id",
                    javaType = List.class,
                    many = @Many(select = "com.example.blog.mapper.CommentMapper.findByParentId")
            )
    })
    List<Comment> findByParentId(Integer parentId);

    @Select("select * from t_comment where id=#{id}")
    Comment findById(Integer id);

    @Insert("insert into t_comment values(null,#{blogId},#{parentId},#{commenterName},#{commenterEmail},#{content},#{commentDate},#{adminComment})")
    Integer add(Comment comment);

    @Delete("delete from t_comment where id=#{id}")
    Integer deleteById(Integer id);

    @Delete("delete from t_comment where parentId=#{parentId} and blogId=#{blogId}")
    Integer deleteChildComment(@Param("parentId") Integer parentId,@Param("blogId") Integer blogId);
}
