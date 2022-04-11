package ru.learnup.db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.learnup.db.entity.Premiere;
import ru.learnup.db.entity.Ticket;

import java.util.List;

public interface PremiereRepository extends JpaRepository<Premiere,Integer> {

    Premiere getPremiereByTitle(String title);
}


