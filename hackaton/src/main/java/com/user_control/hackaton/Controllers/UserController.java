package com.user_control.hackaton.Controllers;

import com.user_control.hackaton.DTO.DTOForGiveMoney;
import com.user_control.hackaton.DTO.Login;
import com.user_control.hackaton.DataBase.Users;
import com.user_control.hackaton.FileWork.FileUploadResponse;
import com.user_control.hackaton.Services.ServiceUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    ServiceUser serviceUser;
    @PostMapping("/reg")
    public Users reg(@RequestBody Users users){
        return serviceUser.reg(users);
    }
    @PostMapping("/login")
    public Login login(@RequestBody Users users){
        return serviceUser.login(users);
    }
    @PostMapping("/giveMoneyUser")
    public Users giveMoney(@RequestBody DTOForGiveMoney dtoForGiveMoney){
        return serviceUser.giveMoney(dtoForGiveMoney);
    }
    @GetMapping("/downloadFile/{fileCode}")
    public ResponseEntity<?> downloadFile(@PathVariable("fileCode") String fileCode) {
        return serviceUser.downloadFile(fileCode);
    }
    @PostMapping("/uploadFile/{id}")
    public ResponseEntity<FileUploadResponse> uploadFile(
            @RequestParam("file") MultipartFile multipartFile, @PathVariable("id") int id)
            throws IOException {
        return serviceUser.uploadFile(multipartFile, id);
    }
    @GetMapping("/confirmed/{id}")
    public Users confirmed(@PathVariable("id") int id){
        return serviceUser.confirmed(id);
    }
    @GetMapping("/getUser/{id}")
    public Users getUser(@PathVariable("id") int id){
        return serviceUser.getUser(id);
    }
}
