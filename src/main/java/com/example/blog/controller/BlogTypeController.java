package com.example.blog.controller;

import com.example.blog.po.BlogType;
import com.example.blog.service.BlogTypeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin/blogType")
public class BlogTypeController {

    @Autowired
    private BlogTypeService blogTypeService;

    @RequestMapping("/toTypeList")
    public String toTypeList() {
        return "admin/type_list";
    }

    @RequestMapping("/typeList")
    public String typeList(@RequestParam(value="pageNum",required = false, defaultValue = "1") Integer pageNum,
                       @RequestParam(value="pageSize",required = false, defaultValue = "10") Integer pageSize,
                       Model model){
        PageHelper.startPage(pageNum,pageSize);

        Map<String, Object> map = new HashMap<>();
        map.put("typeName","");

        List<BlogType> blogTypes = blogTypeService.list(map);

        PageInfo<BlogType> blogList = new PageInfo<>(blogTypes);

        model.addAttribute("blogTypes",blogTypes);
        model.addAttribute("blogTypeInfo",blogList);

        return "admin/type_list";
    }

    /**
     * 跳转到类别添加页面
     */
    @RequestMapping("/toTypeAdd")
    public String toTypeAdd(){
        return "admin/type_add";
    }

    /**
     * 类别添加
     */
    @RequestMapping("/typeAdd")
    public String typeAdd(BlogType blogType){
        blogTypeService.add(blogType);
        return "redirect:/admin/blogType/typeList";
    }

    /**
     * 跳转到类别修改页面
     */
    @RequestMapping("/toTypeEdit")
    public String toTypeEdit(@RequestParam("id") Integer id,Model model){
        BlogType blogType = blogTypeService.findById(id);
        model.addAttribute("blogType",blogType);
        return "admin/type_edit";
    }

    /**
     * 类别修改
     */
    @RequestMapping("/typeEdit")
    public String typeEdit(BlogType blogType){
        blogTypeService.update(blogType);
        return "redirect:/admin/blogType/typeList";
    }

    /**
     * 类别删除
     */
    @RequestMapping("/typeDelete")
    public String typeDelete(@RequestParam("id") Integer id){
        blogTypeService.deleteById(id);
        return "redirect:/admin/blogType/typeList";
    }
}
