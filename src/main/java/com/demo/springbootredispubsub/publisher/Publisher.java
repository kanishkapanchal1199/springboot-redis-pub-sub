package com.demo.springbootredispubsub.publisher;

import com.demo.springbootredispubsub.dto.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/publish")
public class Publisher {

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @Autowired
    private ChannelTopic topic;


    @PostMapping
    public String publish(@RequestBody Product product)
    {
        redisTemplate.convertAndSend(topic.getTopic(), product.toString());
        return "Event Published";
    }

}
