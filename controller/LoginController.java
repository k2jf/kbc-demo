package com.k2data.kbc.login.controller;

import com.k2data.kbc.api.KbcBizException;
import com.k2data.kbc.api.KbcResponse;
import com.k2data.kbc.login.entity.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class LoginController {

    //TODO 演示方便改为get请求，后期改为post请求
    @GetMapping("/login")
    public KbcResponse login(HttpServletRequest request,String userName,String password) throws KbcBizException {
        if(userName == null || userName.isEmpty()){
            throw new KbcBizException("请输入用户名");
        }
        if(password == null || password.isEmpty()){
            throw new KbcBizException("请输入密码");
        }
        boolean valid = false;
        // TODO 引入用户(User)组件,调用service，验证用户名密码
        User user=null;
        if(userName.equals("admin")&&password.equals("admin")){
            user = new User();
            user.setUserName(userName);
            user.setPassword(password);
            valid=true;
        }
        if(valid){
            request.getSession().setAttribute("user",user);
        }else {
            throw new KbcBizException("用户名密码或密码错误");
        }
        return KbcResponse.SUCCESS;
    }
}
