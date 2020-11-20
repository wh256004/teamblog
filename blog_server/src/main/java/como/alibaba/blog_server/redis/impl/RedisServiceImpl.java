package como.alibaba.blog_server.redis.impl;

import com.alibaba.blog_common.LogUtil;
import com.alibaba.blog_common.redis.RedisManager;
import como.alibaba.blog_server.redis.RedisService;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.curator.utils.CloseableUtils;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import redis.clients.jedis.Jedis;
import com.google.common.base.Preconditions;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @ProjectName: teamblog
 * @Package: como.alibaba.blog_server.redis.impl
 * @ClassName: RedisServiceImpl
 * @Author: 漫步
 * @Description:
 * @Date: 2020/11/19 6:04 下午
 * @Version: 1.0
 */
@Service
public class RedisServiceImpl implements RedisService {
    private static final Logger LOGGER = LogUtil.createLogger();
    @Autowired
    RedisConfiig redisConfiig;
    /**
     * 正常执行 redis command 返回的数字结果
     */
    private static final Long REDIS_LONG_RESULT = 1L;

    /**
     * 正常执行 redis command 返回的字符串结果
     */
    private static final String REDIS_STRING_RESULT = "OK";

    @Autowired
    private RedisManager redisManager;

    @Override
    public Long append(String key, String value) {
        Jedis jedis = redisManager.getRedisClient();
        checkJedisNotNull(jedis);
        try {
            logInfo(jedis, "append", key);
            return jedis.append(key,value);
        } finally {
            CloseableUtils.closeQuietly(jedis);
        }


    }

    public String get(String key) {
        checkStringNotEmpty(key);

        Jedis jedis = redisManager.getRedisClient();
        checkJedisNotNull(jedis);

        try {
            logInfo(jedis, "get", key);
            return jedis.get(key);
        } finally {
            CloseableUtils.closeQuietly(jedis);
        }
    }

    public Boolean set(String key, String value) {
        checkStringNotEmpty(key);
        checkStringNotEmpty(value);

        Jedis jedis = redisManager.getRedisClient();
        checkJedisNotNull(jedis);

        try {
            logInfo(jedis, "set", key, value);
            String result = jedis.set(key, value);

            return isOK(result);
        } finally {
            CloseableUtils.closeQuietly(jedis);
        }
    }

    public Long incr(String key) {
        checkStringNotEmpty(key);

        Jedis jedis = redisManager.getRedisClient();
        checkJedisNotNull(jedis);

        try {
            logInfo(jedis, "incr", key);
            return jedis.incr(key);
        } finally {
            CloseableUtils.closeQuietly(jedis);
        }
    }

    @Override
    public Boolean setnx(String key, String value) {
        checkStringNotEmpty(key);
        checkStringNotEmpty(value);

        Jedis jedis = redisManager.getRedisClient();
        checkJedisNotNull(jedis);

        try {
            logInfo(jedis, "setnx", key, value);
            Long result = jedis.setnx(key, value);
            return isOK(result);
        } finally {
            CloseableUtils.closeQuietly(jedis);
        }
    }


    @Override
    public String getSet(String key, String value) {
        checkStringNotEmpty(key);
        checkStringNotEmpty(value);

        Jedis jedis = redisManager.getRedisClient();
        checkJedisNotNull(jedis);

        try {
            logInfo(jedis, "getSet", key, value);
            return jedis.getSet(key, value);
        } finally {
            CloseableUtils.closeQuietly(jedis);
        }
    }

    public Boolean setex(String key, int seconds, String value) {
        checkStringNotEmpty(key);
        checkNumberNotNegative(seconds);
        checkStringNotEmpty(value);

        Jedis jedis = redisManager.getRedisClient();
        checkJedisNotNull(jedis);

        try {
            logInfo(jedis, "setex", key, value);
            String result = jedis.setex(key, seconds, value);
            return isOK(result);
        } finally {
            CloseableUtils.closeQuietly(jedis);
        }

    }

