package com.alibaba.blog_common.pojo;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

/**
 * @ProjectName: teamblog
 * @Package: com.alibaba.blog_common.pojo
 * @ClassName: Student
 * @Author: 漫步
 * @Description:
 * @Date: 2020/11/11 11:32 上午
 * @Version: 1.0
 */
@Data
public class Student {
    private int id;
    private String name;
    private String password;
    private  String address;
    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
