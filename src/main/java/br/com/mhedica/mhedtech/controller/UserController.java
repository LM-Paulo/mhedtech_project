package br.com.mhedica.mhedtech.controller;

import br.com.mhedica.mhedtech.dto.UserDto;
import jakarta.persistence.NoResultException;
import org.jboss.logging.Logger;
import br.com.mhedica.mhedtech.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/user")
public class UserController {

    static Logger logger = Logger.getLogger(UserController.class.getName());

    @Autowired
    private UserService userService;


    @PostMapping("/create-user")
    public ResponseEntity<?> createUser(@RequestBody UserDto userDto){

        try {
            userService.createUser(userDto);
        }catch (Exception ex){
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("don't successfully created user -> ");
            stringBuilder.append(ex.getMessage());
            logger.log(Logger.Level.ERROR,stringBuilder);
            return ResponseEntity.ok(stringBuilder.toString());
        }

        return ResponseEntity.ok("successfully created user");

    }


    @GetMapping("/search-user-by-heritage/{patrimony}")
    public ResponseEntity<?> SearchUserByHeritage(@PathVariable("patrimony") Long patrimony){
        UserDto userDto;
        try{
            userDto = userService.SearchUserByHeritage(patrimony);
        }catch (Exception ex){
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("User not found, check the asset number. -> ");
            stringBuilder.append(ex.getMessage());
            logger.log(Logger.Level.ERROR,stringBuilder);
            return ResponseEntity.ok(stringBuilder.toString());

        }
        return ResponseEntity.ok(userDto);

    }

    @GetMapping("/list-users")
    public ResponseEntity<?> listUsers(@RequestParam("sort") Sort.Direction direction,@RequestParam String properties,
                                           @RequestParam("page") Integer page, @RequestParam("size") Integer size ){

        try {
            return ResponseEntity.ok(userService.listUsers(Sort.by(direction, properties),page,size));
        }catch (NoResultException ex){
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("user not found. -> ");
            stringBuilder.append(ex.getMessage());
            return ResponseEntity.ok(stringBuilder.toString());
        }
    }

    @DeleteMapping("/delete-user/{id}")
    public ResponseEntity<?> deleteUsers(@PathVariable("id") Integer ID){

        try {
            userService.deleteUsers(ID);
        }catch (NoResultException ex) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("user not found. -> ");
            stringBuilder.append(ex.getMessage());
            return ResponseEntity.ok(stringBuilder.toString());
        }

        return ResponseEntity.ok("User has been deleted ");
    }


    @PutMapping("/update-user/{id}")
    public ResponseEntity<?> updateUsers(@PathVariable("id") Integer userId, @RequestBody UserDto userDto){

        try {
            userService.updateUsers(userId,userDto);
        }catch (NoResultException ex){
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("user not found. -> ");
            stringBuilder.append(ex.getMessage());
            return ResponseEntity.ok(stringBuilder.toString());
        }

        return ResponseEntity.ok("The user has been updated ");

    }

}
