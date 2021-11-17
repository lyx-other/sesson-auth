package com.lyx.servide;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.lyx.model.AuthenticationRequest;
import com.lyx.model.UserDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@Service("authenticationServiceImpl")
public class AuthenticationServiceImpl implements AuthenticationService
{
    /**
     * 模拟数据库中的数据
     */
    private static final List<UserDto> userList = CollUtil.newArrayList();
    {
        Set<String> auth1 = CollUtil.newHashSet("p1"); // p1、p2 是权限标识符，p1和r/r1对应 p2和r/r2对应
        Set<String> auth2 = CollUtil.newHashSet("p2");

        userList.add(new UserDto("u1", "zs", "aabc", "张三", "15264482645", auth1));
        userList.add(new UserDto("u2", "ls", "a123", "李四", "17864482645", auth2));
    }

    /**
     * 用户认证方法
     * @param authenticationRequest 登录信息
     * @return 用户
     */
    @Override
    public UserDto authentication(AuthenticationRequest authenticationRequest)
    {
        // ①检查参数
        if (Objects.isNull(authenticationRequest) || StrUtil.isEmpty(authenticationRequest.getUsername()) || StrUtil.isEmpty(authenticationRequest.getPassword()))
        {
            throw new RuntimeException("用户名或密码为空");
        }

        // ②根据账号查询用户信息
        UserDto userDto = this.getUserDto(authenticationRequest.getUsername());
        if (Objects.isNull(userDto))
        {
            throw new RuntimeException("查询不到此账户");
        }

        // ③检查密码
        if (!StrUtil.equals(userDto.getPassword(), authenticationRequest.getPassword()))
        {
            throw new RuntimeException("密码不正确");
        }

        return userDto;
    }

    /**
     * 从数据库中查询用户
     * @param username 用户名
     * @return 查询的用户
     */
    private UserDto getUserDto(String username)
    {
        Optional<UserDto> userOpt = userList.stream().filter(el -> StrUtil.equals(el.getUsername(), username)).findFirst();
        if (userOpt.isPresent())
        {
            return userOpt.get();
        }

        return null;
    }
}
