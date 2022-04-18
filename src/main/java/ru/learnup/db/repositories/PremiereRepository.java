package ru.learnup.db.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.learnup.db.entity.Premiere;

import java.util.List;

public interface PremiereRepository extends CrudRepository<Premiere,Integer> {

    Premiere findPremiereByTitle(String title);
    String deleteByTitle(String title);

    @Query("select p from Premiere p")
    List<Premiere> getAllPremiere();

}


