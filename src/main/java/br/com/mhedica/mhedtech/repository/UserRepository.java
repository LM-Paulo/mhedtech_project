package br.com.mhedica.mhedtech.repository;

import br.com.mhedica.mhedtech.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Integer> {

    @Query(value = "select u from UserEntity u join u.machine m where m.patrimony = :patrimony")
    Optional<UserEntity> searchUserByHeritage(Long patrimony);

    Optional<UserEntity> findById(Integer ID);

    @Modifying
    @Query("update UserEntity userEntity set "+
            "userEntity.name = :name, userEntity.sector = :sector, userEntity.machine = (select m from MachineEntity m where m.patrimony = :machine ) where userEntity.id = :id ")
    void updateUsers(@Param("id") Integer id, @Param("name")String name, @Param("sector") String sector, @Param("machine")Long machine);

}
