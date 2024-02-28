//package com.dxhh.elearning.configs;
//
//import com.dxhh.elearning.pojos.Course;
//import org.springframework.context.annotation.Bean;
//import org.springframework.data.redis.connection.RedisConnectionFactory;
//import org.springframework.data.redis.core.RedisTemplate;
//
//public class RedisConfig {
//    @Bean
//    public RedisTemplate<Long, Course> redisTemplate(RedisConnectionFactory connectionFactory) {
//        RedisTemplate<Long, Course> template = new RedisTemplate<Long, Course>();
//        template.setConnectionFactory(connectionFactory);
//        // Add some specific configuration here. Key serializers, etc.
//        return template;
//    }
//}
