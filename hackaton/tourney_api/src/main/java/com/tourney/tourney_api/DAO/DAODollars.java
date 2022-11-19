package com.tourney.tourney_api.DAO;

import com.tourney.tourney_api.DTO.DTOBuySold;
import com.tourney.tourney_api.DataBase.Dollars;
import com.tourney.tourney_api.DataBase.Tourney;
import com.tourney.tourney_api.DataBase.Users;
import com.tourney.tourney_api.Errors.Failed;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class DAODollars {
    @Autowired
    private EntityManager entityManager;
    public Dollars buy(DTOBuySold dtoBuySold){
        int id = dtoBuySold.getId();
        int howMuch = dtoBuySold.getHowMuch();
        Session session = entityManager.unwrap(Session.class);
        Users need = session.get(Users.class, id);
        List<Dollars> alreadyIn = session.createQuery("from Dollars where user_id = '" + need.getId() + "'").getResultList();
        int needMoney = howMuch*60;
        List<Tourney> tourneyThis = session.createQuery("from Tourney where user_id = '" + need.getId() + "'").getResultList();
        Tourney tourney = tourneyThis.get(0);
        if(tourney.getBalance() < needMoney){
            throw new Failed("Dont have money for this");
        }
        Dollars dollars = null;
        tourney.setBalance(tourney.getBalance()-needMoney);
        session.save(tourney);
        if(alreadyIn.size() == 0){
            dollars = new Dollars();
            dollars.setUser_id(need);
            dollars.setBalance(dollars.getBalance() + howMuch);
            session.save(dollars);
        } else {
            dollars = alreadyIn.get(0);
            dollars.setUser_id(need);
            dollars.setBalance(dollars.getBalance() + howMuch);
            session.update(dollars);
        }
        return dollars;
    }
    public Dollars sold(DTOBuySold dtoBuySold){
        Session session = entityManager.unwrap(Session.class);
        List<Dollars> need = session.createQuery("from Dollars where user_id = '" + dtoBuySold.getId() + "'").getResultList();
        Dollars own = need.get(0);
        if(own.getBalance() < dtoBuySold.getHowMuch()){
            throw new Failed("Dont have dollars for this");
        }
        own.setBalance(own.getBalance()-dtoBuySold.getHowMuch());
        List<Tourney> inTourney = session.createQuery("from Tourney where user_id = '" + dtoBuySold.getId() + "'").getResultList();
        Tourney tourney = inTourney.get(0);
        tourney.setBalance(tourney.getBalance() + dtoBuySold.getHowMuch()*60);
        session.update(own);
        session.update(tourney);
        return own;
    }
}
