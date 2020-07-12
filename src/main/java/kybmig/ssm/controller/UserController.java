package kybmig.ssm.controller;

import kybmig.ssm.Utility;
import kybmig.ssm.model.TopicCommentModel;
import kybmig.ssm.model.TopicModel;
import kybmig.ssm.model.UserModel;
import kybmig.ssm.service.TopicCommentService;
import kybmig.ssm.service.TopicService;
import kybmig.ssm.service.UserService;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Controller
public class UserController {
    private UserService userService;
    private HashMap<String, Integer> tokenMap;
    private AsyncTask asyncTask;
    private TopicCommentService topicCommentService;
    private TopicService topicService;

    public UserController(UserService userService, AsyncTask asyncTask, TopicCommentService topicCommentService, TopicService topicService) {
        this.userService = userService;
        this.asyncTask = asyncTask;
        this.topicCommentService = topicCommentService;
        this.topicService = topicService;
    }

    @GetMapping("/login")
    public ModelAndView loginView(HttpServletRequest request) {
        UserModel currentUser = userService.currentUser(request);
        ModelAndView m = new ModelAndView("user/login");
        m.addObject("currentUser", currentUser);
        return m;
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return "redirect:/login";
    }

    @PostMapping("/user/login")
    public ModelAndView login(HttpServletRequest request) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        UserModel user = userService.findByUsername(username);

