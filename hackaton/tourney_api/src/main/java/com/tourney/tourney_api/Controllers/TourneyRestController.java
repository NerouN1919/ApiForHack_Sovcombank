package com.tourney.tourney_api.Controllers;

import com.tourney.tourney_api.DTO.DTOBuySold;
import com.tourney.tourney_api.DataBase.Tourney;
import com.tourney.tourney_api.Services.ServiceTourney;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class TourneyRestController {
    @Autowired
    ServiceTourney serviceTourney;
    @GetMapping("/addUser/{id}")
    public Tourney addUser(@PathVariable("id") int id){
        return serviceTourney.addUser(id);
    }
    @PostMapping("/giveMoneyInTourney")
    public Tourney giveMoney(@RequestBody DTOBuySold dtoBuySold){
        return serviceTourney.giveMoney(dtoBuySold);
    }
}
