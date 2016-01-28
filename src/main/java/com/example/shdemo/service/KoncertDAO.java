package com.example.shdemo.service;

import com.example.shdemo.domain.Klub;
import com.example.shdemo.domain.Koncert;

import java.util.List;

public interface KoncertDAO {


    void addKoncert(Koncert koncert);
    List<Koncert> getAllKoncerts();
    Koncert getKoncertByID(Long id);
    void updateKoncert(Koncert koncert);
    void deleteKoncert(Koncert koncert);
    List<Koncert> getKoncertByKlubID(Klub klub);


}
