package ru.learnup.db.dao;

import ru.learnup.db.entity.Premiere;
import ru.learnup.db.entity.Ticket;

import java.util.List;

public interface TicketDao {
    List<Ticket> getAllTicket();
    boolean addTicket(String title, String buyersName);
    boolean deleteTicket(Ticket ticket);

}
