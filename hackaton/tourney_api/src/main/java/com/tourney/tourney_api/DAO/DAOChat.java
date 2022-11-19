package com.tourney.tourney_api.DAO;

import com.tourney.tourney_api.DTO.DTOForChat;
import com.tourney.tourney_api.DTO.DTOResultGetMes;
import com.tourney.tourney_api.DataBase.Chat;
import com.tourney.tourney_api.DataBase.Users;
import org.hibernate.Session;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class DAOChat {
    @Autowired
    private EntityManager entityManager;
    public void sendMessage(DTOForChat dtoForChat){
        Session session = entityManager.unwrap(Session.class);
        Users users = session.get(Users.class, dtoForChat.getId());
        users.sendMessage(dtoForChat.getChat());
        session.save(users);
    }
    public List<DTOResultGetMes> getMessageByDate(String date){
        //String str = "12/27/2019 14:15:45";
        DateTimeFormatter formatter = DateTimeFormat.forPattern("MM/dd/yyyy HH:mm:ss");
        DateTime dateTime = DateTime.parse(date, formatter);
        Date need = dateTime.toDate();
        Session session = entityManager.unwrap(Session.class);
        List<Chat> messages = session.createQuery("from Chat where id > 0").getResultList();
        List<DTOResultGetMes> result = new ArrayList<>();
        for(Chat in : messages){
            if(in.getTime().after(need)){
                DTOResultGetMes dtoResultGetMes = new DTOResultGetMes();
                dtoResultGetMes.setUser_id(in.getUserFrom().getId());
                dtoResultGetMes.setChat(in);
                result.add(dtoResultGetMes);
            }
        }
        return result;
    }
}
