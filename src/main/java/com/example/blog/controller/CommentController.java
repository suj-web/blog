package com.example.blog.controller;

import com.example.blog.po.Blog;
import com.example.blog.po.Comment;
import com.example.blog.service.BlogService;
import com.example.blog.service.CommentService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private BlogService blogService;


    /**
     * 跳转到含有评论信息的博客列表
     * @return
     */
    @RequestMapping("/commentBlogList")
    public String commentBlogList(@RequestParam(value="pageNum",required = false, defaultValue = "1") Integer pageNum,
                           @RequestParam(value="pageSize",required = false, defaultValue = "10") Integer pageSize,
                           Model model){
        PageHelper.startPage(pageNum,pageSize);
        Map<String,Object> map = new HashMap<>();
        List<Blog> blogs = blogService.list(map);

        PageInfo<Blog> blogInfo = new PageInfo<>(blogs);

        model.addAttribute("blogs",blogs);
        model.addAttribute("blogInfo",blogInfo);
        return "admin/comment_blog_list";
    }

    @RequestMapping("/commentList")
    public String commentList(@RequestParam(value="pageNum",required = false, defaultValue = "1") Integer pageNum,
                              @RequestParam(value="pageSize",required = false, defaultValue = "10") Integer pageSize,
                              @RequestParam("blogId") Integer blogId,Model model){

        PageHelper.startPage(pageNum,pageSize);

        List<Comment> commentList = commentService.list(blogId);
        PageInfo<Comment> commentPageInfo = new PageInfo<>(commentList);

        model.addAttribute("commentList",commentList);
        model.addAttribute("commentPageInfo",commentPageInfo);
        model.addAttribute("blogId",blogId);
        return "admin/comment_list";
    }

    @RequestMapping("/commentDelete")
    public String commentDelete(@RequestParam("blogId")Integer blogId, @RequestParam("commentId")Integer commentId, RedirectAttributes attributes){
        commentService.deleteById(commentId);
        attributes.addAttribute("blogId",blogId);
        return "redirect:/admin/comment/commentList";
    }
}
