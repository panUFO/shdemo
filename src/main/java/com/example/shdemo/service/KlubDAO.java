package com.example.shdemo.service;


import com.example.shdemo.domain.Klub;

import java.util.List;

public interface KlubDAO {
    void addKlub(Klub klub);
    List<Klub> getAllKlubs();
    Klub getKlubByID(Long id);
    void updateKlub(Klub klub);
    void deleteKlub(Klub klub);

}
