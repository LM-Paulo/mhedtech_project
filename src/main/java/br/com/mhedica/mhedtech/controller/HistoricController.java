package br.com.mhedica.mhedtech.controller;

import br.com.mhedica.mhedtech.dto.HistoricDto;
import br.com.mhedica.mhedtech.service.HistoricService;
import jakarta.persistence.NoResultException;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/history")
public class HistoricController {

    static Logger logger = Logger.getLogger(HistoricController.class.getName());
    @Autowired
    private HistoricService historicService;


    @PostMapping("/create-history")
    public ResponseEntity<String> createHistory(@RequestBody HistoricDto historicDto){

        try {
            historicService.createHistoric(historicDto);
        }catch (Exception ex){
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Unable to create history. -> ");
            stringBuilder.append(ex.getMessage());
            logger.log(Logger.Level.ERROR,stringBuilder);
            return ResponseEntity.ok(stringBuilder.toString());
        }
        return ResponseEntity.ok("History created successfully!");
    }


    @GetMapping("/historical-list")
    public ResponseEntity<?> historicallist(@RequestParam("sort") Sort.Direction direction,
                                            @RequestParam("properties") String properties,
                                            @RequestParam("page") Integer page,
                                            @RequestParam("size") Integer size) {


        try {
            return ResponseEntity.ok(historicService.historicallist(Sort.by(direction, properties), page, size));

        } catch (NoResultException ex) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Records not found. -> ");
            stringBuilder.append(ex.getMessage());
            logger.log(Logger.Level.ERROR,stringBuilder);
            return ResponseEntity.ok(stringBuilder.toString());
        }

    }


}
