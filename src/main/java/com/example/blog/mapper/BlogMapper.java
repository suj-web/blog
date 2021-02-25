package com.example.blog.mapper;

import com.example.blog.po.Blog;
import com.example.blog.po.BlogType;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface BlogMapper {
    /**无参数查询所有博客列表*/
    @Select("select date_format(releaseDate,'%Y-%m') as releaseDateStr,count(*) as blogCount " +
            "from t_blog group by date_format(releaseDate,'%Y-%m') " +
            "order by date_format(releaseDate,'%Y-%m') desc")
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "title",column = "title"),
            @Result(property = "summary",column = "summary"),
            @Result(property = "releaseDate",column = "releaseDate"),
            @Result(property = "clickHit",column = "clickHit"),
            @Result(property = "replyHit",column = "replyHit"),
            @Result(property = "content",column = "content"),
            @Result(property = "keyWord",column = "keyWord"),
            @Result(property = "releaseDateStr",column = "releaseDateStr"),
            @Result(property = "blogCount",column = "blogCount"),
            @Result(
                    property = "blogType",
                    column = "typeId",
                    javaType = BlogType.class,
                    one=@One(select = "com.example.blog.mapper.BlogTypeMapper.findById")
            )
    })
    List<Blog> countList();

    /**
     * 根据日期进行查询
     */
    @Select("select * from t_blog where year(releaseDate)=year(#{releaseDate}) and month(releaseDate)=month(#{releaseDate})  order by releaseDate desc")
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "title",column = "title"),
            @Result(property = "summary",column = "summary"),
            @Result(property = "releaseDate",column = "releaseDate"),
            @Result(property = "clickHit",column = "clickHit"),
            @Result(property = "replyHit",column = "replyHit"),
            @Result(property = "content",column = "content"),
            @Result(property = "keyWord",column = "keyWord"),
            @Result(property = "releaseDateStr",column = "releaseDateStr"),
            @Result(property = "blogCount",column = "blogCount"),
            @Result(
                    property = "blogType",
                    column = "typeId",
                    javaType = BlogType.class,
                    one=@One(select = "com.example.blog.mapper.BlogTypeMapper.findById")
            )
    })
    List<Blog> queryByDate(Date releaseDate);

    /**
     * 查询10条最新博客
     */
    @Select("select * from t_blog order by releaseDate desc limit 10")
    List<Blog> queryLastedList();

    /**带参数查询所有博客列表*/
    @Select({"<script>",
            "select * from t_blog where 1=1",
            "<if test='title!=null and title!=\"\"'>",
            "and title like #{typeName} ",
            "</if>",
            "<if test='typeId!=null and typeId!=\"\"'>",
            "and typeId=#{typeId} ",
            "</if>",
            "<if test='releaseDateStr!=null and releaseDateStr!=\"\"'>",
            "and date_format(releaseDate,'%Y年%m月')=#{releaseDateStr}",
            "</if>",
            "order by releaseDate desc",
            "</script>"})
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "title",column = "title"),
            @Result(property = "summary",column = "summary"),
            @Result(property = "releaseDate",column = "releaseDate"),
            @Result(property = "clickHit",column = "clickHit"),
            @Result(property = "replyHit",column = "replyHit"),
            @Result(property = "content",column = "content"),
            @Result(property = "keyWord",column = "keyWord"),
            @Result(property = "releaseDateStr",column = "releaseDateStr"),
            @Result(property = "blogCount",column = "blogCount"),
            @Result(
                    property = "blogType",
                    column = "typeId",
                    javaType = BlogType.class,
                    one=@One(select = "com.example.blog.mapper.BlogTypeMapper.findById")
            )
    })
    List<Blog> list(Map<String, Object> map);

    /**带参数查询博客数量*/
    @Select({"<script>",
            "select count(*) from t_blog where 1=1",
            "<if test='title!=null and title!=\"\"'>",
            "and title like #{typeName} ",
            "</if>",
            "<if test='typeId!=null and typeId!=\"\"'>",
            "and typeId=#{typeId} ",
            "</if>",
            "<if test='releaseDateStr!=null and releaseDateStr!=\"\"'>",
            "and date_format(releaseDate,'%Y年%m月')=#{releaseDateStr}",
            "</if>",
            "</script>"})
    Long getTotal(Map<String, Object> map);

    /**根据主键查询一条博客信息*/
    @Select("select * from t_blog where id=#{id}")
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "title",column = "title"),
            @Result(property = "summary",column = "summary"),
            @Result(property = "releaseDate",column = "releaseDate"),
            @Result(property = "clickHit",column = "clickHit"),
            @Result(property = "replyHit",column = "replyHit"),
            @Result(property = "content",column = "content"),
            @Result(property = "keyWord",column = "keyWord"),
            @Result(property = "releaseDateStr",column = "releaseDateStr"),
            @Result(property = "blogCount",column = "blogCount"),
            @Result(
                    property = "blogType",
                    column = "typeId",
                    javaType = BlogType.class,
                    one=@One(select = "com.example.blog.mapper.BlogTypeMapper.findById")
            )
    })
    Blog findById(Integer id);


    @Select("select *,count(*) as blogCount from t_blog where typeId=#{typeId}")
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "title",column = "title"),
            @Result(property = "summary",column = "summary"),
            @Result(property = "releaseDate",column = "releaseDate"),
            @Result(property = "clickHit",column = "clickHit"),
            @Result(property = "replyHit",column = "replyHit"),
            @Result(property = "content",column = "content"),
            @Result(property = "keyWord",column = "keyWord"),
            @Result(property = "releaseDateStr",column = "releaseDateStr"),
            @Result(property = "blogCount",column = "blogCount"),
            @Result(
                    property = "blogType",
                    column = "typeId",
                    javaType = BlogType.class,
                    one=@One(select = "com.example.blog.mapper.BlogTypeMapper.findById")
            )
    })
    List<Blog> findByTypeId(Integer typeId);


    @Select("select * from t_blog where content like CONCAT('%',#{content},'%')")
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "title",column = "title"),
            @Result(property = "summary",column = "summary"),
            @Result(property = "releaseDate",column = "releaseDate"),
            @Result(property = "clickHit",column = "clickHit"),
            @Result(property = "replyHit",column = "replyHit"),
            @Result(property = "content",column = "content"),
            @Result(property = "keyWord",column = "keyWord"),
            @Result(property = "releaseDateStr",column = "releaseDateStr"),
            @Result(property = "blogCount",column = "blogCount"),
            @Result(
                    property = "blogType",
                    column = "typeId",
                    javaType = BlogType.class,
                    one=@One(select = "com.example.blog.mapper.BlogTypeMapper.findById")
            )
    })
    List<Blog> findByContentLike(String content);

    /**添加*/
    @Insert("insert into t_blog values(null,#{title},#{summary},#{releaseDate},0,0,#{content},#{blogType.id},#{keyWord})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    Integer add(Blog blog);

    /**修改*/
    @Update({"<script>",
            "update t_blog ",
            "<set>",
            "<if test='title!=null and title!=\"\"'>",
            "title=#{title},",
            "</if>",
            "<if test='summary!=null and summary!=\"\"'>",
            "summary=#{summary},",
            "</if>",
            "<if test='content!=null and content!=\"\"'>",
            "content=#{content},",
            "</if>",
            "<if test='blogType.id!=null'>",
            "typeId=#{blogType.id},",
            "</if>",
            "<if test='clickHit!=null'>",
            "clickHit=#{clickHit},",
            "</if>",
            "<if test='replyHit!=null'>",
            "replyHit=#{replyHit},",
            "</if>",
            "<if test='keyWord!=null and keyWord!=\"\"'>",
            "keyWord=#{keyWord},",
            "</if>",
            "</set>",
            "where id=#{id}",
            "</script>"})
    Integer update(Blog blog);

    /**删除*/
    @Delete("delete from t_blog where id=#{id}")
    Integer deleteById(Integer id);
}
