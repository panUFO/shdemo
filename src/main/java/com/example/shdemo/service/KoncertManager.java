package com.example.shdemo.service;


import com.example.shdemo.domain.Klub;
import com.example.shdemo.domain.Koncert;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class KoncertManager implements KoncertDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addKoncert(Koncert koncert){

        koncert.setIdKoncert(null);
        sessionFactory.getCurrentSession().persist(koncert);
    }

    @Override
    public List<Koncert> getAllKoncerts(){
        return sessionFactory.getCurrentSession().getNamedQuery("koncert.getAll").list();

    }
    @Override
    public Koncert getKoncertByID(Long id){
        return (Koncert) sessionFactory.getCurrentSession().getNamedQuery("koncert.getByID").setLong("idKoncert",id).uniqueResult();

    }
    @Override
    public void deleteKoncert(Koncert koncert){
        koncert = (Koncert) sessionFactory.getCurrentSession().get(Koncert.class, koncert.getIdKoncert());


        sessionFactory.getCurrentSession().delete(koncert);
    }
    @Override
    public void updateKoncert(Koncert koncert){
        sessionFactory.getCurrentSession().update(koncert);

    }

    @Override
    public List<Koncert> getKoncertByKlubID(Klub klub){
        return sessionFactory.getCurrentSession().getNamedQuery("koncert.getByIdKlub").setLong("idKlub", klub.getIdKlub()).list();

    }

}
