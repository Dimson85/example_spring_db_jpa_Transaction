package ru.learnup.db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.learnup.db.entity.Ticket;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket,Integer> {
    List<Ticket> getTicketsByTitlePremiereContains(String titlePremiere);
    Ticket getTicketByBuyersName(String buyersName);
    Ticket getTicketByTitlePremiereAndBuyersName(String titlePremiere, String buyersName);
}



