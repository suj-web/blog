package com.example.blog.service;

import com.example.blog.po.Blog;

import java.util.List;
import java.util.Map;

public interface BlogService {

    List<Blog> countList();

    List<Blog> queryLastedList();

    List<Blog> list(Map<String, Object> map);

    Long getTotal(Map<String, Object> map);

    Blog findById(Integer id);

    List<Blog> findByTypeId(Integer typeId);

    List<Blog> findByContentLike(String content);

    Integer add(Blog blog);

    Integer update(Blog blog);

    Integer deleteById(Integer id);

    List<List<Blog>> findByDate();
}
