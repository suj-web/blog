package test;

import com.example.blog.mapper.BlogMapper;
import com.example.blog.mapper.BlogTypeMapper;
import com.example.blog.mapper.CommentMapper;
import com.example.blog.po.Blog;
import com.example.blog.po.BlogType;
import com.example.blog.po.Blogger;
import com.example.blog.po.Comment;
import com.example.blog.service.BlogService;
import com.example.blog.service.BlogTypeService;
import com.example.blog.service.BloggerService;
import com.example.blog.service.CommentService;
import lombok.ToString;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import utils.MD5Util;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/applicationContext-*.xml")
public class test {

    @Autowired
    private BloggerService bloggerService;

    @Autowired
    private BlogService blogService;

    @Autowired
    private BlogTypeMapper blogTypeMapper;

    @Autowired
    private BlogTypeService blogTypeService;

    @Autowired
    private BlogMapper blogMapper;

    @Test
    public void testBloggerService() {
        Blogger blogger = new Blogger();
        blogger.setUsername("admin");
        blogger.setPassword(MD5Util.md5("admin","java_suj"));
        Blogger blogger1 = bloggerService.checkByUsername("admin");
        System.out.println(blogger1);
    }

    @Test
    public void testBlogTypeMapper() {
        BlogType blogType = new BlogType();
        blogType.setId(1);
        blogType.setTypeName("Python");
        blogType.setOrderNo(2);
//        blogTypeMapper.add(blogType);
//        blogTypeMapper.update(blogType);
//        blogTypeMapper.deleteById(2);
//        blogTypeMapper.getTotal(new HashMap<String, Object>());
        List<BlogType> list =  blogTypeMapper.countList();
        System.out.println(list);
//        Map<String,Object> map = new HashMap<>();
//        map.put("id",1);
//        System.out.println(blogTypeMapper.list(map));

//        Map<String, Object> map = new HashMap<>();
//        map.put("typeName","");

//        List<Blog> blogTypes = blogMapper.findByContentLike("娃娃");
//        System.out.println(blogTypes);

    }

    @Test
    public void testBlogMapper(){
        Blog blog = new Blog();
        Date date = new Date();
        blog.setReleaseDate(date);

        BlogType blogType = new BlogType();
        blog.setBlogType(blogType);

//        blogMapper.add(blog);
//        System.out.println(blogMapper.list(new HashMap<>())+"++++++++++++++++++");
//        blogMapper.list(new HashMap<>());
//        System.out.println(blogMapper.countList());
//        System.out.println(blogMapper.findById(1));

//        System.out.println(blogMapper.add(blog));
//        Map<String,Object> map = new HashMap<>();
//
//        System.out.println(blogMapper.list(map));
        System.out.println(blogService.findByDate());

    }

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private CommentService commentService;

    @Test
    public void testCommentMapper(){
        Comment comment = new Comment();
        comment.setParentId(1);
        comment.setBlogId(4);
        comment.setContent("我不同意");
        comment.setCommentDate(new Date());
        comment.setCommenterName("平天大圣");
        comment.setCommenterEmail("66666666@qq.com");
//        commentMapper.add(comment);
//        commentMapper.deleteById(9);
//        System.out.println(commentMapper.findByBlogId(4)+"***");
//        System.out.println(commentMapper.findById(5));
//        Comment comment1 = commentService.findById(6);
//        commentService.deleteById(6);
//        commentService.deleteChildComment(comment1.getId(),comment1.getBlogId());
//        commentMapper.deleteChildComment(6,4);
//        System.out.println(commentMapper.findByBlogId(1));
//        File file = new File("D:\\1.txt");
        System.out.println(commentService.findByBlogId(1));
        System.out.println(commentService.list(1));
    }
}
