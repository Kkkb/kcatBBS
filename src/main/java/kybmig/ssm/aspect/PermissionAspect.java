package kybmig.ssm.aspect;


import kybmig.ssm.Utility;
import kybmig.ssm.model.TopicCommentModel;
import kybmig.ssm.model.TopicModel;
import kybmig.ssm.model.UserModel;
import kybmig.ssm.model.UserRole;
import kybmig.ssm.service.TopicCommentService;
import kybmig.ssm.service.TopicService;
import kybmig.ssm.service.UserService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
public class PermissionAspect {
    private UserService userService;
    private HttpServletRequest request;
    private TopicService topicService;
    private TopicCommentService topicCommentService;

    public PermissionAspect(UserService userService, HttpServletRequest request, TopicService topicService, TopicCommentService topicCommentService) {
        this.request = request;
        this.userService = userService;
        this.topicService = topicService;
        this.topicCommentService = topicCommentService;
    }

    @Around("execution(* kybmig.ssm.controller.UserController.profile(..)) || " +
            "execution(* kybmig.ssm.controller.UserController.index(..)) || " +
            "execution(* kybmig.ssm.controller.TopicController.add(..)) || " +
            "execution(* kybmig.ssm.controller.TopicController.addView(..)) || " +
            "execution(* kybmig.ssm.controller.TopicCommentController.add(..))")
    public ModelAndView loginRequired(ProceedingJoinPoint joint) throws Throwable {
        Utility.log("loginRequired 正在访问的 url %s", request.getRequestURI());
        Utility.log("loginRequired 正在执行的方法 %s %s", joint.getSignature(), joint.getArgs());
        Integer userID = (Integer) request.getSession().getAttribute("user_id");
        if (userID == null) {
            Utility.log("loginRequired 没有 session");
            return new ModelAndView("redirect:/login");
        } else {
            UserModel u = userService.findById(userID);
            if (u == null && u.getRole().equals(UserRole.guest)) {
                Utility.log("loginRequired 用户不存在 %s", userID);
                return new ModelAndView("redirect:/login");
            } else {
                return (ModelAndView) joint.proceed();
            }
        }
    }

    @Around("execution(* kybmig.ssm.controller.TopicController.edit(..)) || " +
            "execution(* kybmig.ssm.controller.TopicController.updateMapper(..))" )
    public ModelAndView owerRequired(ProceedingJoinPoint joint) throws Throwable {
        Utility.log("owerRequired 正在访问的 url %s", request.getRequestURI());
        Utility.log("owerRequired 正在执行的方法 %s %s", joint.getSignature(), joint.getArgs());
        Integer userID = (Integer) request.getSession().getAttribute("user_id");

        String topicIdString = request.getParameter("id");
        if (topicIdString == null) {
            return new ModelAndView("redirect:/login");
        }

        Integer topicId = Integer.valueOf(topicIdString);
        if (userID == null) {
            Utility.log("owerRequired 没有 session");
            return new ModelAndView("redirect:/login");
        } else {
            UserModel u = userService.findById(userID);
            if (u == null && u.getRole().equals(UserRole.guest)) {
                Utility.log("owerRequired 用户不存在 %s", userID);
                return new ModelAndView("redirect:/login");
            } else {
                TopicModel topic = topicService.findById(topicId);
                if (userID.equals(topic.getUserId()) || u.getRole().equals(UserRole.admin)) {
                    return (ModelAndView) joint.proceed();
                } else {
                    return new ModelAndView("redirect:/login");
                }
            }
        }
    }

    @Around("execution(* kybmig.ssm.controller.TopicCommentController.edit(..)) || " +
            "execution(* kybmig.ssm.controller.TopicCommentController.update(..))" )
    public ModelAndView commentOwnerRequired(ProceedingJoinPoint joint) throws Throwable {
        Integer id = Integer.valueOf(request.getParameter("id"));
        UserModel user = userService.currentUser(request);
        TopicCommentModel comment = topicCommentService.findCommentById(id);
        Integer ownerId = comment.getUserId();

        if (user.getRole().equals(UserRole.admin) || user.getId().equals(ownerId)) {
            return (ModelAndView) joint.proceed();
        } else {
            return new ModelAndView("redirect:/login");
        }
    }

    @Around("execution(* kybmig.ssm.controller.UserController.edit(..)) || " +
            "execution(* kybmig.ssm.controller.UserController.updateMapper(..))")
    public ModelAndView userOwerRequired(ProceedingJoinPoint joint) throws Throwable {
        Integer userID = (Integer) request.getSession().getAttribute("user_id");

        Integer editId = Integer.valueOf(request.getParameter("id"));

        if (userID == null) {
            return new ModelAndView("redirect:/login");
        } else if (userID.equals(editId)) {
            return (ModelAndView) joint.proceed();
        } else {
            return new ModelAndView("redirect:/login");
        }
    }

    @Around("execution(* kybmig.ssm.controller.UserController.admin(..)) || " +
            "execution(* kybmig.ssm.controller.UserController.adminEdit(..)) || " +
            "execution(* kybmig.ssm.controller.UserController.adminUpdate(..)) || " +
            "execution(* kybmig.ssm.controller.TopicController.deleteMapper(..)) || " +
            "execution(* kybmig.ssm.controller.TopicCommentController.delete(..))")
    public ModelAndView adminRequired(ProceedingJoinPoint joint) throws Throwable {
        UserModel currentUser = userService.currentUser(request);

        if (currentUser.getRole().equals(UserRole.admin)) {
            return (ModelAndView) joint.proceed();
        } else {
            return new ModelAndView("redirect:/permission");
        }
    }


}
