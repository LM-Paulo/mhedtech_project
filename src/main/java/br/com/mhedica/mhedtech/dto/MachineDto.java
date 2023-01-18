package br.com.mhedica.mhedtech.dto;

import br.com.mhedica.mhedtech.Enum.MachineEnum;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class MachineDto {

    private Long patrimony;

    private String machineName;

    private String config;

    private LocalDateTime purchaseDate;

    private String officeKey;

    private String antivirusKey;

    private MachineEnum status;


}
