package kybmig.ssm.controller;

import com.sun.activation.registries.MailcapFile;
import kybmig.ssm.Utility;
import kybmig.ssm.service.UserService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

@Controller
public class FileUploadController {
    // 本地测试环境
//    private String dir = "avatar";
    // 生产环境
    private String dir = "/var/lib/tomcat9/webapps/images";

    private UserService userService;

    public FileUploadController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/upload")
    public String singleFileUpload(@RequestParam("file") MultipartFile file, HttpServletRequest request) {

        if (file.isEmpty()) {
            return "上传失败";
        } else {
            String path = "";
            String originName = file.getOriginalFilename();
            String[] parts = originName.split("\\.", 2);
            String randomStr = UUID.randomUUID().toString();
            String newFileName = String.format("%s.%s", randomStr, parts[1]);

            path = String.format("%s/%s", dir, newFileName);
            try(FileOutputStream os = new FileOutputStream(path)) {
                // Ge the file and save it somewhere
                byte[] bytes = file.getBytes();
                os.write(bytes);

                String avatarPath = String.format("avatar/%s", newFileName);
                userService.updateAvatar(request, avatarPath);

                String id = request.getParameter("id");
                String s = String.format("redirect:/user/profile/%s", id);
                return s;
            } catch (IOException e) {
                e.printStackTrace();
                return "上传失败";
            }
        }

    }

    @GetMapping("/avatar/{imageName}")
    @ResponseBody
    public ResponseEntity<byte[]> avatar(@PathVariable String imageName) {
        String path = String.format("%s/%s", dir, imageName);

        byte[] content;
        try (FileInputStream is = new FileInputStream(path)){
            content = is.readAllBytes();
        } catch (IOException e) {
            String s = String.format("Load file <%s> error <%s>", path, e);
            throw new RuntimeException(s);
        }

        return ResponseEntity.ok().
                contentType(MediaType.IMAGE_PNG).
                contentType(MediaType.IMAGE_JPEG).
                contentType(MediaType.IMAGE_GIF).
                body(content);
    }

}
