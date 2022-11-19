package com.user_control.hackaton.Services;

import com.user_control.hackaton.DAO.DAOUser;
import com.user_control.hackaton.DTO.DTOForGiveMoney;
import com.user_control.hackaton.DTO.Login;
import com.user_control.hackaton.DataBase.Users;
import com.user_control.hackaton.FileWork.FileUploadResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class ServiceUser {
    @Autowired
    DAOUser daoLoginReg;
    @Transactional
    public Login login(Users users){
        return daoLoginReg.login(users);
    }
    @Transactional
    public Users reg(Users users){
        return daoLoginReg.registration(users);
    }
    @Transactional
    public Users giveMoney(DTOForGiveMoney dtoForGiveMoney){
        return daoLoginReg.giveMoney(dtoForGiveMoney);
    }
    @Transactional
    public ResponseEntity<?> downloadFile(String fileCode){
        return daoLoginReg.downloadFile(fileCode);
    }
    @Transactional
    public ResponseEntity<FileUploadResponse> uploadFile(
            MultipartFile multipartFile, int id) throws IOException {
        return daoLoginReg.uploadFile(multipartFile, id);
    }
    @Transactional
    public Users confirmed(int id){
        return daoLoginReg.confirmed(id);
    }
    @Transactional
    public Users getUser(int id){
        return daoLoginReg.getUser(id);
    }
}
