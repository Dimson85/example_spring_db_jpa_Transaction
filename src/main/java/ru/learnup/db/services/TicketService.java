package ru.learnup.db.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.learnup.db.entity.Premiere;
import ru.learnup.db.entity.Ticket;
import ru.learnup.db.exceptions.NotFoundPremiereException;
import ru.learnup.db.exceptions.NotFoundTicketException;
import ru.learnup.db.model.TicketModel;
import ru.learnup.db.repositories.PremiereRepository;
import ru.learnup.db.repositories.TicketRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TicketService {

    PremiereRepository premiereRepository;
    TicketRepository ticketRepository;

    @Autowired
    public TicketService(PremiereRepository premiereRepository, TicketRepository ticketRepository) {
        this.premiereRepository = premiereRepository;
        this.ticketRepository = ticketRepository;
    }

    public List<TicketModel> getAllTickets(){
      return   ticketRepository.findAll().stream()
                .map(TicketModel::TicketToModel)
                .collect(Collectors.toList());
    }

    public TicketModel buyTicket(Ticket ticket) throws NotFoundPremiereException {
        Premiere premiere = premiereRepository.findPremiereByTitle(ticket.getTitlePremiere());
        if (premiere == null) {
            throw new NotFoundPremiereException("Премьеры \'" + ticket.getTitlePremiere() +
                    "\' не существует!");
        }
        ticket.setPremiere(premiere);
        return TicketModel.TicketToModel(ticketRepository.save(ticket));
    }

    public TicketModel removeTicket(String title, String name) throws NotFoundTicketException {
        Ticket ticket = ticketRepository.getTicketByTitlePremiereAndBuyersName(title, name);
        if (ticket == null) {
            throw new NotFoundTicketException("Билет с такими данными не найден.");
        }
        ticketRepository.delete(ticket);
        return TicketModel.TicketToModel(ticket);
    }


}
