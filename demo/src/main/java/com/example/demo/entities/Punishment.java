//package com.example.demo.entities;
//
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//@Entity
//@Table(name = "punishments")
//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//public class Punishment {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int id;
//    @Column(name = "delay_day")
//    private int delayDay;
//    @Column(name= "penalty_fee")
//    private double penaltyFee;
//
//
//
//    @ManyToOne
//    @JoinColumn(name = "delivery_id")
//    private Delivery delivery;
//
//    @ManyToOne
//    @JoinColumn(name = "borrow_id")
//    private Borrow borrow;
//
//}
