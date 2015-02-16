package com.etiennek.ws.topic;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;


@Service
public class TopicManager {

  private StringRedisTemplate redisTemplate;

  @Autowired
  public TopicManager(StringRedisTemplate redisTemplate) {
    this.redisTemplate = redisTemplate;
  }

  public void addSubscriber(String topicName, String subscriberName) {
    redisTemplate.opsForZSet()
                 .add(keyTopicSubscribers(topicName), subscriberName, System.currentTimeMillis());
  }

  public Set<String> getSubscribers(String topicName) {
    return redisTemplate.opsForZSet()
                        .range(keyTopicSubscribers(topicName), 0, -1);
  }

  private String keyTopicSubscribers(String topicName) {
    return "etiennek:topics:" + topicName + ":subscribers";
  }

}
