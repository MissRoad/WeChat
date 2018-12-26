package com.bing.utils.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.StringRedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Service;

@Service
public class RedisService {

  @Autowired
  private StringRedisTemplate redisTemplate;


  public void set(String key, String val) {
    redisTemplate.execute(new RedisCallback<Object>() {

      @Override
      public Object doInRedis(RedisConnection connection) throws DataAccessException {
        StringRedisConnection stringConnection = (StringRedisConnection) connection;
        stringConnection.set(key, val);
        return null;
      }
    });
  }

  public String get(String key) {
    return redisTemplate.execute(new RedisCallback<String>() {

      @Override
      public String doInRedis(RedisConnection connection) throws DataAccessException {
        StringRedisConnection stringConnection = (StringRedisConnection) connection;
        return stringConnection.get(key);
      }
    });
  }

  /**
   * 
   * @Description 指定的 key 设置值及其过期时间。如果 key 已经存在， SETEX 命令将会替换旧的值。
   * @author 罗选通
   * @date 2017年4月5日 上午10:27:17
   * @action setEx
   * @param key
   * @param seconds
   * @param value void
   */
  public void setEx(String key, long seconds, String value) {
    redisTemplate.execute(new RedisCallback<Object>() {
      @Override
      public Object doInRedis(RedisConnection connection) throws DataAccessException {
        StringRedisConnection stringConnection = (StringRedisConnection) connection;
        stringConnection.setEx(key, seconds, value);
        return null;
      }
    });
  }

  /**
   * 
   * @Description 将 key 中储存的数字值增一。如果 key 不存在，那么 key 的值会先被初始化为 0 ，然后再执行 INCR 操作
   * @author 罗选通
   * @date 2017年4月5日 上午10:23:21
   * @action setIncr
   * @param key
   * @return Long
   */
  public Long setIncr(String key) {
    return redisTemplate.execute(new RedisCallback<Long>() {
      @Override
      public Long doInRedis(RedisConnection connection) throws DataAccessException {
        StringRedisConnection stringConnection = (StringRedisConnection) connection;
        return stringConnection.incr(key);
      }
    });
  }

  /**
   * @Description 同步锁
   * @author 潘辉
   * @date 2017年3月15日 下午5:16:58
   * @action redisSynchronized
   * @return T
   */
  public <T> T redisSynchronized(String key, SampleCallback<T> action) {
    redisTemplate.watch(key);
    redisTemplate.multi();
    return redisTemplate.execute(new RedisCallback<T>() {
      @Override
      public T doInRedis(RedisConnection connection) throws DataAccessException {
        StringRedisSerializer keyKeySerializer =
            (StringRedisSerializer) redisTemplate.getKeySerializer();
        StringRedisSerializer valKeySerializer =
            (StringRedisSerializer) redisTemplate.getValueSerializer();
        byte[] serialize = keyKeySerializer.serialize(key);
        try {
          Boolean setNX =
              connection.setNX(keyKeySerializer.serialize(key), valKeySerializer.serialize("1"));
          if (setNX) {
            return action.excute();
          }
        } finally {
          connection.del(keyKeySerializer.serialize(key));
        }
        return null;
      }
    });
  }


  public Object del(final String... keys) {
    return redisTemplate.execute(new RedisCallback<Object>() {
      public Long doInRedis(RedisConnection connection) throws DataAccessException {
        long result = 0;
        for (int i = 0; i < keys.length; i++) {
          result = connection.del(keys[i].getBytes());
        }
        return result;
      }
    });
  }

}
