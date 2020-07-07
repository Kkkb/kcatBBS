package kybmig.ssm.service;

import kybmig.ssm.Digest;
import kybmig.ssm.Utility;
import kybmig.ssm.mapper.TopicMapper;
import kybmig.ssm.mapper.UserMapper;
import kybmig.ssm.model.TopicModel;
import kybmig.ssm.model.UserModel;
import kybmig.ssm.model.UserRole;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
public class UserService {
    private UserMapper userMapper;
    private TopicMapper topicMapper;

    public UserService(UserMapper mapper, TopicMapper topicMapper) {
        this.userMapper = mapper;
        this.topicMapper = topicMapper;
    }

    public void add(HttpServletRequest request) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String defaultNote = "Hello";
        String defaultAvatar = "/avatar/default.jpg";

        UserModel m = new UserModel();
        m.setUsername(username);
        m.setPassword(saltedPassword(password));
        m.setEmail(email);
        m.setNote(defaultNote);
        m.setRole(UserRole.normal);
        m.setAvatar(defaultAvatar);

        Long unixTime = System.currentTimeMillis() / 1000L;
        m.setCreatedTime(unixTime);

        userMapper.insertUser(m);
    }


    public UserModel guest() {
        UserModel m = new UserModel();
        m.setId(-1);
        m.setUsername("游客");
        m.setPassword("123");
        m.setNote("这家伙很懒, 什么都没有留下");
        m.setRole(UserRole.guest);
        m.setCreatedTime(1593850644L);
        m.setAvatar("/avatar/default.jpg");
        return m;
    }

    public UserModel findByUsername(String username) {
        return userMapper.selectOneByUsername(username);
    }

    public boolean validLogin(String username, String password) {
        UserModel user = findByUsername(username);
        if (user != null && user.getPassword().equals(saltedPassword(password))) {
            return true;
        }

        return false;
    }

    public boolean validRegister(HttpServletRequest request) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");

        return password.length() != 0 && password.length() > 5 &&
                email.contains("@") && findByUsername(username) == null;
    }

    public boolean existUsername(HttpServletRequest request) {
        String username = request.getParameter("username");
        return findByUsername(username) != null;
    }

    public UserModel findById(Integer id) {
        return userMapper.selectUser(id);
    }

    public UserModel currentUser(HttpServletRequest request) {
        Integer userId = (Integer)request.getSession().getAttribute("user_id");

        if (userId == null) {
            return this.guest();
        }

        UserModel currentUser = this.findById(userId);

        if (currentUser == null) {
            return this.guest();
        }

        return currentUser;
    }

    public void updateUserNameAndNote(HttpServletRequest request) {
        UserModel user = new UserModel();
        Integer id = Integer.valueOf(request.getParameter("id"));
        String username = request.getParameter("username");
        String note = request.getParameter("note");

        user.setId(id);
        user.setUsername(username);
        user.setNote(note);

        userMapper.updateUserNameAndNote(user);
    }

    public void updateAvatar(HttpServletRequest request, String path) {
        UserModel user = new UserModel();
        Integer id = Integer.valueOf(request.getParameter("id"));
        String avatar = "/" + path;
        user.setId(id);
        user.setAvatar(avatar);

        userMapper.updateAvatar(user);
    }

    public void updatePassword(HashMap<String, Integer> tokenMap, String token, String password) {
        Integer userId = tokenMap.get(token);
        UserModel user = findById(userId);

        String saltedPassword = saltedPassword(password);
        user.setPassword(saltedPassword);

        userMapper.updatePassword(user);
    }

    public String saltedPassword(String password) {
        String salt = "g$a#k#k@i";
        String pd = password + salt;
        String hex = Digest.md5(pd);
        return hex;
    }

    public List<TopicModel> findTopicByUserId(Integer id) {
        return topicMapper.selectTopicByUserId(id);
    }

    public List<UserModel> findAllUser() {
        return userMapper.selectAllUser();
    }

    public void updateByAdmin(HttpServletRequest request) {
        UserModel user = new UserModel();
        Integer id = Integer.valueOf(request.getParameter("id"));
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String note = request.getParameter("note");
        String avatar = request.getParameter("avatar");
        String email = request.getParameter("email");
        Long createdTime = Long.valueOf(request.getParameter("createdTime"));
        UserRole role = UserRole.valueOf(request.getParameter("role"));

        user.setId(id);
        user.setUsername(username);
        user.setPassword(saltedPassword(password));
        user.setNote(note);
        user.setAvatar(avatar);
        user.setEmail(email);
        user.setCreatedTime(createdTime);
        user.setRole(role);

        userMapper.updateByAdmin(user);

    }


}
