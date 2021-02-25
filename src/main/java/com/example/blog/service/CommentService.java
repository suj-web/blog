package com.example.blog.service;

import com.example.blog.po.Comment;

import java.util.List;

public interface CommentService {

    List<Comment> list(Integer blogId);

    List<Comment> findByBlogId(Integer blogId);

    Comment findById(Integer id);

    Integer add(Comment comment);

    Integer deleteById(Integer id);

    Integer deleteChildComment(Integer parentId,Integer blogId);

}
