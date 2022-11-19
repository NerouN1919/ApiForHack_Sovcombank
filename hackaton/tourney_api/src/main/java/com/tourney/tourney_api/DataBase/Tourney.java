package com.tourney.tourney_api.DataBase;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "tourney")
@Data
public class Tourney {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private Users user_id;
    @Column(name = "balance")
    private double balance = 5000;

    public Tourney(Users user_id, double balance) {
        this.user_id = user_id;
        this.balance = balance;
    }

    public Tourney() {

    }
}
