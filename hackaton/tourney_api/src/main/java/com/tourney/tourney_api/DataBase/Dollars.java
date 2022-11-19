package com.tourney.tourney_api.DataBase;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "dollars")
@Data
public class Dollars {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private Users user_id;
    @Column(name = "balance")
    private int balance;

    public Dollars(Users user_id, int balance) {
        this.user_id = user_id;
        this.balance = balance;
    }

    public Dollars() {
    }
}
