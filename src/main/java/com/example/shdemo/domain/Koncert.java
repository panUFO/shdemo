package com.example.shdemo.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@NamedQueries({
        @NamedQuery(name = "koncert.getAll", query = "Select k from Koncert k"),
        @NamedQuery(name = "koncert.getByID", query = "Select k from Koncert k where k.idKoncert = :idKoncert"),
        @NamedQuery(name = "koncert.getByIdKlub", query = "Select k from Koncert k where k.klub = :idKlub"),
})

public class Koncert implements Serializable
{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idKoncert;

    private String nazwa_koncertu;
    private double ceny_biletow;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "idKlub")
    private Klub klub;


    public Long getIdKoncert() {
        return idKoncert;
    }

    public void setIdKoncert(Long idKoncert) {
        this.idKoncert = idKoncert;
    }

    public String getNazwa_koncertu() {
        return nazwa_koncertu;
    }

    public void setNazwa_koncertu(String nazwa_koncertu) {
        this.nazwa_koncertu = nazwa_koncertu;
    }

    public double getCeny_biletow() {
        return ceny_biletow;
    }

    public void setCeny_biletow(double ceny_biletow) {
        this.ceny_biletow = ceny_biletow;
    }

    public Klub getKlub() {
        return klub;
    }
    public void setKlub(Klub klub) {
        this.klub = klub;
    }

}