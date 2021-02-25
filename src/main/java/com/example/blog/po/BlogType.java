package com.example.blog.po;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * 博客类型
 */
@Data
@ToString
public class BlogType implements Serializable {
    //主键
    private Integer id;
    //类型名称
    private String typeName;
    //序号
    private Integer orderNo;
    //该类型下博客的数量
    private Integer blogCount;
    //该类型下的博客
    private List<Blog> blogList;
}
