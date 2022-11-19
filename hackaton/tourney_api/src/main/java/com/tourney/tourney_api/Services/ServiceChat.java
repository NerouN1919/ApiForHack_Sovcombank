package com.tourney.tourney_api.Services;

import com.tourney.tourney_api.DAO.DAOChat;
import com.tourney.tourney_api.DTO.DTOForChat;
import com.tourney.tourney_api.DTO.DTOResultGetMes;
import com.tourney.tourney_api.DataBase.Chat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ServiceChat {
    @Autowired
    private DAOChat daoChat;
    @Transactional
    public void sendMessage(DTOForChat dtoForChat){
        daoChat.sendMessage(dtoForChat);
    }
    @Transactional
    public List<DTOResultGetMes> getMessageByDate(String date){
        return daoChat.getMessageByDate(date);
    }
}
