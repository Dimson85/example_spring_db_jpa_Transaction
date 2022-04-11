package ru.learnup.db.services;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import ru.learnup.db.dao.PremiereDaoJpa;
import ru.learnup.db.entity.Premiere;
import ru.learnup.db.entity.Ticket;

import java.io.EOFException;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.transaction.annotation.Propagation.REQUIRED;

@Service
public class EventService implements ApplicationContextAware {

    private ApplicationContext context;
    final PremiereDaoJpa premiereDao;

    public EventService(PremiereDaoJpa premiereDao) {
        this.premiereDao = premiereDao;

    }


    @Transactional(
            propagation = REQUIRED,
            isolation = Isolation.DEFAULT,
            rollbackFor = {EOFException.class, FileNotFoundException.class},
            noRollbackFor = IllegalArgumentException.class,
            timeout = 5,
            readOnly = false)
    public void demoTransaction() {

        Premiere premiere = new Premiere("Армагедон", "Про армагедон", "18+", 50);
        List<Ticket> ticketList = new ArrayList<>();
        ticketList.add(new Ticket(premiere, "Александр"));
        premiere.setTicketList(ticketList);
        addPremiere(premiere);

        System.out.println("Добавили Премьеру");

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
        }

        System.out.println("Меняем премьеру");


        premiere.setDescription("Про Кармен1");
        updatePremiere(premiere);

        //  Невалидное изменение
        premiere.setTitle("Кармен2");
        updatePremiere(premiere);

//        System.out.println("Удаляем премьеру.");
//        deletePremiere(premiere);
    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;

    }

    public void addPremiere(Premiere premiere) {
        premiereDao.addPremiere(premiere);
    }

    public void updatePremiere(Premiere premiere) {
        premiereDao.updatePremiere(premiere);
    }

    public void deletePremiere(Premiere premiere) {
        premiereDao.deletePremiereById(premiere.getId());
    }


    public void printAllPremiere() {
        for (Premiere premiere : premiereDao.getAllPremiere()) {
            System.out.println(premiere);
        }
    }

    public void buyTicket(String titlePremiere, String buyerName) {
        premiereDao.addTicket(titlePremiere, buyerName);
    }

    public void removeTicket(String titlePremiere, String buyerName) {
        Ticket ticket = premiereDao.findTicket(titlePremiere, buyerName);
        if (ticket != null) {
            premiereDao.deleteTicket(ticket);
            System.out.println("Билет возвращен.");
        } else {
            throw new IllegalArgumentException("Покупатель \'" + buyerName + "\' на премьеру \'" + titlePremiere + "\' не найден.");
        }


    }

    public void printAllTicket() {
        List<Ticket> allTicket = premiereDao.getAllTicket();
        for (Ticket ticket : allTicket)
            System.out.println(ticket);
    }
}
