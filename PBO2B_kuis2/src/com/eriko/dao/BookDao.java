package com.eriko.dao;
/**
 * Eriko Agustino
 * 1972041
 * 6 January 2022
 */
import com.eriko.model.Buku;
import com.eriko.util.DaoService;
import com.eriko.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class BookDao implements DaoService<Buku> {

    @Override
    public List<Buku> FetchAllData() throws HibernateException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Buku> criteriaQuery = criteriaBuilder.createQuery(Buku.class);
        criteriaQuery.from(Buku.class);
        List<Buku> bukuList = session.createQuery(criteriaQuery).getResultList();
        session.close();
        return bukuList;
    }

    @Override
    public boolean AddData(Buku buku) throws HibernateException {
        boolean result = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.save(buku);
            transaction.commit();
            result = true;
        } catch (HibernateException ex){
            transaction.rollback();
            ex.printStackTrace();
        }
        session.close();

        return result;
    }
}
