package com.example.blog.service.impl;

import com.example.blog.mapper.BloggerMapper;
import com.example.blog.po.Blogger;
import com.example.blog.service.BloggerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BloggerServiceImpl implements BloggerService {
    @Autowired
    private BloggerMapper bloggerMapper;

    @Override
    public Blogger checkByUsernameAndPassword(String username, String password) {
        Blogger blogger1 =  bloggerMapper.findByUsernameAndPassword(username,password);
        if(blogger1 == null) {
            return null;
        }
        return blogger1;
    }

    @Override
    public Blogger checkByUsername(String username) {
        Blogger blogger = bloggerMapper.findByUsername(username);
        if(blogger == null) {
            return null;
        }
        return blogger;
    }

    @Override
    public Blogger findById(Integer id) {
        return bloggerMapper.findById(id);
    }

    @Override
    public Integer update(Blogger blogger) {
        return bloggerMapper.update(blogger);
    }
}
