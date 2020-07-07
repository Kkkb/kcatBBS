package kybmig.ssm.controller;

import kybmig.ssm.Utility;
import kybmig.ssm.model.TopicCommentModel;
import kybmig.ssm.model.TopicModel;
import kybmig.ssm.model.UserModel;
import kybmig.ssm.service.TopicCommentService;
import kybmig.ssm.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class TopicCommentController {

    private TopicCommentService topicCommentService;
    private UserService userService;


    public TopicCommentController(TopicCommentService topicCommentService, UserService userService) {
        this.topicCommentService = topicCommentService;
        this.userService = userService;
    }

    @PostMapping("/topic/detail/{id}/comment")
    public ModelAndView add(@PathVariable Integer id, String content, HttpServletRequest request) {
        UserModel currentUser = userService.currentUser(request);
        Integer userId = currentUser.getId();
        topicCommentService.add(content, id, userId);

        String viewName = String.format("redirect:/topic/detail/%s", id);
        ModelAndView m = new ModelAndView(viewName);
        return m;
    }

    @GetMapping("/comment/edit")
    public ModelAndView edit(Integer id, HttpServletRequest request) {
        TopicCommentModel comment = topicCommentService.findCommentById(id);
        UserModel currentUser = userService.currentUser(request);

        ModelAndView m = new ModelAndView("topic/comment_edit");
        m.addObject("comment", comment);
        m.addObject("currentUser", currentUser);
        return m;
    }

    @PostMapping("/comment/update")
    public ModelAndView update(Integer id, String content) {
        topicCommentService.update(id, content);

        TopicCommentModel comment = topicCommentService.findCommentById(id);
        Integer topicId = comment.getTopicId();
        String s = String.format("redirect:/topic/detail/%s", topicId);
        ModelAndView m = new ModelAndView(s);

        return m;
    }

    @GetMapping("/comment/delete")
    public ModelAndView delete(Integer id, HttpServletRequest request) {
        TopicCommentModel comment = topicCommentService.findCommentById(id);
        Integer topicId = comment.getTopicId();

        UserModel currentUser = userService.currentUser(request);
        if (!currentUser.getUsername().equals("测试用户[admin]")) {
            topicCommentService.delete(id);
        }

        String s = String.format("redirect:/topic/detail/%s", topicId);
        ModelAndView m = new ModelAndView(s);
        return m;
    }
}
