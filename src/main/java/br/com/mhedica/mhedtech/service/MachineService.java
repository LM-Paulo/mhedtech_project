package br.com.mhedica.mhedtech.service;

import br.com.mhedica.mhedtech.dto.MachineDto;
import br.com.mhedica.mhedtech.entity.MachineEntity;
import br.com.mhedica.mhedtech.repository.MachineRepository;
import jakarta.persistence.NoResultException;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class MachineService {

    static org.jboss.logging.Logger logger = Logger.getLogger(UserService.class.getName());
    @Autowired
    private MachineRepository machineRepository;


    public void createMachine(MachineDto machineDto){
        MachineEntity machineEntity = new MachineEntity();
        try{
            machineEntity.toEntity(machineDto);
            machineRepository.save(machineEntity);

        }catch (Exception ex){
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Could not find machine. -> ->");
            stringBuilder.append(ex.getMessage());
            logger.log(org.jboss.logging.Logger.Level.ERROR,stringBuilder);

        }
    }

    public Page<MachineEntity> listMachine (Sort sort, Integer page, Integer size) throws NoResultException {

        try {
            Pageable pageable = PageRequest.of(page,size,sort);
            return machineRepository.findAll(pageable);
        }catch (NoResultException ex){
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Could not find machine. -> ->");
            stringBuilder.append(ex.getMessage());
            logger.log(org.jboss.logging.Logger.Level.ERROR,stringBuilder);
            throw new NoResultException();
        }

    }


}
