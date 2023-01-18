package br.com.mhedica.mhedtech.entity;

import br.com.mhedica.mhedtech.dto.UserDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;

@Entity
@Table(name = "user")
@Getter
@Setter
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer userId;

    @Column(nullable = false)
    @NotBlank(message = "Name cannot be empty")
    @Length(min = 5, max = 100)
    private String name;

    @Column(nullable = false)
    @NotBlank(message = "Sector cannot be empty")
    private String sector;

    private LocalDateTime dateMaq;


    @OneToOne( fetch = FetchType.EAGER)
    @JoinColumn(name = "patrimony")
    private MachineEntity machine;

    public void toEntity(UserDto userDto){

        this.name = userDto.getName();
        this.sector = userDto.getSector();
        this.dateMaq = userDto.getDateMaq();

    }

}
