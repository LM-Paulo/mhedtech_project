package br.com.mhedica.mhedtech.repository;

import br.com.mhedica.mhedtech.entity.MachineEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MachineRepository extends JpaRepository<MachineEntity,Integer> {

    Optional<MachineEntity> findBypatrimony(Long Id);

}
