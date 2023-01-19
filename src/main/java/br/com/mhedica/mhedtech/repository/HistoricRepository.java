package br.com.mhedica.mhedtech.repository;

import br.com.mhedica.mhedtech.entity.HistoricEntity;
import jdk.jfr.Registered;
import org.springframework.data.jpa.repository.JpaRepository;

@Registered
public interface HistoricRepository extends JpaRepository<HistoricEntity,Integer> {

}
