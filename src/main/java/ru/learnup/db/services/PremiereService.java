package ru.learnup.db.services;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;
import ru.learnup.db.entity.Premiere;
import ru.learnup.db.exceptions.NotFoundPremiereException;
import ru.learnup.db.exceptions.PremiereAlreadyExistsException;
import ru.learnup.db.model.PremiereModel;
import ru.learnup.db.repositories.PremiereRepository;
import ru.learnup.db.repositories.TicketRepository;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class PremiereService  {

    private ApplicationContext context;

    final PremiereRepository premiereRepository;
    final TicketRepository ticketRepository;


    @Autowired
    public PremiereService(PremiereRepository premiereRepository, TicketRepository ticketRepository) {
        this.premiereRepository = premiereRepository;
        this.ticketRepository = ticketRepository;
    }


//    @Override
//    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
//        this.context = applicationContext;
//    }

    public List<PremiereModel> getAllPremiere(){
        List<Premiere> premiereList = premiereRepository.getAllPremiere();
        return premiereList.stream()
                .map(PremiereModel::premiereToModel)
                .collect(Collectors.toList());
    }

    public void addPremiere(Premiere premiere) throws PremiereAlreadyExistsException {
        if (premiereRepository.findPremiereByTitle(premiere.getTitle()) != null) {
            throw new PremiereAlreadyExistsException("Премьера \'" + premiere.getTitle() +
                    "\' уже существует!");
        }
            premiereRepository.save(premiere);
        }


        public PremiereModel findPremiereByTitle(String title) throws NotFoundPremiereException {
            Premiere premiere = premiereRepository.findPremiereByTitle(title);
            if(premiere == null){
                throw new NotFoundPremiereException("Премьеры \'" + title +
                        "\' не существует!");
            }
            return PremiereModel.premiereToModel(premiere);
        }

        public String deletePremiere(String title) throws NotFoundPremiereException {
            Premiere premiere = premiereRepository.findPremiereByTitle(title);
            if(premiere == null){
                throw new NotFoundPremiereException("Премьеры \'" + title +
                        "\' не существует!");
            }
            premiereRepository.delete(premiere);
            return "Премьера удалена";
        }



    }



//
//    public void updatePremiere(Premiere premiere) {
//            premiereRepository.
//        }
////        premiereDao.updatePremiere(premiere);
//    }
//
//    public void deletePremiere(Premiere premiere) {
//        premiereDao.deletePremiereById(premiere.getId());
//    }
//
//
//    public void printAllPremiere() {
//        for (Premiere premiere : premiereDao.getAllPremiere()) {
//            System.out.println(premiere);
//        }
//    }
//
//    public void buyTicket(String titlePremiere, String buyerName) {
//        premiereDao.addTicket(titlePremiere, buyerName);
//    }
//
//    public void removeTicket(String titlePremiere, String buyerName) {
//        Ticket ticket = premiereDao.findTicket(titlePremiere, buyerName);
//        if (ticket != null) {
//            premiereDao.deleteTicket(ticket);
//            System.out.println("Билет возвращен.");
//        } else {
//            throw new IllegalArgumentException("Покупатель \'" + buyerName + "\' на премьеру \'" + titlePremiere + "\' не найден.");
//        }
//
//
//    }
//
//    public void printAllTicket() {
//        List<Ticket> allTicket = premiereDao.getAllTicket();
//        for (Ticket ticket : allTicket)
//            System.out.println(ticket);
//    }

