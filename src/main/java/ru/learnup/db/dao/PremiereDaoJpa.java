package ru.learnup.db.dao;

import org.springframework.stereotype.Component;
import ru.learnup.db.entity.Premiere;
import ru.learnup.db.entity.Ticket;
import ru.learnup.db.repositories.PremiereRepository;
import ru.learnup.db.repositories.TicketRepository;

import java.util.List;

@Component
public class PremiereDaoJpa implements PremiereDao, TicketDao {

    private final PremiereRepository premiereRepository;
    private final TicketRepository ticketRepository;


    public PremiereDaoJpa(PremiereRepository premiereRepository, TicketRepository ticketRepository) {
        this.premiereRepository = premiereRepository;
        this.ticketRepository = ticketRepository;
    }


    @Override
    public Premiere getPremiereById(Integer id) {
        Premiere premiere = premiereRepository.getOne(id);
        premiere.setTicketList(ticketRepository.getTicketsByTitlePremiereContains(premiere.getTitle()));
        return premiere;
    }

    @Override
    public List<Premiere> getAllPremiere() {
        return premiereRepository.findAll();
    }

    @Override
    public boolean addPremiere(Premiere premiere) {
        premiereRepository.save(premiere);
        return true;
    }

    @Override
    public boolean updatePremiere(Premiere premiere) {
        addPremiere(premiere);
        return true;
    }

    @Override
    public boolean deletePremiereById(Integer id) {
        premiereRepository.deleteById(id);
        return true;
    }


    public List<Ticket> getAllTicketByTitleContains(String title) {
        return ticketRepository.getTicketsByTitlePremiereContains(title);
    }


    @Override
    public List<Ticket> getAllTicket() {
        return ticketRepository.findAll();
    }



    public Ticket findTicket(String titlePremiere, String buyersName) {
        return ticketRepository.getTicketByTitlePremiereAndBuyersName(titlePremiere, buyersName);
    }

    public boolean addTicket(String title, String buyersName){
        Premiere premiere = premiereRepository.getPremiereByTitle(title);
        premiere.setTicketList(ticketRepository.getTicketsByTitlePremiereContains(title));
        Ticket ticket = new Ticket(premiere,buyersName);
        premiere.getTicketList().add(ticket);
        updatePremiere(premiere);
        return true;
    }

    @Override
    public boolean deleteTicket(Ticket ticket) {
        ticketRepository.delete(ticket);
        return true;
    }
}
