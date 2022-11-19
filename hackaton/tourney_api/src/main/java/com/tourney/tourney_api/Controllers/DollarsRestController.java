package com.tourney.tourney_api.Controllers;

import com.tourney.tourney_api.DTO.DTOBuySold;
import com.tourney.tourney_api.DataBase.Dollars;
import com.tourney.tourney_api.Services.ServiceDollar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class DollarsRestController {
    @Autowired
    private ServiceDollar serviceDollar;
    @PostMapping("/buy")
    public Dollars buy(@RequestBody DTOBuySold dtoBuySold){
        return serviceDollar.buy(dtoBuySold);
    }
    @PostMapping("/sold")
    public Dollars sold(@RequestBody DTOBuySold dtoBuySold){
        return serviceDollar.sold(dtoBuySold);
    }
}
