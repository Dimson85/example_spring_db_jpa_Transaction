package ru.learnup.db.model;
import lombok.*;
import ru.learnup.db.entity.Premiere;
import ru.learnup.db.entity.Ticket;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class PremiereModel {
    private String title;
    private String description;
    private String ageLimit;
    private int numberOfSeats;
    private List<TicketModel> ticketList;

    public static PremiereModel premiereToModel(Premiere premiere){
        PremiereModel premiereModel = new PremiereModel();
        premiereModel.setTitle(premiere.getTitle());
        premiereModel.setDescription(premiere.getDescription());
        premiereModel.setAgeLimit(premiere.getAgeLimit());
        premiereModel.setNumberOfSeats(premiere.getNumberOfSeats());
        premiereModel.setTicketList(premiere.getTicketList().stream()
                                    .map(t->TicketModel.TicketToModel(t))
                                    .collect(Collectors.toList()));
        return premiereModel;
    }

}
