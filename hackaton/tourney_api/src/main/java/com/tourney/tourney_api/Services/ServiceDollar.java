package com.tourney.tourney_api.Services;

import com.tourney.tourney_api.DAO.DAODollars;
import com.tourney.tourney_api.DTO.DTOBuySold;
import com.tourney.tourney_api.DataBase.Dollars;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ServiceDollar {
    @Autowired
    private DAODollars daoDollars;
    @Transactional
    public Dollars buy(DTOBuySold dtoBuySold){
        return daoDollars.buy(dtoBuySold);
    }
    @Transactional
    public Dollars sold(DTOBuySold dtoBuySold){
        return daoDollars.sold(dtoBuySold);
    }
}
