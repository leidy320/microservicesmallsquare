package com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@Table(name = "plateOrder")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderPlateEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "id_order")
    private OrderEntity order;
    @ManyToOne
    @JoinColumn(name = "id_plate")
    private PlateEntity plate;
    private int quantity;
}
