package com.example.blog.service.impl;

import com.example.blog.mapper.CommentMapper;
import com.example.blog.po.Comment;
import com.example.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentMapper commentMapper;

    @Override
    public List<Comment> list(Integer blogId) {
        return commentMapper.list(blogId);
    }

    @Override
    public List<Comment> findByBlogId(Integer blogId) {
        return commentMapper.findByBlogId(blogId);
    }

    @Override
    public Comment findById(Integer id) {
        return commentMapper.findById(id);
    }

    @Override
    public Integer add(Comment comment) {
        return commentMapper.add(comment);
    }

    @Override
    public Integer deleteById(Integer id) {
        return commentMapper.deleteById(id);
    }

    @Override
    public Integer deleteChildComment(Integer parentId, Integer blogId) {
        return commentMapper.deleteChildComment(parentId,blogId);
    }
}