    /**
     * @param key
     * @param seconds
     * @return Boolean reply, specifically: true: the timeout was set. false: the
     * timeout was not set since the key already has an associated
     * timeout (this may happen only in Redis versions < 2.1.3, Redis >=
     * 2.1.3 will happily update the timeout), or the key does not
     * exist.
     */
    public Boolean expire(String key, int seconds) {
        checkStringNotEmpty(key);
        checkNumberNotNegative(seconds);

        Jedis jedis = redisManager.getRedisClient();
        checkJedisNotNull(jedis);

        try {
            logInfo(jedis, "expire", key);
            Long result = jedis.expire(key, seconds);
            return isOK(result);
        } finally {
            CloseableUtils.closeQuietly(jedis);
        }

    }

    /**
     * Remove the specified keys. If a given key does not exist no operation is
     * performed for this key. The command returns the number of keys removed.
     * <p/>
     * Time complexity: O(1)
     *
     * @param key
     * @return Integer reply, specifically: an integer greater than 0 if one or
     * more keys were removed 0 if none of the specified key existed
     */
    public Long del(String... key) {
        checkArrayNotEmpty(key);

        Jedis jedis = redisManager.getRedisClient();
        checkJedisNotNull(jedis);

        try {
            logInfo(jedis, "del", key.toString());
            return jedis.del(key);
        } finally {
            CloseableUtils.closeQuietly(jedis);
        }
    }

    @Override
    public Boolean exists(String key) {
        checkStringNotEmpty(key);

        Jedis jedis = redisManager.getRedisClient();
        checkJedisNotNull(jedis);

        try {
            logInfo(jedis, "exists", key);
            return jedis.exists(key);
        } finally {
            CloseableUtils.closeQuietly(jedis);
        }
    }

    /**
     * @param key
     * @param field
     * @param value
     * @return If the field already exists, and the HSET just produced an update
     * of the value, 0 is returned, otherwise if a new field is created
     * 1 is returned.
     */
    @Override
    public Long hset(String key, String field, String value) {
        checkStringNotEmpty(key);
        checkStringNotEmpty(field);
        checkStringNotEmpty(value);

        Jedis jedis = redisManager.getRedisClient();
        checkJedisNotNull(jedis);

        try {
            logInfo(jedis, "hset", key, field, value);
            return jedis.hset(key, field, value);
        } finally {
            CloseableUtils.closeQuietly(jedis);
        }
    }

    @Override
    public String hget(String key, String field) {
        checkStringNotEmpty(key);
        checkStringNotEmpty(field);

        Jedis jedis = redisManager.getRedisClient();
        checkJedisNotNull(jedis);

        try {
            logInfo(jedis, "hget", key, field, "");
            return jedis.hget(key, field);
        } finally {
            CloseableUtils.closeQuietly(jedis);
        }
    }

    @Override
    public Boolean hsetnx(String key, String field, String value) {
        checkStringNotEmpty(key);
        checkStringNotEmpty(field);
        checkStringNotEmpty(value);

        Jedis jedis = redisManager.getRedisClient();
        checkJedisNotNull(jedis);

        try {
            logInfo(jedis, "hsetnx", key, field, value);
            Long result = jedis.hsetnx(key, field, value);
            return isOK(result);
        } finally {
            CloseableUtils.closeQuietly(jedis);
        }
    }

    @Override
    public Boolean hexists(String key, String field) {
        checkStringNotEmpty(key);
        checkStringNotEmpty(field);

        Jedis jedis = redisManager.getRedisClient();
        checkJedisNotNull(jedis);

        try {
            logInfo(jedis, "hexists", key, field, "");
            return jedis.hexists(key, field);
        } finally {
            CloseableUtils.closeQuietly(jedis);
        }
    }

