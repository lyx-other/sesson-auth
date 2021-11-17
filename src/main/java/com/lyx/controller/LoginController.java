package com.lyx.controller;

import cn.hutool.core.lang.Console;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.ContentType;
import com.lyx.model.AuthenticationRequest;
import com.lyx.model.UserDto;
import com.lyx.servide.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Objects;

@RestController
public class LoginController
{
    @Autowired
    @Qualifier("authenticationServiceImpl")
    private AuthenticationService authenticationService;

    /**
     * 登录控制器
     * @param request 请求报文
     * @param session session
     * @return 响应微信
     */
    @PostMapping(value = "/login", produces = "text/plain;charset=utf-8")
    public String login(AuthenticationRequest request, HttpSession session)
    {
        Console.log("打印数据：{}", session.getClass().getName());

        UserDto userDto = authenticationService.authentication(request);

        session.setAttribute(UserDto.SESSION_USER_KEY, userDto); // 在 session 中放入用户对象

        return StrUtil.format("{} 登录成功", userDto.getUsername());
    }

    @GetMapping(value = "/logout", produces = "text/plain;charset=utf-8")
    public String logout(HttpServletRequest request)
    {
        HttpSession session = request.getSession(true);

        session.invalidate(); // 调用invalidate()方法使用session失效

        return "退出登录成功";
    }

    @GetMapping(value = "/r/r1", produces = "text/plain;charset=utf-8")
    public String r1(HttpSession session)
    {
        Object user = session.getAttribute(UserDto.SESSION_USER_KEY);
        if (Objects.isNull(user)) // 在session中获取用户，获取不到的话说明用户退出了登录
        {
            return "用户退出了登录，不可以访问资源.";
        }
        else
        {
            String username = ((UserDto) user).getUsername();
            return StrUtil.format("成功访问资源r1.", username);
        }
    }

    @GetMapping(value = "/r/r2", produces = "text/plain;charset=utf-8")
    public String r2(HttpSession session)
    {
        Object user = session.getAttribute(UserDto.SESSION_USER_KEY);
        if (Objects.isNull(user)) // 在session中获取用户，获取不到的话说明用户退出了登录
        {
            return "用户退出了登录，不可以访问资源.";
        }
        else
        {
            String username = ((UserDto) user).getUsername();
            return StrUtil.format("成功访问资源r2.", username);
        }
    }
}
