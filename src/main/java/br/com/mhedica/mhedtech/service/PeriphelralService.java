package br.com.mhedica.mhedtech.service;

import br.com.mhedica.mhedtech.dto.PeriphelralDto;
import br.com.mhedica.mhedtech.entity.MachineEntity;
import br.com.mhedica.mhedtech.entity.PeriphelralEntity;
import br.com.mhedica.mhedtech.exceptions.BusinessException;
import br.com.mhedica.mhedtech.repository.MachineRepository;
import br.com.mhedica.mhedtech.repository.PeriphelralRepository;
import jakarta.persistence.NoResultException;
import org.hibernate.ObjectNotFoundException;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PeriphelralService {

    static org.jboss.logging.Logger logger = Logger.getLogger(PeriphelralService.class.getName());
    @Autowired
    private PeriphelralRepository periphelralRepository;

    @Autowired
    private MachineRepository machineRepository;




    public void createPeripheral(PeriphelralDto periphelralDto) throws BusinessException {
        PeriphelralEntity periphelralEntity = new PeriphelralEntity();

        Optional<MachineEntity> machine = machineRepository.findBypatrimony(periphelralDto.getMachine());
        if(!machine.isPresent()){
            throw new BusinessException("machine not found");
        }
        periphelralDto.setEntity(periphelralEntity);
        periphelralEntity.setMachine(machine.get());
        periphelralRepository.save(periphelralEntity);
    }


    public Page<PeriphelralEntity> peripheralList (Sort sort, Integer page, Integer size) throws NoResultException{

        try {
            Pageable pageable = PageRequest.of(page,size,sort);
            return periphelralRepository.findAll(pageable);
        }catch (Exception ex){
            //logger.error("Unable to locate peripherals ->" , ex.getMessage());
            throw new NoResultException();
        }

    }


    public void deletePeripheral(Integer ID){
        Optional<PeriphelralEntity> periferico = periphelralRepository.findById(ID);
        if (periferico.isPresent()){
            PeriphelralEntity perifericoEntity = periferico.get();
            periphelralRepository.delete(perifericoEntity);
        }else{
            throw new ObjectNotFoundException(ID, "-> Peripheral not found");
        }
    }

}
