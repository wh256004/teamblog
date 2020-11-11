package com.alibaba.blog_module.controller.demo;

import com.alibaba.blog_common.pojo.Student;
import como.alibaba.blog_server.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ProjectName: teamblog
 * @Package: com.alibaba.blog_module.controller
 * @ClassName: DemoController
 * @Author: 漫步
 * @Description:
 * @Date: 2020/11/11 11:30 上午
 * @Version: 1.0
 */
@RequestMapping("/demo")
@RestController
public class DemoController {
    @Autowired
    DemoService demoService;

    @RequestMapping("/get")
    @ResponseBody
    public List<Student> tedt(){

        List<Student> all = demoService.all();
        return demoService.all();
    }
}
