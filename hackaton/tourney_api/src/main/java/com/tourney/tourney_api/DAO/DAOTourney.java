package com.tourney.tourney_api.DAO;

import com.tourney.tourney_api.DTO.DTOBuySold;
import com.tourney.tourney_api.DataBase.Tourney;
import com.tourney.tourney_api.DataBase.Users;
import com.tourney.tourney_api.Errors.Failed;
import com.tourney.tourney_api.Errors.Info;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class DAOTourney {
    @Autowired
    private EntityManager entityManager;
    public Tourney addUser(int id){
        Session session = entityManager.unwrap(Session.class);
        Tourney tourney = new Tourney();
        tourney.setUser_id(session.find(Users.class, id));
        List<Tourney> need = session.createQuery("from Tourney where user_id = '" + tourney.getUser_id().getId() + "'").getResultList();
        if(need.size() != 0){
            throw new Failed("Already in tourney");
        }
        session.save(tourney);
        return tourney;
    }
    public Tourney giveMoney(DTOBuySold dtoBuySold){
        Session session = entityManager.unwrap(Session.class);
        List<Tourney> need = session.createQuery("from Tourney where user_id = '" + dtoBuySold.getId() + "'").getResultList();
        Tourney own = need.get(0);
        own.setBalance(own.getBalance() + dtoBuySold.getHowMuch());
        session.update(own);
        return own;
    }
}
