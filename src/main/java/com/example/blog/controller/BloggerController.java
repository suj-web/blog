package com.example.blog.controller;

import com.example.blog.po.Blogger;
import com.example.blog.service.BloggerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import utils.CopyFile;
import utils.MD5Util;

import javax.jws.WebParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.UUID;
import java.util.jar.Attributes;

/**
 * 博主登录相关
 */
@Controller
@RequestMapping("/blogger")
public class BloggerController {

    @Autowired
    private BloggerService bloggerService;

    @RequestMapping("/toLogin")
    public String toLogin(Model model, @RequestParam(value="update_msg",required = false)String update_msg){
        model.addAttribute("update_msg",update_msg);
        return "admin/login";
    }

    @RequestMapping("/index")
    public String index(HttpSession session, Model model){
        Blogger blogger = (Blogger) session.getAttribute("blogger");
        model.addAttribute("blogger",blogger);
        return "admin/index";
    }

    @RequestMapping("/img")
    public String img(){
        return "admin/img";
    }

    @RequestMapping("/login")
    public String login(Blogger blogger, HttpSession session, RedirectAttributes attributes){
        String username = blogger.getUsername();
        String password = blogger.getPassword();

        String mD5PW = MD5Util.md5(password,"java_suj");

        Blogger blogger1 = bloggerService.checkByUsernameAndPassword(username,mD5PW);
        if(blogger1 != null){
            blogger1.setPassword(password);
            session.setAttribute("blogger",blogger1);
            return "redirect:/blogger/index";
        } else {
            attributes.addFlashAttribute("message","用户名或密码错误");
            return "redirect:/blogger/toLogin";
        }

//        Subject subject = SecurityUtils.getSubject();
//        UsernamePasswordToken token = new UsernamePasswordToken(username,mD5PW);
//        try {
//            subject.login(token);
//            return "index";
//        } catch (Exception e){
//            e.printStackTrace();
//            attributes.addFlashAttribute("message","用户名或密码错误");
//            session.setAttribute("blogger",blogger);
////            return "redirect:/blogger/toLogin";
//        }
//        return "index";
    }

    @RequestMapping("/toPersonalMessageEdit")
    public String toPersonalMessageEdit(HttpSession session, Model model){
        Blogger blogger = (Blogger) session.getAttribute("blogger");
        model.addAttribute("blogger",blogger);
        return "admin/personal_message_edit";
    }

    @RequestMapping("/personalMessageEdit")
    public String personalMessageEdit(Blogger blogger, @RequestParam("file") MultipartFile uploadFile,
                                      HttpServletRequest request,RedirectAttributes attributes){
        blogger.setPassword(MD5Util.md5(blogger.getPassword(),"java_suj"));

        String avatarPath = request.getSession().getServletContext().getRealPath("/") + "static/images/avatar/avatar.jpg";//头像路径
        try {
            File avatarFile = new File(avatarPath);
            if(avatarFile.exists()){
                avatarFile.delete();
            }
            uploadFile.transferTo(new File(avatarPath));
            blogger.setImageName(avatarPath);
            bloggerService.update(blogger);
        }catch (Exception e){
            e.printStackTrace();
            attributes.addFlashAttribute("update_msg","false");
            return "redirect:/blogger/toPersonalMessageEdit";
        }
        attributes.addAttribute("update_msg","true");
        request.getSession().removeAttribute("blogger");
        return "redirect:/blogger/toLogin";
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("blogger");
        return "redirect:/blogger/toLogin";
    }
}
