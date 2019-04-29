package com.k2data.kbc.login.controller;

import com.k2data.kbc.api.KbcBizException;
import com.k2data.kbc.api.KbcResponse;
import com.k2data.kbc.auth.service.UsrmgrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class LoginController {

    @Autowired
    UsrmgrService usrmgrService;

    //TODO 演示方便改为get请求，后期改为post请求
    @GetMapping("/login")
    public KbcResponse login(HttpServletRequest request,String userName,String password) throws KbcBizException {
        if(userName == null || userName.isEmpty()){
            throw new KbcBizException("请输入用户名");
        }
        if(password == null || password.isEmpty()){
            throw new KbcBizException("请输入密码");
        }
        boolean valid = usrmgrService.validateUserPassword(userName,password);

        if(valid){
            request.getSession().setAttribute("validFlag",valid);
        }else {
            throw new KbcBizException("用户名密码或密码错误");
        }
        return KbcResponse.SUCCESS;
    }
}
