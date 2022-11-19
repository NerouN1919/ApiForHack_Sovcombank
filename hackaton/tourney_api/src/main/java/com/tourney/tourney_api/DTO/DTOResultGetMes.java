package com.tourney.tourney_api.DTO;

import com.tourney.tourney_api.DataBase.Chat;
import lombok.Data;

@Data
public class DTOResultGetMes {
    private int user_id;
    private Chat chat;
}
