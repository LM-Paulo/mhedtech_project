package br.com.mhedica.mhedtech.service;


import br.com.mhedica.mhedtech.dto.UserDto;
import br.com.mhedica.mhedtech.entity.MachineEntity;
import br.com.mhedica.mhedtech.entity.UserEntity;
import br.com.mhedica.mhedtech.exceptions.BusinessException;
import br.com.mhedica.mhedtech.repository.MachineRepository;
import br.com.mhedica.mhedtech.repository.UserRepository;
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
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserService {

    static org.jboss.logging.Logger logger = Logger.getLogger(UserService.class.getName());
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MachineRepository machineRepository;




    public void createUser(UserDto userDto) throws NoResultException {

        UserEntity userEntity = new UserEntity();
        Optional<MachineEntity> machine = machineRepository.findBypatrimony(userDto.getMachine());
        if(!machine.isPresent()){
            ResponseEntity.ok("Machine does not exist in the bank!");
        }
        try {
            userEntity.setMachine(machine.get());
            userEntity.toEntity(userDto);
            userEntity.getMachine();
            userRepository.save(userEntity);
        }catch (NoResultException ex){
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("don't successfully created user. ->");
            stringBuilder.append(ex.getMessage());
            logger.log(Logger.Level.ERROR,stringBuilder);
            ResponseEntity.ok(stringBuilder.toString());

        }


    }

    public UserDto SearchUserByHeritage(Long patrimony) throws NoResultException {

        Optional<UserEntity> user = userRepository.searchUserByHeritage(patrimony);
        if(user.isPresent()){
            UserDto userDto = new UserDto();
            userDto.toDto(user.get());
            return userDto;
        }
        throw new NoResultException();

    }

    public Page<UserEntity> listUsers(Sort sort, Integer page, Integer size) throws NoResultException {
        try {
            Pageable pageable = PageRequest.of(page,size,sort);
            return userRepository.findAll(pageable);

        }catch (Exception ex){
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("unable to list users. ->");
            stringBuilder.append(ex.getMessage());
            logger.log(Logger.Level.ERROR,stringBuilder);
            ResponseEntity.ok(stringBuilder.toString());
            throw new NoResultException();
        }


    }

    public void deleteUsers(Integer ID) {

        Optional<UserEntity> user = userRepository.findById(ID);
        if (user.isPresent()){
            try {
                UserEntity userEntity = user.get();
                userRepository.delete(userEntity);
            }catch (ObjectNotFoundException ex){
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("could not delete user. ->");
                stringBuilder.append(ex.getMessage());
                logger.log(Logger.Level.ERROR,stringBuilder);
                ResponseEntity.ok(stringBuilder.toString());
            }

        }


    }

    @Transactional
    public void updateUsers(Integer userId,UserDto userDto) {

        Optional<UserEntity> user = userRepository.findById(userId);
        if (user.isPresent()) {

            try {
                UserEntity userEntity = new UserEntity();
                userEntity.toEntity(userDto);
                userRepository.updateUsers(userId, userDto.getName(), userDto.getSector(), userDto.getMachine());
            } catch (ObjectNotFoundException ex) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("User not found ->");
                stringBuilder.append(ex.getMessage());
                logger.log(Logger.Level.ERROR, stringBuilder);
                ResponseEntity.ok(stringBuilder.toString());
            }
        }


    }

}
