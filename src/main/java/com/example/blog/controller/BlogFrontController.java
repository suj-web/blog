package com.example.blog.controller;

import com.example.blog.po.Blog;
import com.example.blog.po.BlogType;
import com.example.blog.po.Blogger;
import com.example.blog.po.Comment;
import com.example.blog.service.BlogService;
import com.example.blog.service.BlogTypeService;
import com.example.blog.service.BloggerService;
import com.example.blog.service.CommentService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/front")
public class BlogFrontController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private BlogTypeService blogTypeService;

    @Autowired
    private CommentService commentService;

    /**
     * 博客前端页面，展示博客列表
     * @return
     */
    @RequestMapping("/index")
    public String index(@RequestParam(value="pageNum",required = false, defaultValue = "1") Integer pageNum,
                        @RequestParam(value="pageSize",required = false, defaultValue = "10") Integer pageSize,
                        Model model){

        PageHelper.startPage(pageNum,pageSize);

        Map<String,Object> map = new HashMap<>();
        List<Blog> blogList = blogService.list(map);

        PageInfo<Blog> blogPageInfo = new PageInfo<>(blogList);

        //右侧博客类型
        PageHelper.startPage(1,5);
        List<BlogType> blogTypes = blogTypeService.countList();
        PageInfo<BlogType> blogTypePageInfo = new PageInfo<>(blogTypes);

        //右侧最新博客
        List<Blog> lastedBlogLists = blogService.queryLastedList();

        model.addAttribute("blogList",blogList);
        model.addAttribute("blogPageInfo",blogPageInfo);
        model.addAttribute("blogTypes",blogTypes) ;
        model.addAttribute("lastedBlogLists",lastedBlogLists);
        return "index";
    }

    /**
     * 搜索框模糊查询
     * @return
     */
    @RequestMapping("/checkByKey")
    public String checkByKey(@RequestParam(value="pageNum",required = false, defaultValue = "1") Integer pageNum,
                             @RequestParam(value="pageSize",required = false, defaultValue = "10") Integer pageSize,
                             @RequestParam("keyContent") String keyContent, Model model){
        PageHelper.startPage(pageNum,pageSize);

        List<Blog> blogs = blogService.findByContentLike(keyContent);
        PageInfo<Blog> blogPageInfo = new PageInfo<>(blogs);

        model.addAttribute("keyContent",keyContent);
        model.addAttribute("blogs",blogs);
        model.addAttribute("blogPageInfo",blogPageInfo);
        return "check";
    }

    /**
     * 博客详情
     * 显示评论
     * 评论提交
     */
    @RequestMapping("/blog")
    public String blog(@RequestParam(value="blogId",required = false) Integer blogId, Comment comment, HttpSession session, Model model){
        Blogger blogger= (Blogger)session.getAttribute("blogger");
        if(comment.getCommenterName()!=null || "".equals(comment.getCommenterName())){
            comment.setBlogId(blogId);
            comment.setCommentDate(new Date());
            comment.setAdminComment(false);
            //判断是否是博主
            if(blogger!=null){
                comment.setCommenterName(blogger.getUsername());
                comment.setAdminComment(true);
            }
            commentService.add(comment);
        }

        List<Comment> commentList = commentService.findByBlogId(blogId);
        Blog blog = blogService.findById(blogId);

        model.addAttribute("commentList",commentList);
        model.addAttribute("blog",blog);
        return "blog";
    }

    /**
     * 博客分类，根据不同类型，展示博客列表
     * @return
     */
    @RequestMapping("/types")
    public String types(@RequestParam(value="pageNum",required = false, defaultValue = "1") Integer pageNum,
                        @RequestParam(value="pageSize",required = false, defaultValue = "10") Integer pageSize,
                        @RequestParam(value="blogTypeId",required = false) Integer blogTypeId, Model model){

        System.out.println(blogTypeId);
        if(blogTypeId == 0) {
            Map<String,Object> map =new HashMap<>();
            List<BlogType> types = blogTypeService.list(map);
            blogTypeId = types.get(0).getId();
        }

        PageHelper.startPage(pageNum,pageSize);
        List<Blog> blogs = blogService.findByTypeId(blogTypeId);
        PageInfo<Blog> blogPageInfo = new PageInfo<>(blogs);


        //博客类别标签
        List<BlogType> blogTypes = blogTypeService.countList();
        PageInfo<BlogType> blogTypePageInfo = new PageInfo<>(blogTypes);

        //该类别下的所有博客

        System.out.println(blogPageInfo);
        System.out.println(blogTypePageInfo);

        model.addAttribute("blogTypeId",blogTypeId);
        model.addAttribute("blogs",blogs);
        model.addAttribute("blogPageInfo",blogPageInfo);
        model.addAttribute("blogTypes",blogTypes);
        model.addAttribute("blogTypePageInfo",blogTypePageInfo);
        return "types";
    }

    /**
     * 博客归档，按发布时间日期，展示博客列表
     * @return
     */
   @RequestMapping("/archives")
    public String archives(Model model){
       List<List<Blog>> blogList = blogService.findByDate();

       Long blogNumber = blogService.getTotal(new HashMap<>());

       model.addAttribute("blogList",blogList);
       model.addAttribute("blogNumber",blogNumber);
       return "archives";
   }

    /**
     * 关于博主的详情
     */
    @RequestMapping("/about")
    public String about() {
        return "about";
    }
}
