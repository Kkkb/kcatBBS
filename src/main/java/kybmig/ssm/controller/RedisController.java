package kybmig.ssm.controller;

import kybmig.ssm.Utility;
import kybmig.ssm.mapper.TodoMapper;
import kybmig.ssm.model.TodoModel;
import kybmig.ssm.model.UserModel;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;


@Controller
public class RedisController {
    private final RedisTemplate<String, String> templateNormal;
    private RedisTemplate<Integer, TodoModel> templateTodo;
//    private ChannelTopic mailTopic;

    public RedisController(RedisTemplate<String, String> templateNormal, RedisTemplate<Integer, TodoModel> templateTodo) {
        this.templateNormal = templateNormal;
        this.templateTodo = templateTodo;
//        this.mailTopic = mailTopic;
    }

    @GetMapping("/redis/index")
    public ModelAndView index() {
        TodoModel m = new TodoModel();
        m.setId(1);
        m.setContent("testRedisTodo");
        templateTodo.opsForValue().set(m.getId(), m);
        TodoModel mGet = templateTodo.opsForValue().get(m.getId());
        Utility.log("mGet %s", mGet);


        var value = templateNormal.opsForValue().get("testkey");
        if (value == null) {
            value = "redis 中没有 testkey 的值";
        }
        var mv = new ModelAndView("redis/index");
        mv.addObject("key", value);
        return mv;
    }

    @GetMapping("/redis/set")
    public String set(String value) {
        if (value == null) {
            value = "kun";
        }

        templateNormal.opsForValue().set("testkey", value);
        templateNormal.convertAndSend("messageQueue", value);
//        templateNormal.convertAndSend(mailTopic.getTopic(), value);
        // localredis.put("testkey", value);
        return "redirect:/redis/index";
    }
}
