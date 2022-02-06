package pl.piasta.acmanagement.infrastructure.redis;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.rmi.UnknownHostException;

@Configuration
public class RedisConfig {
        /**
         * redis默认使用jdk的二进制数据来序列化
         * 以下自定义使用jackson来序列化
         *
         * @param redisConnectionFactory
         * @return
         * @throws UnknownHostException
         */
        @Bean
        @SuppressWarnings("all")
        public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
            RedisTemplate<Object, Object> template = new RedisTemplate<Object, Object>();
            template.setConnectionFactory(redisConnectionFactory);

            /* 序列化10000个对象数据,在Redis 所占用空间
             * 根据最终测试, String 和 FastJson 占用较少
             * */
//        Jackson2JsonRedisSerializer serializer = new Jackson2JsonRedisSerializer(Object.class);//2.5M,若开启类型检测是2.96M
//        StringRedisSerializer serializer = new StringRedisSerializer();//2.33M
//        FastJsonRedisSerializer serializer = new FastJsonRedisSerializer(Object.class);//2.35M
//        KryoRedisSerializer serializer = new KryoRedisSerializer(Object.class);//2.35M
            MsgpackRedisSerializer serializer = new MsgpackRedisSerializer();//2.96M

//        ObjectMapper om = new ObjectMapper();
//        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
//        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
//        serializer.setObjectMapper(om);

            template.setKeySerializer(new StringRedisSerializer()); //1
            template.setValueSerializer(serializer); //2

            template.setHashKeySerializer(new StringRedisSerializer());
            template.setHashValueSerializer(serializer);

            //开启事务支持
            template.setEnableTransactionSupport(true);

            template.afterPropertiesSet();
            return template;
        }

}