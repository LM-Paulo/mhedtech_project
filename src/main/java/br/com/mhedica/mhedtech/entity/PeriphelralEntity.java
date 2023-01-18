package br.com.mhedica.mhedtech.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "periphelral")
@Getter
@Setter
public class PeriphelralEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPeriphelral;

    private LocalDateTime purchaseDate;

    private String config;

    private String model;

    @ManyToOne
    private MachineEntity machine;
}
