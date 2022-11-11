package com.eriko.dao;
/**
 * Eriko Agustino
 * 1972041
 * 6 January 2022
 */
import com.eriko.model.Anggota;
import com.eriko.model.Buku;
import com.eriko.util.DaoService;
import com.eriko.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class MemberDao implements DaoService<Anggota> {

    @Override
    public List<Anggota> FetchAllData() throws HibernateException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Anggota> criteriaQuery = criteriaBuilder.createQuery(Anggota.class);
        criteriaQuery.from(Anggota.class);
        List<Anggota> anggotaList = session.createQuery(criteriaQuery).getResultList();
        session.close();
        return anggotaList;
    }

    @Override
    public boolean AddData(Anggota anggota) throws HibernateException {
        boolean result = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.save(anggota);
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
