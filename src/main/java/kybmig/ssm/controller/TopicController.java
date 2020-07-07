package kybmig.ssm.controller;

import kybmig.ssm.Utility;
import kybmig.ssm.model.TopicModel;
import kybmig.ssm.model.UserModel;
import kybmig.ssm.service.TopicService;
import kybmig.ssm.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Controller
public class TopicController {

    private TopicService topicService;
    private UserService userService;

    HashMap<String, String> tokenMap = new HashMap<>();

    public TopicController(TopicService topicService, UserService userService) {
        this.topicService = topicService;
        this.userService = userService;
    }

    @GetMapping("/")
    public ModelAndView indexType(String tab, HttpServletRequest request) {
        List<TopicModel> allTopicsByTab = topicService.findTopicByType(tab);
        Integer numberOfPages = allTopicsByTab.size() / 16;
        List<TopicModel> topics = topicService.topicsOfOnePage(request);

        // 网页加入 token 避免 XSRF 攻击
        String token = UUID.randomUUID().toString();
        UserModel currentUser = userService.currentUser(request);
        Integer currentId = currentUser.getId();
        tokenMap.put(currentId.toString(), token);

        Integer numberOfTopic = topicService.numberOfTopicByUser(currentId);
        Integer numberOfComment = topicService.numberOfCommentsByUserId(currentId);

        ModelAndView m = new ModelAndView("topic/topic_index");
        m.addObject("topics", topics);
        m.addObject("token", token);
        m.addObject("currentUser", currentUser);
        m.addObject("numberOfTopic", numberOfTopic);
        m.addObject("numberOfComment", numberOfComment);
        m.addObject("tab", tab);
        m.addObject("numberOfPages", numberOfPages);
        return m;
    }

    @GetMapping("/topic/add/view")
    public ModelAndView addView(HttpServletRequest request) {
        UserModel currentUser = userService.currentUser(request);

        ModelAndView m = new ModelAndView("topic/topic_add");
        m.addObject("currentUser", currentUser);
        return m;
    }

    @PostMapping("/topic/add")
    public ModelAndView add(HttpServletRequest request) {
        UserModel current = userService.currentUser(request);
        Integer userId = current.getId();

        topicService.add(request, userId);

        ModelAndView m = new ModelAndView("redirect:/");
        return m;
    }

    @GetMapping("/topic/delete")
    public ModelAndView deleteMapper(Integer id, HttpServletRequest request) {
        UserModel user = userService.currentUser(request);
        String token = request.getParameter("token");
        String userToken = tokenMap.get(user.getId().toString());

        if (userToken.equals(token) && !user.getUsername().equals("测试用户[admin]")) {
            topicService.deleteById(id);
            ModelAndView m = new ModelAndView("redirect:/");
            return m;
        } else {
            ModelAndView m = new ModelAndView("redirect:/login");
            return m;
        }
     }

    @GetMapping("/topic/edit")
    public ModelAndView edit(Integer id, HttpServletRequest request) {
        UserModel currentUser = userService.currentUser(request);
        TopicModel topic = topicService.findById(id);

        ModelAndView m = new ModelAndView("topic/topic_edit");
        m.addObject("topic", topic);
        m.addObject("currentUser", currentUser);
        return m;
    }

    @PostMapping("/topic/update")
    public ModelAndView updateMapper(HttpServletRequest request) {
        topicService.update(request);
        Integer topicId = Integer.valueOf(request.getParameter("topicId"));
        String viewName = String.format("redirect:/topic/detail/%s", topicId);
        ModelAndView m = new ModelAndView(viewName);
        return m;
    }

    @GetMapping("/topic/detail/{id}")
    public ModelAndView detail(@PathVariable Integer id, HttpServletRequest request) {
        topicService.updateNumberOfClick(id);

        UserModel currentUser = userService.currentUser(request);

        TopicModel topic = topicService.findByIdWithComment(id);

        if (topic == null) {
            return new ModelAndView("redirect:/");
        }

        Utility.log("topic update: %s", topic.getUpdatedTime());
        ModelAndView m = new ModelAndView("topic/topic_detail");
        m.addObject("topic", topic);
        m.addObject("currentUser", currentUser);
        return m;
    }

}
