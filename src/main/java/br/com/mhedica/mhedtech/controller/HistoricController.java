package br.com.mhedica.mhedtech.controller;

import br.com.mhedica.mhedtech.dto.HistoricDto;
import br.com.mhedica.mhedtech.repository.HistoricRepository;
import br.com.mhedica.mhedtech.service.HistoricService;
import jakarta.persistence.NoResultException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("api/history")
public class HistoricController {

    @Autowired
    private HistoricRepository historicRepository;
    @Autowired
    private HistoricService historicService;


    @PostMapping("/create-history")
    public ResponseEntity<String> createHistory(@RequestBody HistoricDto historicDto){

        try {
            System.out.println(historicDto.getDescription());

            historicService.createHistory(historicDto);
            return ResponseEntity.ok("History created successfully!");
        }catch (Exception ex){
            return ResponseEntity.ok("Unable to create history");
        }



    }


    @GetMapping("/historical-list")
    public ResponseEntity<?> historicallist    (@RequestParam("sort") Sort.Direction direction,
                                                @RequestParam("properties") String properties,
                                                @RequestParam("page") Integer page,
                                                @RequestParam("size") Integer size) {


        try {
            HistoricDto historicDto = new HistoricDto();
            return ResponseEntity.ok(historicService.historicallist(Sort.by(direction, properties), page, size));

        } catch (NoResultException ex) {
            return ResponseEntity.ok("Records not found.");
        }

    }


}
