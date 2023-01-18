package br.com.mhedica.mhedtech.dto;

import br.com.mhedica.mhedtech.entity.HistoricEntity;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
public class HistoricDto {

    private Integer idHistoric;

    private LocalDateTime changeDate;

    private String description;

    private Long machine;

    public void setEntity(HistoricEntity historicEntity){
        historicEntity.setChangeDate(this.changeDate);
        historicEntity.setDescription(this.description);

    }

}
