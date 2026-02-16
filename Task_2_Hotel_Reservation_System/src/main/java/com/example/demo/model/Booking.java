package com.example.demo.model;

import jakarta.persistence.*;

@Entity
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int roomId;
    private String customerName;
    private String phone;
    private String email;

    public Booking() {}

    public Booking(int roomId, String customerName, String phone, String email) {
        this.roomId = roomId;
        this.customerName = customerName;
        this.phone = phone;
        this.email = email;
    }

    public int getId() { return id; }
    public int getRoomId() { return roomId; }
    public String getCustomerName() { return customerName; }
    public String getPhone() { return phone; }
    public String getEmail() { return email; }
}
