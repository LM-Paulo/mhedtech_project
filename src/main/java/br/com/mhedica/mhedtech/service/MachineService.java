package br.com.mhedica.mhedtech.service;

import br.com.mhedica.mhedtech.dto.MachineDto;
import br.com.mhedica.mhedtech.entity.MachineEntity;
import br.com.mhedica.mhedtech.repository.MachineRepository;
import jakarta.persistence.NoResultException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class MachineService {

    @Autowired
    private MachineRepository machineRepository;


    public void createMachine(MachineDto machineDto){
        MachineEntity machineEntity = new MachineEntity();
        machineEntity.toEntity(machineDto);
        machineRepository.save(machineEntity);
    }

    public Page<MachineEntity> listMachine (Sort sort, Integer page, Integer size) throws NoResultException {
            Pageable pageable = PageRequest.of(page,size,sort);
            return machineRepository.findAll(pageable);

    }


}