    @Override
    public Long hdel(String key, String... fields) {
        checkStringNotEmpty(key);
        checkArrayNotEmpty(fields);

        Jedis jedis = redisManager.getRedisClient();
        checkJedisNotNull(jedis);

        try {
            logInfo(jedis, "hdel", key, fields.toString(), "");
            return jedis.hdel(key, fields);
        } finally {
            CloseableUtils.closeQuietly(jedis);
        }
    }

    @Override
    public Long hlen(String key) {
        checkStringNotEmpty(key);

        Jedis jedis = redisManager.getRedisClient();
        checkJedisNotNull(jedis);

        try {
            logInfo(jedis, "hlen", key);
            return jedis.hlen(key);
        } finally {
            CloseableUtils.closeQuietly(jedis);
        }
    }

    @Override
    public Set<String> hkeys(String key) {
        checkStringNotEmpty(key);

        Jedis jedis = redisManager.getRedisClient();
        checkJedisNotNull(jedis);

        try {
            logInfo(jedis, "hkeys", key);
            return jedis.hkeys(key);
        } finally {
            CloseableUtils.closeQuietly(jedis);
        }
    }

    @Override
    public List<String> hvals(String key) {
        checkStringNotEmpty(key);

        Jedis jedis = redisManager.getRedisClient();
        checkJedisNotNull(jedis);

        try {
            logInfo(jedis, "hvals", key);
            return jedis.hvals(key);
        } finally {
            CloseableUtils.closeQuietly(jedis);
        }
    }

    @Override
    public Map<String, String> hgetAll(String key) {
        checkStringNotEmpty(key);

        Jedis jedis = redisManager.getRedisClient();
        checkJedisNotNull(jedis);

        try {
            logInfo(jedis, "hgetAll", key);
            return jedis.hgetAll(key);
        } finally {
            CloseableUtils.closeQuietly(jedis);
        }
    }

    @Override
    public Long rpush(String key, String... strings) {
        checkStringNotEmpty(key);
        checkArrayNotEmpty(strings);

        Jedis jedis = redisManager.getRedisClient();
        checkJedisNotNull(jedis);

        try {
            logInfo(jedis, "rpush", key);
            return jedis.rpush(key, strings);
        } finally {
            CloseableUtils.closeQuietly(jedis);
        }
    }

    @Override
    public Long lpush(String key, String... strings) {
        checkStringNotEmpty(key);
        checkArrayNotEmpty(strings);

        Jedis jedis = redisManager.getRedisClient();
        checkJedisNotNull(jedis);

        try {
            logInfo(jedis, "lpush", key);
            return jedis.lpush(key, strings);
        } finally {
            CloseableUtils.closeQuietly(jedis);
        }
    }

    @Override
    public Long llen(String key) {
        checkStringNotEmpty(key);

        Jedis jedis = redisManager.getRedisClient();
        checkJedisNotNull(jedis);

        try {
            logInfo(jedis, "llen", key);
            return jedis.llen(key);
        } finally {
            CloseableUtils.closeQuietly(jedis);
        }
    }

    @Override
    public List<String> lrange(String key, long start, long end) {
        checkStringNotEmpty(key);

        Jedis jedis = redisManager.getRedisClient();
        checkJedisNotNull(jedis);

        try {
            logInfo(jedis, "lrange", key);
            return jedis.lrange(key, start, end);
        } finally {
            CloseableUtils.closeQuietly(jedis);
        }
    }

    @Override
    public Boolean ltrim(String key, long start, long end) {
        checkStringNotEmpty(key);

        Jedis jedis = redisManager.getRedisClient();
        checkJedisNotNull(jedis);

        try {
            logInfo(jedis, "ltrim", key);
            String result = jedis.ltrim(key, start, end);
            return isOK(result);
        } finally {
            CloseableUtils.closeQuietly(jedis);
        }
    }

    @Override
    public String lindex(String key, long index) {
        checkStringNotEmpty(key);

        Jedis jedis = redisManager.getRedisClient();
        checkJedisNotNull(jedis);

        try {
            logInfo(jedis, "lindex", key);
            return jedis.lindex(key, index);
        } finally {
            CloseableUtils.closeQuietly(jedis);
        }
    }