        if (userService.validLogin(username, password)) {
            request.getSession().setAttribute("user_id", user.getId());
            return new ModelAndView("redirect:/");
        } else {
            UserModel currentUser = userService.currentUser(request);
            ModelAndView m = new ModelAndView("user/login");
            m.addObject("currentUser", currentUser);
            m.addObject("invalidLogin", true);
            return m;
        }
    }

    @GetMapping("/register")
    public ModelAndView registerView(HttpServletRequest request) {
        UserModel currentUser = userService.currentUser(request);
        ModelAndView m = new ModelAndView("user/register");
        m.addObject("currentUser", currentUser);
        m.addObject("existUsername", false);
        return m;
    }

    @PostMapping("/user/register")
    public ModelAndView register(HttpServletRequest request) {
        UserModel currentUser = userService.currentUser(request);
        Boolean existUsername = userService.existUsername(request);
        if (userService.validRegister(request)) {
            userService.add(request);
            return loginView(request);
        } else {
            ModelAndView m = new ModelAndView("user/register");
            m.addObject("currentUser", currentUser);
            m.addObject("existUsername", existUsername);
            return m;
        }

    }

    @GetMapping("/user/profile/{id}")
    public ModelAndView profile(@PathVariable Integer id, HttpServletRequest request) {
        UserModel user = userService.findById(id);
        UserModel currentUser = userService.currentUser(request);

        List<TopicModel> topics = userService.findTopicByUserId(id);
        List<TopicModel> joinedTopics = topicService.findTopicByComment(id);

        Integer numberOfTopic = topicService.numberOfTopicByUser(id);
        Integer numberOfComment = topicService.numberOfCommentsByUserId(id);

        ModelAndView m = new ModelAndView("user/profile");
        m.addObject("user", user);
        m.addObject("currentUser", currentUser);
        m.addObject("topics", topics);
        m.addObject("joinedTopics", joinedTopics);
        m.addObject("numberOfTopic", numberOfTopic);
        m.addObject("numberOfComment", numberOfComment);

        return m;
    }

    @GetMapping("/user/edit")
    public ModelAndView edit(Integer id, HttpServletRequest request) {
        UserModel user = userService.findById(id);
        UserModel currentUser = userService.currentUser(request);

        ModelAndView m = new ModelAndView("/user/edit");
        m.addObject("user", user);
        m.addObject("currentUser", currentUser);
        return m;
    }

    @PostMapping("/user/update")
    public String updateMapper(HttpServletRequest request) {
        userService.updateUserNameAndNote(request);
        String id = request.getParameter("id");

        String path = String.format("redirect:/user/profile/%s", id);
        return path;
    }

    @GetMapping("/reset/index")
    public ModelAndView resetIndex(HttpServletRequest request) {
        UserModel currentUser = userService.currentUser(request);
        ModelAndView m = new ModelAndView("/user/reset_index");
        m.addObject("currentUser", currentUser);
        return m;
    }

    @PostMapping("/reset/send")
    public ModelAndView reset(String username, HttpServletRequest request) {
        UserModel currentUser = userService.currentUser(request);
        UserModel user = userService.findByUsername(username);
        if (user == null) {
            ModelAndView m = new ModelAndView("/user/reset_index");
            m.addObject("existUser", false);
            m.addObject("currentUser", currentUser);
            return m;
        }

        Integer userId = user.getId();
        String token = UUID.randomUUID().toString();
        this.tokenMap = new HashMap<>();
        this.tokenMap.put(token , userId);

        String address = user.getEmail();
        String title = String.format("Hi! %s, reset password - kcat.top", user.getUsername());
        String content = String.format("http://www.kcat.top/reset/view?token=%s", token);
        asyncTask.sendMail(address, title, content);

        ModelAndView m = new ModelAndView("/user/reset_index");
        m.addObject("existUser", true);
        m.addObject("currentUser", currentUser);
        return m;
    }

    @GetMapping("/reset/view")
    public ModelAndView resetView(String token) {
        UserModel currentUser = userService.guest();
        if (tokenMap == null) {
            return new ModelAndView("redirect:/login");
        } else if (tokenMap.containsKey(token)) {
            ModelAndView m = new ModelAndView("/user/reset");
            m.addObject("token", token);
            m.addObject("currentUser", currentUser);
            return m;
        } else {
            return new ModelAndView("redirect:/login");
        }
    }

    @PostMapping("/reset/update")
    public ModelAndView updatePassword(String token, String password) {

        if (tokenMap == null) {
            return new ModelAndView("redirect:/login");
        } else if (tokenMap.containsKey(token)) {
            userService.updatePassword(tokenMap, token, password);
            return new ModelAndView("redirect:/login");
        } else {
            return new ModelAndView("redirect:/login");
        }
    }

    @GetMapping("/users")
    public ModelAndView index(HttpServletRequest request) {
        UserModel currentUser = userService.currentUser(request);
        List<UserModel> users = userService.findAllUser();

        ModelAndView m = new ModelAndView("user/users");
        m.addObject("currentUser", currentUser);
        m.addObject("users", users);
        return m;
    }

    @GetMapping("/admin")
    public ModelAndView admin(HttpServletRequest request) {
        UserModel currentUser = userService.currentUser(request);
        List<UserModel> users = userService.findAllUser();

        ModelAndView m = new ModelAndView("user/admin");
        m.addObject("currentUser", currentUser);
        m.addObject("users", users);
        return m;
    }

    @GetMapping("/admin/edit")
    public ModelAndView adminEdit(Integer id, HttpServletRequest request) {
        UserModel user = userService.findById(id);
        UserModel currentUser = userService.currentUser(request);

        ModelAndView m = new ModelAndView("user/admin_edit");
        m.addObject("user", user);
        Utility.log("create time: %s", user.getCreatedTime());
        m.addObject("currentUser", currentUser);
        return m;
    }

    @PostMapping("/admin/update")
    public ModelAndView adminUpdate(HttpServletRequest request) {
        UserModel currentUser = userService.currentUser(request);
        if (currentUser.getUsername().equals("测试用户[admin]")) {
            ModelAndView m = new ModelAndView("redirect:/permission");
            return m;
        } else {
            userService.updateByAdmin(request);
            String id = request.getParameter("id");
            String path = String.format("redirect:/user/profile/%s", id);
            ModelAndView m = new ModelAndView(path);
            return m;
        }

    }

    @GetMapping("/permission")
    public ModelAndView permission(HttpServletRequest request) {
        UserModel currentUser = userService.currentUser(request);
        ModelAndView m = new ModelAndView("user/permission");
        m.addObject("currentUser", currentUser);
        return m;
    }

    @GetMapping("/about")
    public ModelAndView about(HttpServletRequest request) {
        UserModel currentUser = userService.currentUser(request);
        ModelAndView m = new ModelAndView("user/about");
        m.addObject("currentUser", currentUser);

        return m;
    }

}
