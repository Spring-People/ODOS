package com.my.odos.domain;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Invitation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private int fromId;

    @Column
    private int toId;

    @Column
    private boolean connected;


    public void setFromId(int fromId) {
        this.fromId = fromId;
    }

    public void setToId(int toId) {
        this.toId = toId;
    }

    public void setConnected(boolean connected) {
        this.connected = connected;
    }
}