    @Override
    public Boolean lset(String key, long count, String value) {
        checkStringNotEmpty(key);

        Jedis jedis = redisManager.getRedisClient();
        checkJedisNotNull(jedis);

        try {
            logInfo(jedis, key, value);
            String result = jedis.lset(key, count, value);
            return isOK(result);
        } finally {
            CloseableUtils.closeQuietly(jedis);
        }
    }

    /**
     * Remove the first count occurrences of the value element from the list. If
     * count is zero all the elements are removed. If count is negative elements
     * are removed from tail to head, instead to go from head to tail that is
     * the normal behaviour. So for example LREM with count -2 and hello as
     * value to remove against the list (a,b,c,hello,x,hello,hello) will lave
     * the list (a,b,c,hello,x). The number of removed elements is returned as
     * an integer, see below for more information about the returned value. Note
     * that non existing keys are considered like empty lists by LREM, so LREM
     * against non existing keys will always return 0.
     * <p/>
     * Time complexity: O(N) (with N being the length of the list)
     *
     * @param key
     * @param count
     * @param value
     * @return Integer Reply, specifically: The number of removed elements if
     * the operation succeeded
     */
    @Override
    public Long lrem(String key, long count, String value) {
        checkStringNotEmpty(key);
        checkNumberNotNegative(count);
        checkStringNotEmpty(value);

        Jedis jedis = redisManager.getRedisClient();
        checkJedisNotNull(jedis);

        try {
            logInfo(jedis, "lrem", key, value);
            return jedis.lrem(key, count, value);
        } finally {
            CloseableUtils.closeQuietly(jedis);
        }
    }

    @Override
    public String lpop(String key,boolean isLog) {
        checkStringNotEmpty(key);

        Jedis jedis = redisManager.getRedisClient();
        checkJedisNotNull(jedis);

        try {
            if (isLog) {
                logInfo(jedis, "lpop", key);
            }
            return jedis.lpop(key);
        } finally {
            CloseableUtils.closeQuietly(jedis);
        }
    }

    @Override
    public String rpop(String key) {
        checkStringNotEmpty(key);

        Jedis jedis = redisManager.getRedisClient();
        checkJedisNotNull(jedis);

        try {
            logInfo(jedis, "rpop", key);
            return jedis.rpop(key);
        } finally {
            CloseableUtils.closeQuietly(jedis);
        }
    }

    @Override
    public Object eval(String script, List<String> keys, List<String> args) {
        checkStringNotEmpty(script);
        checkListNotEmpty(keys);
        checkListNotEmpty(args);

        Jedis jedis = redisManager.getRedisClient();
        checkJedisNotNull(jedis);

        try {
            logInfo(jedis, "eval", keys.toString(), args.toString());
            return jedis.eval(script, keys, args);
        } finally {
            CloseableUtils.closeQuietly(jedis);
        }
    }

    @Override
    public Long zadd(String key, double score, String member) {
        checkStringNotEmpty(key);
        checkNumberNotNegative(score);
        checkStringNotEmpty(member);
        Jedis jedis = redisManager.getRedisClient();
        checkJedisNotNull(jedis);
        logInfo(jedis, "zadd", key);
        try {
            return jedis.zadd(key, score, member);
        } finally {
            CloseableUtils.closeQuietly(jedis);
        }
    }

    @Override
    public Set<String> zrange(String key, long start, long end) {
        checkStringNotEmpty(key);
        Jedis jedis = redisManager.getRedisClient();
        checkJedisNotNull(jedis);
        logInfo(jedis, "zrange", key);
        try {
            return jedis.zrange(key, start, end);
        } finally {
            CloseableUtils.closeQuietly(jedis);
        }
    }

