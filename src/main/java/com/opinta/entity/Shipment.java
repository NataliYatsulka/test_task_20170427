package com.opinta.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Shipment {
    @Id
    @GeneratedValue
    private long id;
    @ManyToOne
    @JoinColumn(name = "sender_id")
    private Client sender;
    @ManyToOne
    @JoinColumn(name = "recipient_id")
    private Client recipient;
    @OneToOne
    private BarcodeInnerNumber barcode;
    @Enumerated(EnumType.STRING)
    private DeliveryType deliveryType;

    private BigDecimal price;
    private BigDecimal postPay;
    private String description;

    @OneToMany
    @JoinColumn(name = "shipment_id")
    private List<Parcel> parsels = new ArrayList<>();

    public Shipment(Client sender, Client recipient, DeliveryType deliveryType,
                    BigDecimal price, BigDecimal postPay, List parsels) {
        this.sender = sender;
        this.recipient = recipient;
        this.deliveryType = deliveryType;
        this.price = price;
        this.postPay = postPay;
        this.parsels = parsels;
    }
}
