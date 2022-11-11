package com.eriko.dao;
/**
 * Eriko Agustino
 * 1972041
 * 6 January 2022
 */
import com.eriko.model.Anggota;
import com.eriko.model.Peminjaman;
import com.eriko.util.DaoService;
import com.eriko.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import sun.security.util.Pem;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class PeminjamanDao implements DaoService<Peminjaman> {

    @Override
    public List<Peminjaman> FetchAllData() throws HibernateException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Peminjaman> criteriaQuery = criteriaBuilder.createQuery(Peminjaman.class);
        Root<Peminjaman> peminjamanRoot = criteriaQuery.from(Peminjaman.class);
        peminjamanRoot.join("buku");
        peminjamanRoot.join("anggota");
        List<Peminjaman> peminjamenList = session.createQuery(criteriaQuery).getResultList();
        session.close();
        return peminjamenList;
    }

    @Override
    public boolean AddData(Peminjaman peminjaman) throws HibernateException {
        boolean result = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.save(peminjaman);
            transaction.commit();
            result = true;
        } catch (HibernateException ex){
            transaction.rollback();
            ex.printStackTrace();
        }
        session.close();

        return result;
    }

    public boolean UpdateData(Peminjaman peminjaman) throws HibernateException {
        boolean result = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.update(peminjaman);
            transaction.commit();
            result = true;
        } catch (HibernateException ex){
            transaction.rollback();
            ex.printStackTrace();
        }
        session.close();

        return result;
    }

    public boolean DeleteData(Peminjaman peminjaman) throws HibernateException {
        boolean result = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.delete(peminjaman);
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