    @Override
    public Long zrem(String key, String... members) {
        checkStringNotEmpty(key);
        Jedis jedis = redisManager.getRedisClient();
        checkJedisNotNull(jedis);
        logInfo(jedis, "zrem", key);
        try {
            return jedis.zrem(key, members);
        } finally {
            CloseableUtils.closeQuietly(jedis);
        }
    }

    @Override
    public Long zcount(String key, String min, String max) {
        checkStringNotEmpty(key);
        checkStringNotEmpty(min);
        checkStringNotEmpty(max);
        Jedis jedis = redisManager.getRedisClient();
        checkJedisNotNull(jedis);
        logInfo(jedis, "zcount", key);
        try {
            return jedis.zcount(key, min, max);
        } finally {
            CloseableUtils.closeQuietly(jedis);
        }
    }

    @Override
    public Long zrank(String key, String member) {
        checkStringNotEmpty(key);
        checkStringNotEmpty(member);
        Jedis jedis = redisManager.getRedisClient();
        checkJedisNotNull(jedis);
        logInfo(jedis, "zrank", key);
        try {
            return jedis.zrank(key, member);
        } finally {
            CloseableUtils.closeQuietly(jedis);
        }
    }

    @Override
    public Set<String> zrevrangeByScore(String key, double max, double min) {
        checkStringNotEmpty(key);
        checkNumberNotNegative(max);
        checkNumberNotNegative(min);
        Jedis jedis = redisManager.getRedisClient();
        checkJedisNotNull(jedis);
        logInfo(jedis, "zrevrangeByScore", key);
        try {
            return jedis.zrevrangeByScore(key, max, min);
        } finally {
            CloseableUtils.closeQuietly(jedis);
        }
    }

    @Override
    public Set<String> zrevrange(String key, long start, long end) {
        checkStringNotEmpty(key);
        Jedis jedis = redisManager.getRedisClient();
        checkJedisNotNull(jedis);
        logInfo(jedis, "zrevrange", key);
        try {
            return jedis.zrevrange(key, start, end);
        } finally {
            CloseableUtils.closeQuietly(jedis);
        }
    }

    /**
     * Add the specified member to the set value stored at key. If member is
     * already a member of the set no operation is performed. If key does not
     * exist a new set with the specified member as sole member is created. If
     * the key exists but does not hold a set value an error is returned.
     * <p/>
     * Time complexity O(1)
     *
     * @param key
     * @param members
     * @return boolean reply, specifically: true if the new element was added,
     * false if the element was already a member of the set
     */
    @Override
    public Boolean sadd(String key, String... members) {
        checkStringNotEmpty(key);
        checkArrayNotEmpty(members);
        Jedis jedis = redisManager.getRedisClient();
        checkJedisNotNull(jedis);
        logInfo(jedis, "sadd", key);

        try {
            Long result = jedis.sadd(key, members);
            return result.equals((long) members.length);
        } finally {
            CloseableUtils.closeQuietly(jedis);
        }
    }

    /**
     * Return all the members (elements) of the set value stored at key.
     * <p/>
     * Time complexity O(N)
     *
     * @param key
     * @return set
     */
    @Override
    public Set<String> smembers(String key) {
        checkStringNotEmpty(key);
        Jedis jedis = redisManager.getRedisClient();
        checkJedisNotNull(jedis);
        logInfo(jedis, "smembers", key);

        try {
            return jedis.smembers(key);
        } finally {
            CloseableUtils.closeQuietly(jedis);
        }
    }

    /**
     * Remove the specified member from the set value stored at key. If member
     * was not a member of the set no operation is performed. If key does not
     * hold a set value an error is returned.
     * <p/>
     * Time complexity O(1)
     *
     * @param key
     * @param members
     * @return Boolean reply, specifically: true if the new element was removed false
     * if the new element was not a member of the set
     */
    @Override
    public Boolean srem(String key, String... members) {
        checkStringNotEmpty(key);
        checkArrayNotEmpty(members);
        Jedis jedis = redisManager.getRedisClient();
        checkJedisNotNull(jedis);
        logInfo(jedis, "srem", key);

        try {
            Long result = jedis.srem(key, members);
            return result.equals((long) members.length);
        } finally {
            CloseableUtils.closeQuietly(jedis);
        }
    }

