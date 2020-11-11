package como.alibaba.blog_server.service;

import com.alibaba.blog_common.pojo.Student;

import java.util.List;

/**
 * @ProjectName: teamblog
 * @Package: como.alibaba.blog_server.service
 * @ClassName: DemoService
 * @Author: 漫步
 * @Description:
 * @Date: 2020/11/11 11:37 上午
 * @Version: 1.0
 */
public interface DemoService {
    List<Student> all();
}
