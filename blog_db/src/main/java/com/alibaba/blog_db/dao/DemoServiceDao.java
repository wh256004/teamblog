package com.alibaba.blog_db.dao;

import com.alibaba.blog_common.pojo.Student;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ProjectName: teamblog
 * @Package: com.alibaba.blog_db.dao
 * @ClassName: DemoServiceDao
 * @Author: 漫步
 * @Description:
 * @Date: 2020/11/11 11:39 上午
 * @Version: 1.0
 */
@Repository
@Mapper
public interface DemoServiceDao {
    List<Student> all();

}
