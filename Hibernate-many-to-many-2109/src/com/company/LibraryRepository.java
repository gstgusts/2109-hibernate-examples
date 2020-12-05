package com.company;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import javax.persistence.criteria.CriteriaBuilder;

public class LibraryRepository {
    private SessionFactory factory;

    public LibraryRepository() {
        try {
            factory = new Configuration().configure("com/company/hibernate/hibernate.cfg.xml").buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Failed to create session factory" + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public Integer addBook(Book book) {
        var session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            var id = (Integer) session.save(book);
            tx.commit();
            return id;
        } catch (HibernateException exception) {
            if(tx != null) {
                tx.rollback();
            }
            System.err.println(exception);
        } finally {
            session.close();
        }

        return null;
    }
}
