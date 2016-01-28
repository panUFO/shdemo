package com.example.shdemo.service;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import com.example.shdemo.domain.Klub;
import com.example.shdemo.service.KlubDAO;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;


import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/beans.xml" })
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = true)
@Transactional
public class KlubManagerTest {

    @Autowired
    KlubDAO klubManager;



    private final String MIASTO_1 = "Gdansk";
    private final String NAZWA_1 = "B90";
    private final int ILOSCM_1 = 2000;

    private final String MIASTO_2 = "Warszawa";
    private final String NAZWA_2 = "Progresja";
    private final int ILOSCM_2 = 1500;


    Klub firstToDelete, secondToDelete;

    @Before
    public void addExamplesKlubs()
    {
        firstToDelete = new Klub();
        firstToDelete.setMiasto("Ozarow");
        firstToDelete.setNazwa("Protector");
        firstToDelete.setIlosc_miejsc(99);
        klubManager.addKlub(firstToDelete);


        secondToDelete = new Klub();
        secondToDelete.setMiasto("Sandomierz");
        secondToDelete.setNazwa("Bajlando");
        secondToDelete.setIlosc_miejsc(500);
        klubManager.addKlub(secondToDelete);
    }

    @After
    public void deleteExamplesKlubs()
    {
        klubManager.deleteKlub(firstToDelete);
        klubManager.deleteKlub(secondToDelete);

    }


    @Test
    public void addKlubTest() {
        Klub klub = new Klub();
        klub.setMiasto(MIASTO_1);
        klub.setNazwa(NAZWA_1);
        klub.setIlosc_miejsc(ILOSCM_1);
        klubManager.addKlub(klub);

        assertNotNull(klub);
        assertEquals(klub.getMiasto(), MIASTO_1);
        assertEquals(klub.getNazwa(),NAZWA_1);
        assertEquals(klub.getIlosc_miejsc(), ILOSCM_1);

        List<Klub> allKlubs = klubManager.getAllKlubs();
        Klub retrieviedKlubs = allKlubs.get(allKlubs.size() - 1);

        assertNotNull(klub);
        assertEquals(MIASTO_1, retrieviedKlubs.getMiasto());
        assertEquals(NAZWA_1, retrieviedKlubs.getNazwa());
        assertEquals(ILOSCM_1, retrieviedKlubs.getIlosc_miejsc());
    }


    @Test
    public void getKlubByIDTest() {
        Klub klub = new Klub();
        klub.setMiasto(MIASTO_2);
        klub.setNazwa(NAZWA_2);
        klub.setIlosc_miejsc(ILOSCM_2);
        klubManager.addKlub(klub);

        assertNotNull(klub);
        assertEquals(klub.getMiasto(), MIASTO_2);
        assertEquals(klub.getNazwa(),NAZWA_2);
        assertEquals(klub.getIlosc_miejsc(), ILOSCM_2);

        Klub fklub = klubManager.getAllKlubs().get(0);
        Klub receivedKlub = klubManager.getKlubByID(fklub.getIdKlub());
        assertEquals(fklub.getIdKlub(), receivedKlub.getIdKlub());
    }


    @Test
    public void updateKlubTest() {
        Klub klub = new Klub();
        klub.setMiasto(MIASTO_1);
        klub.setNazwa(NAZWA_1);
        klub.setIlosc_miejsc(ILOSCM_1);
        klubManager.addKlub(klub);

        Klub updateKlub = klubManager.getAllKlubs().get(0);

        updateKlub.setMiasto(MIASTO_2);
        updateKlub.setNazwa(NAZWA_2);
        updateKlub.setIlosc_miejsc(ILOSCM_2);

        klubManager.updateKlub(updateKlub);
        assertEquals(klubManager.getAllKlubs().get(0).getMiasto(), MIASTO_2);
        assertEquals(klubManager.getAllKlubs().get(0).getNazwa(), NAZWA_2);
        assertEquals(klubManager.getAllKlubs().get(0).getIlosc_miejsc(), ILOSCM_2);
    }


    @Test
    public void deleteKlubTest() {
        Klub klub = new Klub();
        klub.setMiasto(MIASTO_1);
        klub.setNazwa(NAZWA_1);
        klub.setIlosc_miejsc(ILOSCM_1);
        klubManager.addKlub(klub);

        List<Klub> Klublist = klubManager.getAllKlubs();
        int n = Klublist.size();

        Klub retrievedKlub = klubManager.getAllKlubs().get(klubManager.getAllKlubs().size() -1);
        klubManager.deleteKlub(retrievedKlub);

        assertEquals(n - 1, klubManager.getAllKlubs().size());

        List<Klub>KlubafterDel = klubManager.getAllKlubs();

        assertTrue(!KlubafterDel.contains(retrievedKlub));

        for (Klub k : KlubafterDel){
            assertTrue(Klublist.contains(k));
        }

    }

}
