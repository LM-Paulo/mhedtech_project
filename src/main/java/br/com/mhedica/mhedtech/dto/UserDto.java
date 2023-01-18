package br.com.mhedica.mhedtech.dto;

import br.com.mhedica.mhedtech.entity.UserEntity;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
public class UserDto {

    private Integer userId;

    private String name;

    private String sector;

    private LocalDateTime dateMaq;

    private Long machine;

    public void toDto(UserEntity userEntity){

        this.name = userEntity.getName();
        this.sector = userEntity.getSector();
        this.dateMaq = userEntity.getDateMaq();

    }
}
