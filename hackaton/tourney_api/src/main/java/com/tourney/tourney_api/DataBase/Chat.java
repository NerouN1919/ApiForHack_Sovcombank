package com.tourney.tourney_api.DataBase;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Table(name = "chat")
@Entity
@Data
public class Chat {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "text")
    private String text;
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "from_user")
    private Users userFrom;
    @Column(name = "time")
    private java.sql.Timestamp time = new java.sql.Timestamp(new Date().getTime());
}
