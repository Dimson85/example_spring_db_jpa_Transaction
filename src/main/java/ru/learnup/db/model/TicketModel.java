package ru.learnup.db.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.learnup.db.entity.Ticket;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class TicketModel {
    String buyersName;
    String titlePremiere;


    public static TicketModel TicketToModel(Ticket ticket){
        TicketModel ticketModel = new TicketModel();
     ticketModel.setTitlePremiere(ticket.getTitlePremiere());
     ticketModel.setBuyersName(ticket.getBuyersName());
     return ticketModel;
    }
}
