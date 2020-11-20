package como.alibaba.blog_server.redis.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @ProjectName: teamblog
 * @Package: com.alibaba.blog_common.redis
 * @ClassName: RedisConfiig
 * @Author: 漫步
 * @Description:
 * @Date: 2020/11/19 5:54 下午
 * @Version: 1.0
 */
@Component
public class RedisConfiig {

    @Value("${rdb.host}")
    private String RDB_HOST;
    @Value("${rdb.port}")
    private Integer RDB_PORT;
    private JedisPool jedisPool;

    private static final Integer TIME_OUT = 3000;

    private static final Integer MAX_TOTAL = 1024;

    @Bean("redisClient")
    public Jedis getRedisClient() {
        if (jedisPool == null) {
            JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
            jedisPoolConfig.setMaxTotal(MAX_TOTAL);
            /*打印日志*/
            jedisPool = new JedisPool(jedisPoolConfig, RDB_HOST, RDB_PORT, TIME_OUT);
        }
        return jedisPool.getResource();
    }



}
