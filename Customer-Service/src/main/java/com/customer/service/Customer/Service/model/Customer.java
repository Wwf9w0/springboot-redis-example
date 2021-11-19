package com.customer.service.Customer.Service.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@RequiredArgsConstructor
@Data
@Entity
@Table(name = "customer", schema = "public")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id" , unique = true)
    private Long id;

    private String name;
    private String contactName;
    private String address;
    private String city;
    private String postalCode;
    private String country;
}