    /**
     * Return the set cardinality (number of elements). If the key does not
     * exist 0 is returned, like for empty sets.
     *
     * @param key
     * @return Integer reply, specifically: the cardinality (number of elements)
     * of the set as an integer.
     */
    @Override
    public Long scard(String key) {
        checkStringNotEmpty(key);
        Jedis jedis = redisManager.getRedisClient();
        checkJedisNotNull(jedis);
        logInfo(jedis, "scard", key);

        try {
            return jedis.scard(key);
        } finally {
            CloseableUtils.closeQuietly(jedis);
        }
    }

    /**
     * Return true if member is a member of the set stored at key, otherwise false is
     * returned.
     * <p/>
     * Time complexity O(1)
     *
     * @param key
     * @param member
     * @return
     */
    @Override
    public Boolean sismember(String key, String member) {
        checkStringNotEmpty(key);
        checkStringNotEmpty(member);
        Jedis jedis = redisManager.getRedisClient();
        checkJedisNotNull(jedis);
        logInfo(jedis, "sismember", key);

        try {
            return jedis.sismember(key, member);
        } finally {
            CloseableUtils.closeQuietly(jedis);
        }
    }

    private void checkJedisNotNull(Jedis jedis) {
        Preconditions.checkNotNull(jedis, "get jedis client failed!");
    }

    /**
     * 验证 key 是否合法
     *
     * @param param
     * @return true:合法|false:不合法
     */
    private void checkStringNotEmpty(String param) {
        Preconditions.checkArgument(StringUtils.isNotEmpty(param), "Param must not be empty!");
    }

    /**
     * 验证 value 是否合法
     *
     * @param fields
     */
    private void checkArrayNotEmpty(String... fields) {
        Preconditions.checkArgument(!ArrayUtils.isEmpty(fields), "Param fields must not be an empty array!");
    }

    private void checkNumberNotNegative(Number number) {
        Preconditions.checkArgument(number.intValue() >= 0, "Param must greater or equal to 0!");
    }

    private void checkListNotEmpty(List<?> list) {
        Preconditions.checkArgument(!CollectionUtils.isEmpty(list), "Param list must not be an empty list!");
    }

    /**
     * 验证redis命令返回结果
     *
     * @param re
     */
    private boolean isOK(String re) {
        return StringUtils.equals(REDIS_STRING_RESULT, StringUtils.upperCase(re));
    }

    /**
     * 验证redis命令返回结果
     *
     * @param re
     */
    private boolean isOK(Long re) {
        return REDIS_LONG_RESULT.equals(re);
    }

    private void logInfo(Jedis jedis, String command, String key) {
        try {
            LOGGER.info("INVOKE REDIS:[host:{}, port:{}] with command:{}, key:{}", jedis.getClient().getHost(),
                    jedis.getClient().getPort(), command, key);
        } catch (Exception e) {
            LOGGER.warn("log.info exception", e);
        }
    }

    private void logInfo(Jedis jedis, String command, String key, String value) {
        try {
            LOGGER.info("INVOKE REDIS:[host:{}, port:{}] with command:{}, key:{}, value:{}", jedis.getClient().getHost(),
                    jedis.getClient().getPort(), command, key, value);
        } catch (Exception e) {
            LOGGER.warn("log.info exception", e);
        }
    }

    private void logInfo(Jedis jedis, String command, String key, String field, String value) {
        try {
            LOGGER.info("INVOKE REDIS:[host:{}, port:{}] with command:{}, key:{}, field:{}, value:{}", jedis.getClient().getHost(),
                    jedis.getClient().getPort(), command, key, field, value);
        } catch (Exception e) {
            LOGGER.warn("log.info exception", e);
        }
    }
}
