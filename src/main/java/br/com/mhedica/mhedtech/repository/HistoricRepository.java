package br.com.mhedica.mhedtech.repository;

import br.com.mhedica.mhedtech.entity.HistoricEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface HistoricRepository extends JpaRepository<HistoricEntity,Integer> {

}
