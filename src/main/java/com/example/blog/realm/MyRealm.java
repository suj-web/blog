package com.example.blog.realm;

import com.example.blog.po.Blogger;
import com.example.blog.service.BloggerService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.SimpleAutowireCandidateResolver;

public class MyRealm extends AuthorizingRealm {
    @Autowired
    private BloggerService bloggerService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    //登录验证
    //token:令牌，基于用户名，密码的令牌
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //从令牌中取出用户名
        String username = (String)authenticationToken.getPrincipal();
        //让Shiro验证账号密码
        Blogger blogger = bloggerService.checkByUsername(username);
        if(blogger != null){
            SecurityUtils.getSubject().getSession().setAttribute("blogger",blogger);
            SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(blogger.getUsername(),blogger.getPassword(),getName());
            return simpleAuthenticationInfo;
        }
        return null;
    }
}
