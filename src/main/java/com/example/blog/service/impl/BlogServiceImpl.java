package com.example.blog.service.impl;

import com.example.blog.mapper.BlogMapper;
import com.example.blog.po.Blog;
import com.example.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class BlogServiceImpl implements BlogService {
    @Autowired
    private BlogMapper blogMapper;

    @Override
    public List<Blog> countList() {
        return blogMapper.countList();
    }

    @Override
    public List<Blog> queryLastedList() {
        return blogMapper.queryLastedList();
    }

    @Override
    public List<Blog> list(Map<String, Object> map) {
        return blogMapper.list(map);
    }

    @Override
    public Long getTotal(Map<String, Object> map) {
        return blogMapper.getTotal(map);
    }

    @Override
    public Blog findById(Integer id) {
        return blogMapper.findById(id);
    }

    @Override
    public List<Blog> findByTypeId(Integer typeId) {
        return blogMapper.findByTypeId(typeId);
    }

    @Override
    public List<Blog> findByContentLike(String content) {
        return blogMapper.findByContentLike(content);
    }

    @Override
    public Integer add(Blog blog) {
        return blogMapper.add(blog);
    }

    @Override
    public Integer update(Blog blog) {
        return blogMapper.update(blog);
    }

    @Override
    public Integer deleteById(Integer id) {
        return blogMapper.deleteById(id);
    }

    @Override
    public List<List<Blog>> findByDate() {
        Date date = null;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");

        List<Blog> blogList = blogMapper.countList();
        List<List<Blog>> blogs = new ArrayList<>();
        for(Blog blog:blogList){
            try {
                date = format.parse(blog.getReleaseDateStr());
            } catch (Exception e){
                e.printStackTrace();
            }
            date.setDate(2);
            System.out.println(date);
            List<Blog> blogs1 = blogMapper.queryByDate(date);
            blogs.add(blogs1);
        }

        return blogs;
    }
}
