package ru.learnup.db.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.learnup.db.entity.Ticket;
import ru.learnup.db.exceptions.NotFoundPremiereException;
import ru.learnup.db.exceptions.NotFoundTicketException;
import ru.learnup.db.services.TicketService;

@RestController
@RequestMapping("/tickets")
public class TicketController {

    final private TicketService ticketService;

    @Autowired
    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping
    public ResponseEntity getTickets(){
        try {
            return ResponseEntity.ok(ticketService.getAllTickets());
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Ошибка подключения");
        }
    }

    @PostMapping
    public ResponseEntity buyTicket(@RequestBody Ticket ticket){
        try {
           return ResponseEntity.ok(ticketService.buyTicket(ticket));
        }catch (NotFoundPremiereException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Ошибка подключения");
        }
    }

    @DeleteMapping
    public ResponseEntity removeTicket(@RequestParam String titlePremiere, String buyersName){
        try {
            return ResponseEntity.ok(ticketService.removeTicket(titlePremiere,buyersName));
        }catch (NotFoundTicketException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Ошибка подключения");
        }
    }


}
