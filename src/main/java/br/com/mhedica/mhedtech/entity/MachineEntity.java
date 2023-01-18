package br.com.mhedica.mhedtech.entity;

import br.com.mhedica.mhedtech.Enum.MachineEnum;
import br.com.mhedica.mhedtech.dto.MachineDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Entity
@Table(name ="machine")
@Getter
@Setter
public class MachineEntity {

    @Id
    private Long patrimony;

    @NotBlank(message = "Machine name cannot be empty")
    private String machineName;

    @NotBlank(message = "config name cannot be empty")
    private String config;

    private LocalDateTime purchaseDate;

    private String officeKey;

    private String antivirusKey;

    @Enumerated(EnumType.STRING)
    private MachineEnum status;

    public void toEntity(MachineDto machineDto){
        this.patrimony = machineDto.getPatrimony();
        this.machineName = machineDto.getMachineName();
        this.config = machineDto.getConfig();
        this.purchaseDate = machineDto.getPurchaseDate();
        this.officeKey = machineDto.getOfficeKey();
        this.antivirusKey = machineDto.getAntivirusKey();
        this.status=machineDto.getStatus();

    }
}
