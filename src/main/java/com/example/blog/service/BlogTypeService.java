package com.example.blog.service;

import com.example.blog.po.BlogType;

import java.util.List;
import java.util.Map;

public interface BlogTypeService {
    List<BlogType> countList();

    BlogType findById(Integer id);

    List<BlogType> list(Map<String,Object> paramMap);

    Long getTotal(Map<String,Object> paramMap);

    Integer add(BlogType blogType);

    Integer update(BlogType blogType);

    Integer deleteById(Integer id);
}
