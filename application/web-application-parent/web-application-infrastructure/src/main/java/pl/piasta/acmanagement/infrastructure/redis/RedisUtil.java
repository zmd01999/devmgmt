package pl.piasta.acmanagement.infrastructure.redis;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;


import javax.annotation.Resource;
import java.util.Set;

@Component
public class RedisUtil {

    @Resource
    private RedisTemplate<Object,Object> redisTemplate;

    @Resource(name = "redisTemplate")
    ValueOperations<Object, Object> objValueOperations;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource(name = "stringRedisTemplate")
    ValueOperations<String, String> stringValueOperations;

    public void setStr(String key, String value){
        stringValueOperations.set(key, value);
    }

    public void setObject(String key, Object obj){
        objValueOperations.set(key, obj);
    }

    public String getStr(String key){
        return stringValueOperations.get(key);
    }

    public Object getObject(String key){
        return objValueOperations.get(key);
    }

    public Set<String> getAllObject(String fuzzyKey) {
        return stringRedisTemplate.keys(fuzzyKey);
    }
    /**
     * 查询key是否存在
     * @param key
     * @return
     */
    public boolean exists(String key) {
        return Boolean.TRUE.equals(redisTemplate.hasKey(key));
    }

}