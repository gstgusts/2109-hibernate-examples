package com.company;

import com.sun.istack.NotNull;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;

public class CarDataRepository {
    private SessionFactory factory;

    public CarDataRepository() {
        try {
            factory = new Configuration().configure("com/company/hibernate/hibernate.cfg.xml").buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Failed to create session factory" + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public Iterable<Car> getList() {
        var session = factory.openSession();

        try {
            return session.createQuery("FROM Car").list();
        } catch (HibernateException exception) {
            System.err.println(exception);
        } finally {
            session.close();
        }

        return new ArrayList<>();
    }

    public Integer add(@NotNull Car car) {
        var session = factory.openSession();

        try {
            return (Integer) session.save(car);
        } catch (HibernateException exception) {
            System.err.println(exception);
        } finally {
            session.close();
        }

        return null;
    }

    public void update(@NotNull Car car) {
        if(car.getId() == 0) {
            throw new IllegalArgumentException("Car id must have a non 0 value");
        }

        var session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();

            session.update(car);

            tx.commit();
        } catch (HibernateException exception) {
            if(tx != null) {
                tx.rollback();
            }
        } finally {
            session.close();
        }
    }

    public Car get(Integer id) {
        var session = factory.openSession();

        try {
           return session.get(Car.class, id);
        } catch (HibernateException exception) {
            System.err.println(exception);
        } finally {
            session.close();
        }

        return null;
    }
}
