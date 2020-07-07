package kybmig.ssm.service;

import kybmig.ssm.Utility;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.stereotype.Service;


@Service
public class RedisSubscriber extends MessageListenerAdapter {
    private RedisTemplate<String, String> redisTemplate;

    public RedisSubscriber(RedisTemplate<String, String> template) {
        this.redisTemplate = template;
    }


//     // 接受到订阅的消息
//    @Override
//    public void onMessage(Message message, byte[] bytes) {
//        Utility.log("redis 订阅 (%s)", message.toString());
//    }
}
