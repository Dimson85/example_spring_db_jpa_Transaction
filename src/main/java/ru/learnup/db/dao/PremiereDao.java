package ru.learnup.db.dao;

import ru.learnup.db.entity.Premiere;


import java.util.List;

public interface PremiereDao {
    Premiere getPremiereById(Integer id);
    List<Premiere> getAllPremiere();
    boolean addPremiere(Premiere premiere);
    boolean updatePremiere(Premiere premiere);
    boolean deletePremiereById(Integer id);

}
