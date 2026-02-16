package com.example.demo.model;

import jakarta.persistence.*;

@Entity
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String type;
    private int price;
    private String status;   // Available / Booked

    public Room() {}

    public Room(String type, int price, String status) {
        this.type = type;
        this.price = price;
        this.status = status;
    }

    public int getId() { return id; }
    public String getType() { return type; }
    public int getPrice() { return price; }
    public String getStatus() { return status; }

    public void setStatus(String status) { this.status = status; }
}
