package org.example.app;

import org.example.model.Director;
import org.example.model.Movie;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.Objects;
import java.util.function.Consumer;

public class HibernateContext {

    private Configuration configuration;
    private  SessionFactory sessionFactory;


    public void initConfiguration() {
        configuration = new Configuration().addAnnotatedClass(Director.class).addAnnotatedClass(Movie.class);
        sessionFactory = configuration.buildSessionFactory();
    }

    public void runUnContext(Consumer<Session> transactionConsumer) {
        if (Objects.isNull(configuration)) {
            initConfiguration();
        }

        Session session = sessionFactory.getCurrentSession();
        try {
            Transaction transaction = session.beginTransaction();
            transactionConsumer.accept(session);
            transaction.commit();
        } finally {
            sessionFactory.close();
        }
    }
}
