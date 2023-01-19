package br.com.mhedica.mhedtech.controller;


import br.com.mhedica.mhedtech.dto.MachineDto;
import br.com.mhedica.mhedtech.service.MachineService;
import jakarta.persistence.NoResultException;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/machine")
public class MachineController {


    static Logger logger = Logger.getLogger(MachineController.class.getName());
    @Autowired
    private MachineService machineService;


    @PostMapping("/create-machine")
    public ResponseEntity<?> createMachine(@RequestBody MachineDto machineDto){

        try {
            machineService.createMachine(machineDto);
        }catch (Exception ex){
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("don't successfully created machine -> ");
            stringBuilder.append(ex.getMessage());
            logger.log(Logger.Level.ERROR,stringBuilder);
            return ResponseEntity.ok(stringBuilder.toString());
        }

        return ResponseEntity.ok("machine created successfully");
    }


    @GetMapping("/list-machine")
    public ResponseEntity<?> listMachine    (@RequestParam("sort") Sort.Direction direction,
                                              @RequestParam("properties") String properties,
                                              @RequestParam("page") Integer page,
                                              @RequestParam("size") Integer size) {

        try {
            return ResponseEntity.ok(machineService.listMachine(Sort.by(direction, properties), page, size));
        }catch (NoResultException ex){
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Unable to list machines -> ");
            stringBuilder.append(ex.getMessage());
            logger.log(Logger.Level.ERROR,stringBuilder);
            return ResponseEntity.ok(stringBuilder.toString());
        }

    }
}
