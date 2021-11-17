package com.lyx;

import cn.hutool.core.lang.Console;
import com.lyx.model.UserDto;

import java.util.HashMap;
import java.util.Map;

public class Main
{
    public static void main(String[] args)
    {
        Map<String, Object> map = new HashMap<>();

        UserDto res = (UserDto) map.get("a");

        Console.log("打印数据：{}", res);
    }
}
