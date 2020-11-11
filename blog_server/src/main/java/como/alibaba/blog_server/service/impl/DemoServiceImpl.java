package como.alibaba.blog_server.service.impl;

import com.alibaba.blog_common.pojo.Student;
import com.alibaba.blog_db.dao.DemoServiceDao;
import como.alibaba.blog_server.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
@Service
public class DemoServiceImpl implements DemoService {

    @Autowired
    DemoServiceDao demoServiceDao;

    @Override
    public List<Student> all() {
        List<Student> all = demoServiceDao.all();
        return demoServiceDao.all();
    }
}
