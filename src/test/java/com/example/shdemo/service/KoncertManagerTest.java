package com.example.shdemo.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import com.example.shdemo.domain.Klub;
import com.example.shdemo.domain.Koncert;
import com.example.shdemo.service.KlubDAO;
import com.example.shdemo.service.KoncertDAO;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/beans.xml" })
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = true)
@Transactional
public class KoncertManagerTest {


    @Autowired
    KlubDAO klubManager;

    @Autowired
    KoncertDAO koncertManager;


    private final String MIASTO_1 = "Gdansk";
    private final String NAZWA_1 = "B90";
    private final int ILOSCM_1 = 2000;
    private final String MIASTO_2 = "Warszawa";
    private final String NAZWA_2 = "Progresja";
    private final int ILOSCM_2 = 1000;



    private final String NAZWA_KONCERTU1 = "koncert1";
    private final double CENY_BILETOW1 = 100;
    private final String NAZWA_KONCERTU2 = "koncert2";
    private final double CENY_BILETOW2 = 15;


    Klub klub = new Klub();

    Klub firstToDelete, secondToDelete;
/*
    @Before
    public void addExamplesKoncerts()
    {
        Klub firstToDelete = new Klub();
        klub.setMiasto(MIASTO_1);
        klub.setNazwa(NAZWA_1);
        klub.setIlosc_miejsc(ILOSCM_1);
        klubManager.addKlub(firstToDelete);

        Klub secondToDelete = new Klub();
        klub.setMiasto(MIASTO_2);
        klub.setNazwa(NAZWA_2);
        klub.setIlosc_miejsc(ILOSCM_2);
        klubManager.addKlub(secondToDelete);

    }
    @After
    public void deleteExamplesKoncerts()
    {
        klubManager.deleteKlub(firstToDelete);
        klubManager.deleteKlub(secondToDelete);

    }
*/


    @Test
    public void addKoncertTest() {

        Klub klub = new Klub();
        klub.setMiasto(MIASTO_1);
        klub.setNazwa(NAZWA_1);
        klub.setIlosc_miejsc(ILOSCM_1);

        klubManager.addKlub(klub);
        assertNotNull(klub);

        Koncert koncert = new Koncert();
        koncert.setNazwa_koncertu(NAZWA_KONCERTU1);
        koncert.setCeny_biletow(CENY_BILETOW1);
        koncert.setKlub(klub);
        koncertManager.addKoncert(koncert);
        assertNotNull(koncert);
        assertEquals(klub.getNazwa(), koncert.getKlub().getNazwa());

    }

    @Test
    public void getKoncertByIDTest() {
        Klub klub = new Klub();
        klub.setMiasto(MIASTO_1);
        klub.setNazwa(NAZWA_1);
        klub.setIlosc_miejsc(ILOSCM_1);
        assertNotNull(klub);

        Koncert koncert = new Koncert();
        koncert.setNazwa_koncertu(NAZWA_KONCERTU1);
        koncert.setCeny_biletow(CENY_BILETOW1);
        koncert.setKlub(klub);
        koncertManager.addKoncert(koncert);
        assertNotNull(koncert);

        Koncert koncert1 = koncertManager.getAllKoncerts().get(1);
        Koncert receivedKoncert = koncertManager.getKoncertByID(koncert1.getIdKoncert());
        assertEquals(koncert1.getIdKoncert(), receivedKoncert.getIdKoncert());
    }


    @Test
    public void updateKoncertTest() {
        Klub klub = new Klub();
        klub.setMiasto(MIASTO_1);
        klub.setNazwa(NAZWA_1);
        klub.setIlosc_miejsc(ILOSCM_1);
        assertNotNull(klub);

        Koncert koncert = new Koncert();
        koncert.setNazwa_koncertu(NAZWA_KONCERTU1);
        koncert.setCeny_biletow(CENY_BILETOW1);
        koncert.setKlub(klub);
        koncertManager.addKoncert(koncert);
        assertNotNull(koncert);

        /*Koncert koncert = koncertManager.getAllKoncerts().get(0);
        assertNotNull(koncert);*/
        Klub newKlub = new Klub();
        newKlub.setMiasto(MIASTO_2);
        newKlub.setNazwa(NAZWA_2);
        newKlub.setIlosc_miejsc(ILOSCM_2);

        klubManager.addKlub(newKlub);
        assertNotNull(newKlub);

        koncert.setKlub(newKlub);
        koncertManager.updateKoncert(koncert);

        assertEquals(koncertManager.getAllKoncerts().get(0).getKlub().getNazwa(), newKlub.getNazwa());
    }
/*
    @Test
    public void deleteKoncertTest() {
        List<Koncert> listKoncert = koncertManager.getAllKoncerts();
        int nrKoncerts = listKoncert.size();
        Koncert retrievedKoncert = listKoncert.get(nrKoncerts -1);

        List<Klub> listKlubs = klubManager.getAllKlubs();


        for (Klub ko : listKlubs) {

            int index = -1;

            for (Koncert kl : ko.getKoncerts()) {
                if (kl == retrievedKoncert) {
                    index = ko.getKoncerts().indexOf(kl);
                    break;
                }
            }

            if (index != -1)
                ko.getKoncerts().remove(index);
        }

        koncertManager.deleteKoncert(retrievedKoncert);
        assertEquals(nrKoncerts - 1, koncertManager.getAllKoncerts().size());
        List<Koncert> listKoncertsAfterDelete = koncertManager.getAllKoncerts();
        assertTrue(!listKoncertsAfterDelete.contains(retrievedKoncert));

        for (Koncert ko : listKoncertsAfterDelete) {
            assertTrue(listKoncert.contains(ko));
        }
    }*/

    @Test
    public void deleteKoncertTest() {
        Klub klub = new Klub();
        klub.setMiasto(MIASTO_1);
        klub.setNazwa(NAZWA_1);
        klub.setIlosc_miejsc(ILOSCM_1);
        assertNotNull(klub);

        Koncert koncert = new Koncert();
        koncert.setNazwa_koncertu(NAZWA_KONCERTU1);
        koncert.setCeny_biletow(CENY_BILETOW1);
        koncert.setKlub(klub);
        koncertManager.addKoncert(koncert);
        assertNotNull(koncert);

        List<Koncert> listKoncert = koncertManager.getAllKoncerts();
        int nrKoncerts = listKoncert.size();
        Koncert retrievedKoncert = listKoncert.get(nrKoncerts -1);
        assertNotNull(retrievedKoncert);

        koncertManager.deleteKoncert(retrievedKoncert);
        assertEquals(nrKoncerts -1, koncertManager.getAllKoncerts().size());
    }


    @Test
    public void getKoncertByKlubIDTest() {
        Klub klub = klubManager.getAllKlubs().get(klubManager.getAllKlubs().size() - 1);
        Koncert koncert = koncertManager.getAllKoncerts().get(koncertManager.getAllKoncerts().size() - 1);

        assertEquals(klub.getIdKlub(), koncert.getKlub().getIdKlub());

        List<Koncert> klubKoncert = koncertManager.getKoncertByKlubID(klub);

        assertTrue(klubKoncert.contains(klub));

        for (Koncert ko : klubKoncert) {
            if (ko.getIdKoncert() == koncert.getIdKoncert()) {

                assertEquals(ko.getKlub().getIdKlub(), koncert.getKlub().getIdKlub());
            }
        }

    }

}
