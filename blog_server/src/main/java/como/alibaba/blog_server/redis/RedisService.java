package como.alibaba.blog_server.redis;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @ProjectName: teamblog
 * @Package: como.alibaba.blog_server.redis
 * @ClassName: RedisService
 * @Author: 漫步
 * @Description:
 * @Date: 2020/11/19 6:03 下午
 * @Version: 1.0
 */
public interface RedisService {
    Long append(String key,String value);

    String get(String key);

    Boolean set(String key, String value);

    Boolean setnx(String key, String value);

    String getSet(String key, String value);

    Boolean setex(String key, int seconds, String value);

    Boolean expire(String key, int seconds);

    Long del(String... key);

    Boolean exists(String key);

    Long hset(String key, String field, String value);

    String hget(String key, String field);

    Boolean hsetnx(String key, String field, String value);

    Boolean hexists(String key, String field);

    Long hdel(String key, String... fields);

    Long hlen(String key);

    Set<String> hkeys(String key);

    List<String> hvals(String key);

    Map<String, String> hgetAll(String key);

    Long rpush(String key, String... strings);

    Long lpush(String key, String... strings);

    Long llen(String key);

    List<String> lrange(String key, long start, long end);

    Boolean ltrim(String key, long start, long end);

    String lindex(String key, long index);

    Boolean lset(String key, long count, String value);

    Long lrem(String key, long count, String value);

    String lpop(String key,boolean isLog);

    String rpop(String key);

    Object eval(String script, List<String> keys, List<String> args);

    Long zadd(final String key, final double score, final String member);

    Set<String> zrange(final String key, final long start, final long end);

    Long zrem(final String key, final String... members);

    Long zcount(final String key, final String min, final String max);

    Long zrank(final String key, final String member);

    Set<String> zrevrangeByScore(final String key, final double max, final double min);

    Set<String> zrevrange(final String key, final long start, final long end);

    Boolean sadd(final String key, final String... members);

    Set<String> smembers(final String key);

    Boolean srem(final String key, final String... members);

    Long scard(final String key);

    Boolean sismember(final String key, final String member);

    Long incr(String key);
}
