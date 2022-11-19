package com.tourney.tourney_api.Services;

import com.tourney.tourney_api.DAO.DAOTourney;
import com.tourney.tourney_api.DTO.DTOBuySold;
import com.tourney.tourney_api.DataBase.Tourney;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ServiceTourney {
    @Autowired
    private DAOTourney daoTourney;
    @Transactional
    public Tourney addUser(int id){
        return daoTourney.addUser(id);
    }
    @Transactional
    public Tourney giveMoney(DTOBuySold dtoBuySold){
       return daoTourney.giveMoney(dtoBuySold);
    }
}
