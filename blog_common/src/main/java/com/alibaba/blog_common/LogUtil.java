package com.alibaba.blog_common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ProjectName: teamblog
 * @Package: com.alibaba.blog_common
 * @ClassName: LogUtil
 * @Author: 漫步
 * @Description:
 * @Date: 2020/11/20 2:31 下午
 * @Version: 1.0
 */
public class LogUtil {
    public static Logger createLogger() {
        StackTraceElement stackTraceElement = new Throwable().getStackTrace()[1];
        return LoggerFactory.getLogger(stackTraceElement.getClassName());
    }
}
