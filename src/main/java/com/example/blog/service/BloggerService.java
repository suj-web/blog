package com.example.blog.service;

import com.example.blog.po.Blogger;

public interface BloggerService {

    Blogger checkByUsernameAndPassword(String username, String password);

    Blogger checkByUsername(String username);

    Blogger findById(Integer id);

    Integer update(Blogger blogger);
}
