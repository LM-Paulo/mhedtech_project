package br.com.mhedica.mhedtech.dto;

import br.com.mhedica.mhedtech.entity.PeriphelralEntity;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PeriphelralDto {

    private Integer idPeriphelral;

    private LocalDateTime purchaseDate;

    private String config;

    private String model;

    private Long machine;

    public void setEntity(PeriphelralEntity periphelralEntity){

        periphelralEntity.setModel(this.getModel());
        periphelralEntity.setConfig(this.getConfig());
        periphelralEntity.setPurchaseDate(this.getPurchaseDate());
    }

}
