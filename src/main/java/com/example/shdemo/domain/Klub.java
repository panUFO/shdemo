package com.example.shdemo.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "Klub")
@NamedQueries({
        @NamedQuery(name = "klub.getAll", query = "Select k from Klub k"),
        @NamedQuery(name = "klub.getByID", query = "Select k from Klub k where k.idKlub = :idKlub"),
})

public class Klub implements Serializable
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idKlub;

    private String miasto;
    private String nazwa;
    private int ilosc_miejsc;


    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "klub")
    private List<Koncert> koncerts;

    public Klub() {
    }

    public Klub(String miasto, String nazwa, int ilosc_miejsc) {
        this.miasto = miasto;
        this.nazwa = nazwa;
        this.ilosc_miejsc = ilosc_miejsc;
    }


    public Long getIdKlub() {
        return idKlub;
    }

    public void setIdKlub(Long idKlub) {
        this.idKlub = idKlub;
    }

    public String getMiasto() {
        return miasto;
    }

    public void setMiasto(String miasto) {
        this.miasto = miasto;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public int getIlosc_miejsc() {
        return ilosc_miejsc;
    }

    public void setIlosc_miejsc(int ilosc_miejsc) {
        this.ilosc_miejsc = ilosc_miejsc;
    }



    public List<Koncert> getKoncerts() {
        return koncerts;
    }
    public void setKoncerts(List<Koncert> koncerts) {
        this.koncerts = koncerts;
    }

}
