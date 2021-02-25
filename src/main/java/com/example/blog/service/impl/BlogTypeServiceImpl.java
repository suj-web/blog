package com.example.blog.service.impl;

import com.example.blog.mapper.BlogTypeMapper;
import com.example.blog.po.BlogType;
import com.example.blog.service.BlogTypeService;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class BlogTypeServiceImpl implements BlogTypeService {
    @Autowired
    private BlogTypeMapper blogTypeMapper;

    @Override
    public List<BlogType> countList() {
        return blogTypeMapper.countList();
    }

    @Override
    public BlogType findById(Integer id) {
        return blogTypeMapper.findById(id);
    }

    @Override
    public List<BlogType> list(Map<String, Object> paramMap) {
        return blogTypeMapper.list(paramMap);
    }

    @Override
    public Long getTotal(Map<String, Object> paramMap) {
        return blogTypeMapper.getTotal(paramMap);
    }

    @Override
    public Integer add(BlogType blogType) {
        return blogTypeMapper.add(blogType);
    }

    @Override
    public Integer update(BlogType blogType) {
        return blogTypeMapper.update(blogType);
    }

    @Override
    public Integer deleteById(Integer id) {
        return blogTypeMapper.deleteById(id);
    }
}
