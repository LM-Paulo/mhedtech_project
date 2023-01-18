package br.com.mhedica.mhedtech.service;

import br.com.mhedica.mhedtech.dto.HistoricDto;
import br.com.mhedica.mhedtech.entity.HistoricEntity;
import br.com.mhedica.mhedtech.entity.MachineEntity;
import br.com.mhedica.mhedtech.repository.HistoricRepository;
import br.com.mhedica.mhedtech.repository.MachineRepository;
import jakarta.persistence.NoResultException;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class HistoricService {

    @Autowired
    private HistoricRepository historicRepository;
    @Autowired
    private MachineRepository machineRepository;

    private Logger logger;


    public void createHistory(HistoricDto historicDto) throws NoResultException {
        HistoricEntity historicEntity = new HistoricEntity();
        historicDto.setEntity(historicEntity);
        Optional<MachineEntity> machine = machineRepository.findBypatrimony(historicDto.getMachine());
        if(!machine.isPresent())
            throw new NoResultException();

        try{
            historicEntity.setMachine(machine.get());
            historicRepository.save(historicEntity);
        }catch (Exception ex){
            logger.error("Unable to register History ->" , ex.getMessage());

        }


    }


    public Page<HistoricEntity> historicallist (Sort sort, Integer page, Integer size) throws NoResultException {

        try {
            Pageable pageable = PageRequest.of(page,size,sort);
            return historicRepository.findAll(pageable);
        }catch (Exception ex){
            logger.error("Unable to find history ->" , ex.getMessage());
            throw new NoResultException();
        }

    }
}
