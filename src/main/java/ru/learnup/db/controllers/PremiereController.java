package ru.learnup.db.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.learnup.db.entity.Premiere;
import ru.learnup.db.exceptions.NotFoundPremiereException;
import ru.learnup.db.exceptions.PremiereAlreadyExistsException;
import ru.learnup.db.services.PremiereService;

@RestController
@RequestMapping("premieres")
public class PremiereController {

    final PremiereService premiereService;

    @Autowired
    public PremiereController(PremiereService premiereService) {
        this.premiereService = premiereService;
    }


    @GetMapping
    public ResponseEntity getPremieres(){
        try {
            return ResponseEntity.ok(premiereService.getAllPremiere());
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Ошибка подключеия");
        }
    }



    @PostMapping
    public ResponseEntity createPremiere(@RequestBody Premiere premiere){
        try {
            premiereService.addPremiere(premiere);
            return ResponseEntity.ok("Премьера успешно создана!");
        }catch (PremiereAlreadyExistsException e){
        return ResponseEntity.badRequest().body(e.getMessage());
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Ошибка подключеия...");
        }
    }


    @GetMapping("/find")
    public ResponseEntity findPremiereByTitles(@RequestParam String title){
        try {
            return ResponseEntity.ok(premiereService.findPremiereByTitle(title));
        }catch (NotFoundPremiereException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Ошибка подключеия");
        }
    }

    @DeleteMapping("/{title}")
    public ResponseEntity deletePremiere(@PathVariable String title){
        try {
            return ResponseEntity.ok(premiereService.deletePremiere(title));
        }catch (NotFoundPremiereException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Ошибка подключеия");
        }
    }



}
