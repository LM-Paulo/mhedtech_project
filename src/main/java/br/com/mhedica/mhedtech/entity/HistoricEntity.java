package br.com.mhedica.mhedtech.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "historic")
public class HistoricEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idHistoric;

    private LocalDateTime changeDate = LocalDateTime.now();

    @NotBlank(message = "Description is a required field")
    private String description;

    @OneToOne(fetch = FetchType.EAGER,cascade = CascadeType.REMOVE)
    private MachineEntity machine;
}
