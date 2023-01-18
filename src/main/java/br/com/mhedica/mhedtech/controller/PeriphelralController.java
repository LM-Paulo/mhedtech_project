package br.com.mhedica.mhedtech.controller;

import br.com.mhedica.mhedtech.dto.PeriphelralDto;
import br.com.mhedica.mhedtech.repository.PeriphelralRepository;
import br.com.mhedica.mhedtech.service.PeriphelralService;
import jakarta.persistence.NoResultException;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("api/periphelral")
public class PeriphelralController {


    @Autowired
     private PeriphelralRepository periphelralRepository;

    @Autowired
    private PeriphelralService periphelralService;


    @PostMapping("/create-peripheral")
    public ResponseEntity<String> createPeripheral(@RequestBody PeriphelralDto periphelralDto) {

        try {
            System.out.println(periphelralDto.getConfig());
            System.out.println(periphelralDto.getModel());


            periphelralService.createPeripheral(periphelralDto);
            return ResponseEntity.ok("Peripheral created successfully!");
        } catch (Exception ex) {
            StringBuilder mensagemRetorno = new StringBuilder();
            mensagemRetorno.append("Unable to create peripheral ->");
            mensagemRetorno.append(ex.getMessage());
            return ResponseEntity.ok(mensagemRetorno.toString());
        }

    }


    @GetMapping("/peripheral-list")
    public ResponseEntity<?> peripheralList(@RequestParam("sort") Sort.Direction direction,
                                             @RequestParam("properties") String properties,
                                             @RequestParam("page") Integer page,
                                             @RequestParam("size") Integer size) {


        try {
            PeriphelralDto periphelralDto = new PeriphelralDto();
            return ResponseEntity.ok(periphelralService.peripheralList(Sort.by(direction, properties), page, size));

        } catch (NoResultException ex) {
            return ResponseEntity.ok("Peripherals not found.");
        }

    }

    @DeleteMapping("/delete-Peripheral/{id}")
    public ResponseEntity<?> deletePeripheral(@PathVariable("id") Integer ID) {
        try {
            periphelralService.deletePeripheral(ID);
        } catch (ObjectNotFoundException ex) {
            return ResponseEntity.ok(ex.getMessage());
        }
        return ResponseEntity.ok("Peripheral has been deleted ");

    }

}
