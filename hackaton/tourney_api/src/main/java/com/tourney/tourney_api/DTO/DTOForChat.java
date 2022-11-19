package com.tourney.tourney_api.DTO;

import com.tourney.tourney_api.DataBase.Chat;
import lombok.Data;

@Data
public class DTOForChat {
    private int id;
    private Chat chat;
}
