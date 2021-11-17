package com.lyx.interceptor;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.lyx.model.UserDto;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * 控制授权
 * 认证与授权中的授权
 */
@Component("simpleAuthenticationInterceptor")
public class SimpleAuthenticationInterceptor implements HandlerInterceptor
{
    // 需要认证的资源url
    private static final List<String> authUrl = CollUtil.newArrayList("/r/r1", "/r/r2");

    // 调用所有controller之前调用这个方法
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
    {
        // 在这个方法中校验用户请求的url是滞在用户的权限范围内

        // ①获得用户请求的url
        String requestURI = request.getRequestURI();

        // ②获得登录的用户的权限
        UserDto user = (UserDto) request.getSession().getAttribute(UserDto.SESSION_USER_KEY);
        if (Objects.isNull(user))
        {
            this.writeContent(response, "尚未登录，请登录.");
            return false;
        }
        Set<String> authorities = user.getAuthorities();

        // ③根据url与用户权限，判断可以不可以访问资源.
        if ((authorities.contains("p1") && requestURI.contains("/r/r1")) || (authorities.contains("p2") && requestURI.contains("/r/r2")))
        {
            return true;
        }
        this.writeContent(response, "没有权限，拒绝访问.");
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception
    {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception
    {

    }

    /**
     * 响应信息给客户端
     * @param response 响应报文
     * @param msg      响应数据
     */
    private void writeContent(HttpServletResponse response, String msg)
    {
        try
        {
            response.reset();
            response.setContentType(StrUtil.format("{};charset=utf-8", MediaType.TEXT_HTML_VALUE));
            PrintWriter writer = response.getWriter();
            writer.println(msg);
            writer.close();
        }
        catch (IOException e)
        {
            throw new RuntimeException(e.getMessage());
        }
    }
}
