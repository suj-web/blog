package com.example.blog.controller;

import com.example.blog.po.Blog;
import com.example.blog.po.BlogType;
import com.example.blog.service.BlogService;
import com.example.blog.service.BlogTypeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin/blog")
public class BlogController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private BlogTypeService blogTypeService;

    /**
     * 跳转到写博客页面
     * @param model
     * @return
     */
    @RequestMapping("/toWriteBlog")
    public String toWriteBlog(Model model){
        BlogTypesAdd(model);

        Blog blog = new Blog();
        BlogType blogType = new BlogType();
        blog.setBlogType(blogType);
        model.addAttribute("blog",blog);

        return "admin/write_blog";
    }


    /**
     * 添加博客
     * @param blog
     * @param model
     * @return
     */
    @RequestMapping("/blogAdd")
    public String blogAdd(Blog blog, Model model){

        BlogTypesAdd(model);

            Date date = new Date();
            blog.setReleaseDate(date);
            blog.setSummary(blog.getContent().length()>100 ? blog.getContent().substring(0,101):blog.getContent());
            try {
                blogService.add(blog);
                Blog blog1 = blogService.findById(blog.getId());
                model.addAttribute("blog", blog1);
                model.addAttribute("msg", "true");
            } catch (Exception e) {
                e.printStackTrace();
                model.addAttribute("msg", "false");
            }
        return "admin/write_blog";
    }

    /**
     * 博客列表
     */
    @RequestMapping("/blogList")
    public String blogList(@RequestParam(value="pageNum",required = false, defaultValue = "1") Integer pageNum,
                           @RequestParam(value="pageSize",required = false, defaultValue = "10") Integer pageSize,
                           Model model){
        PageHelper.startPage(pageNum,pageSize);
        Map<String,Object> map = new HashMap<>();
        List<Blog> blogs = blogService.list(map);

        PageInfo<Blog> blogInfo = new PageInfo<>(blogs);

        model.addAttribute("blogs",blogs);
        model.addAttribute("blogInfo",blogInfo);
        return "admin/blog_list";
    }

    @RequestMapping("/toBlogEdit")
    public String toBlogEdit(@RequestParam("id") Integer id,Model model){
        BlogTypesAdd(model);

        Blog blog = blogService.findById(id);
        model.addAttribute("blog",blog);
        return "admin/blog_edit";
    }

    @RequestMapping("/blogEdit")
    public String blogEdit(Blog blog,Model model){
        BlogTypesAdd(model);

        Date date = new Date();
        blog.setReleaseDate(date);
        try {
            blogService.update(blog);
            Blog blog1 = blogService.findById(blog.getId());
            model.addAttribute("blog", blog1);
            model.addAttribute("msg", "true");
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("msg", "false");
        }
        return "admin/blog_edit";
    }

    @RequestMapping("/blogDelete")
    public String blogDelete(@RequestParam("id") Integer id){
        blogService.deleteById(id);
        return "redirect:/admin/blog/blogList";
    }

    private void BlogTypesAdd(Model model){
        List<BlogType> blogTypes = blogTypeService.list(new HashMap<>());
        model.addAttribute("blogTypes",blogTypes);
    }
}
