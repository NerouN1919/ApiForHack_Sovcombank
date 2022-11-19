package com.user_control.hackaton.DAO;

import com.user_control.hackaton.DTO.DTOForGiveMoney;
import com.user_control.hackaton.DTO.Login;
import com.user_control.hackaton.DataBase.Users;
import com.user_control.hackaton.Errors.Failed;
import com.user_control.hackaton.FileWork.FileDownloadUtil;
import com.user_control.hackaton.FileWork.FileUploadResponse;
import com.user_control.hackaton.FileWork.FileUploadUtil;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityManager;
import java.io.IOException;
import java.util.List;

@Repository
public class DAOUser {
    @Autowired
    private EntityManager entityManager;
    @Autowired
    private PasswordEncoder passwordEncoder;
    public Users registration(Users users){
        Session session = entityManager.unwrap(Session.class);
        List<Users> already = session.createQuery("from Users where login = '" + users.getLogin()+ "'").getResultList();
        if(already.size()!=0){
            throw new Failed("Already this login");
        }
        users.setPasswordHash(passwordEncoder.encode(users.getPasswordHash()));
        session.save(users);
        return users;
    }
    public Login login(Users users){
        Session session = entityManager.unwrap(Session.class);
        List<Users> need = session.createQuery("from Users where login = '" + users.getLogin()+ "'").getResultList();
        if(need.get(0).is_confirmed()==false){
            throw new Failed("Not confirmed");
        }
        if(need.size()==0){
            throw new Failed("Failed login");
        }
        Users needUser = need.get(0);
        if(passwordEncoder.matches(users.getPasswordHash(), needUser.getPasswordHash())){
            return new Login(needUser.getId());
        }
        throw new Failed("Failed login");
    }
    public Users confirmed(int id){
        Session session = entityManager.unwrap(Session.class);
        Users users = session.get(Users.class, id);
        users.set_confirmed(true);
        session.update(users);
        return users;
    }
    public Users giveMoney(DTOForGiveMoney dtoForGiveMoney){
        Session session = entityManager.unwrap(Session.class);
        Users need = session.get(Users.class, dtoForGiveMoney.getId());
        need.setBalance(need.getBalance()+dtoForGiveMoney.getHowMuch());
        session.update(need);
        return need;
    }
    public ResponseEntity<?> downloadFile(String fileCode) {
        FileDownloadUtil downloadUtil = new FileDownloadUtil();

        Resource resource = null;
        try {
            resource = downloadUtil.getFileAsResource(fileCode);
        } catch (IOException e) {
            return ResponseEntity.internalServerError().build();
        }

        if (resource == null) {
            return new ResponseEntity<>("File not found", HttpStatus.NOT_FOUND);
        }

        String contentType = "application/octet-stream";
        String headerValue = "attachment; filename=\"" + resource.getFilename() + "\"";

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, headerValue)
                .body(resource);
    }
    public Users getUser(int id){
        Session session = entityManager.unwrap(Session.class);
        return session.get(Users.class, id);
    }
    public ResponseEntity<FileUploadResponse> uploadFile(
            MultipartFile multipartFile, int id)
            throws IOException {

        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        long size = multipartFile.getSize();

        String filecode = FileUploadUtil.saveFile(fileName, multipartFile);

        FileUploadResponse response = new FileUploadResponse();
        response.setFileName(fileName);
        response.setSize(size);
        response.setDownloadUri("/downloadFile/" + filecode);
        Session session = entityManager.unwrap(Session.class);
        Users users = session.get(Users.class, id);
        users.setPathToPhoto(filecode);
        session.save(users);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
