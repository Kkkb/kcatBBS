package kybmig.ssm.controller;

import kybmig.ssm.Utility;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Component
class AsyncTask {
    private final MailSender sender;
    private final MailProperties mailProperties;

    public AsyncTask(MailSender sender, MailProperties mailProperties) {
        this.sender = sender;
        this.mailProperties = mailProperties;
    }

    @Async
    public void sendMail(String address, String title, String content) {
        Utility.log("异步发送邮件函数调用了");
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(mailProperties.getUsername());
        mailMessage.setSubject(title);
        mailMessage.setTo(address);
        mailMessage.setText(content);
        try {
            sender.send(mailMessage);
        } catch (MailException e) {
            Utility.log(e.getMessage());
        }
    }
}

@Controller
@RequestMapping("/mail")
public class MailController {
    private AsyncTask asyncTask;

    public MailController(AsyncTask asyncTask) {
        this.asyncTask = asyncTask;
    }


    @GetMapping("/index")
    public ModelAndView indexView() {
        ModelAndView m = new ModelAndView("mail/index");
        return m;
    }

    @GetMapping("/send")
    public ModelAndView send(String address, String title, String content) {
        Utility.log("address: %s", address);
        Utility.log("title: %s", title);
        Utility.log("content: %s", content);

        if (address != null && title != null && content != null) {
            asyncTask.sendMail(address, title, content);
            Utility.log("发送邮件完毕");
        }

        return indexView();
    }
}
