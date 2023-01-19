package br.com.mhedica.mhedtech.controller;

import br.com.mhedica.mhedtech.dto.PeriphelralDto;
import br.com.mhedica.mhedtech.exceptions.BusinessException;
import br.com.mhedica.mhedtech.service.PeriphelralService;
import jakarta.persistence.NoResultException;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/periphelral")
public class PeriphelralController {

    static org.jboss.logging.Logger logger = Logger.getLogger(PeriphelralController.class.getName());

    @Autowired
    private PeriphelralService periphelralService;


    @PostMapping("/create-peripheral")
    public ResponseEntity<String> createPeripheral(@RequestBody PeriphelralDto periphelralDto) {

        try {
            periphelralService.createPeripheral(periphelralDto);

        }catch (BusinessException ex){
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("could not create peripheral -> ");
            stringBuilder.append(ex.getMessage());
            logger.log(org.jboss.logging.Logger.Level.ERROR,stringBuilder);
            ResponseEntity.badRequest().body(stringBuilder);
        }

        return ResponseEntity.ok("Peripheral created successfully!");
    }


    @GetMapping("/peripheral-list")
    public ResponseEntity<?> peripheralList(@RequestParam("sort") Sort.Direction direction,
                                             @RequestParam("properties") String properties,
                                             @RequestParam("page") Integer page,
                                             @RequestParam("size") Integer size) {

        try {
            periphelralService.peripheralList(Sort.by(direction, properties), page, size);
        }catch (NoResultException ex){
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("unable to list peripherals -> ");
            stringBuilder.append(ex.getMessage());
            logger.log(org.jboss.logging.Logger.Level.ERROR,stringBuilder);
            ResponseEntity.badRequest().body(stringBuilder);
        }

        return ResponseEntity.ok("successfully listed");


    }

    @DeleteMapping("/delete-Peripheral/{id}")
    public ResponseEntity<?> deletePeripheral(@PathVariable("id") Integer ID) {

        try{
            periphelralService.deletePeripheral(ID);
        }catch (NoResultException ex){
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("id cannot be found -> ");
            stringBuilder.append(ex.getMessage());
            logger.log(org.jboss.logging.Logger.Level.ERROR,stringBuilder);
            ResponseEntity.badRequest().body(stringBuilder);
        }

        return ResponseEntity.ok("Peripheral has been deleted ");
    }

}
