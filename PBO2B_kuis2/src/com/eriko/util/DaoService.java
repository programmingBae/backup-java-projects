package com.eriko.util;

/**
 * Eriko Agustino
 * 1972041
 * 6 January 2022
 */

import org.hibernate.HibernateException;

import java.sql.SQLException;
import java.util.List;

public interface DaoService<T> {
    List<T> FetchAllData() throws HibernateException;
    boolean AddData(T t) throws  HibernateException;
}
