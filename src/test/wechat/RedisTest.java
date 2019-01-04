package wechat;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

@Slf4j
public class RedisTest extends SpringTest {

    @Autowired
    StringRedisTemplate redisTemplate;

    @Test
    public void getKey() {
        redisTemplate.opsForValue().set("test", "hello word!");
        String test = redisTemplate.opsForValue().get("test");
        log.info(test);
    }

}
