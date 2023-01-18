package br.com.mhedica.mhedtech.repository;

import br.com.mhedica.mhedtech.entity.PeriphelralEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface PeriphelralRepository extends JpaRepository<PeriphelralEntity,Integer> {
    Optional<PeriphelralEntity> findById(Integer ID);
}
