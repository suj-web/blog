package com.example.blog.po;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Data
@ToString
public class Comment {
    private Integer id;
    private Integer blogId;
    private Integer parentId;
    private String commenterName;
    private String commenterEmail;
    private String content;

    @DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy/MM/dd HH:mm:ss")
    private Date commentDate;

    private Boolean adminComment;

    private List<Comment> commentList;

    private Comment parentComment;
}
