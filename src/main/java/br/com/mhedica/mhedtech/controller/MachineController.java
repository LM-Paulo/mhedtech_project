package br.com.mhedica.mhedtech.controller;


import br.com.mhedica.mhedtech.dto.MachineDto;
import br.com.mhedica.mhedtech.service.MachineService;
import jakarta.persistence.NoResultException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("api/machine")
public class MachineController {

    @Autowired
    private MachineService machineService;


    @PostMapping("/create-machine")
    public ResponseEntity<?> createMachine(@RequestBody MachineDto machineDto){
        machineService.createMachine(machineDto);
        return ResponseEntity.ok("machine created successfully");
    }


    @GetMapping("/list-machine")
    public ResponseEntity<?> listMachine    (@RequestParam("sort") Sort.Direction direction,
                                              @RequestParam("properties") String properties,
                                              @RequestParam("page") Integer page,
                                              @RequestParam("size") Integer size) {

            return ResponseEntity.ok(machineService.listMachine(Sort.by(direction, properties), page, size));

    }
}
