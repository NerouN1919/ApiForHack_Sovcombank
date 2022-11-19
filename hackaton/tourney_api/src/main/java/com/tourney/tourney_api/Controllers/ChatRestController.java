package com.tourney.tourney_api.Controllers;

import com.tourney.tourney_api.DTO.DTODate;
import com.tourney.tourney_api.DTO.DTOForChat;
import com.tourney.tourney_api.DTO.DTOResultGetMes;
import com.tourney.tourney_api.DataBase.Chat;
import com.tourney.tourney_api.Services.ServiceChat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ChatRestController {
    @Autowired
    private ServiceChat serviceChat;
    @PostMapping("/sendMessage")
    public void sendMessage(@RequestBody DTOForChat dtoForChat){
        serviceChat.sendMessage(dtoForChat);
    }
    @PostMapping("/getMessageBeforeDate")
    public List<DTOResultGetMes> getMessageByDate(@RequestBody DTODate dtoDate){
        return serviceChat.getMessageByDate(dtoDate.getDate());
    }
}
