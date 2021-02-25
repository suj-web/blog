package com.example.blog.po;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;


@Data
@ToString
public class Blog implements Serializable {
    private Integer id;
    //标题
    private String title;
    //摘要
    private String summary;
    //发布时间
    @DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy/MM/dd HH:mm:ss")
    private Date releaseDate;
    //点击数
    private Integer clickHit;
    //评论数
    private Integer replyHit;
    //内容
    private String content;
    //类别id
    private BlogType blogType;
    //关键字
    private String keyWord;

    /**纯文本格式的内容*/
    private String contentNoTag;

    /**发布时间*/
    private String releaseDateStr;

    /**博客数量*/
    private Integer blogCount;
}
