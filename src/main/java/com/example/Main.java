package com.example;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.entity.Event;

@SpringBootApplication
public class Main implements CommandLineRunner {

    private SessionFactory sessionFactory;

    public static void main(String[] args) 
    {
        SpringApplication.run(Main.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        setUp();
        saveEntity();
    }
    
    /**
     * https://docs.jboss.org/hibernate/orm/current/quickstart/html_single/#hibernate-gsg-tutorial-basic-test
     * → Example 4
     */
    protected void setUp() throws Exception {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();
        try {
            sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
        } catch (Exception e) {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
    
    /**
     * https://docs.jboss.org/hibernate/orm/current/quickstart/html_single/#hibernate-gsg-tutorial-basic-test
     * → Example 5
     * The method save(...) is deprecated in Hibernate 6 and replaced with the method persist(...).
     */
    protected void saveEntity() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.persist( new Event( "Our very first event!", new Date() ) );
        session.getTransaction().commit();
        session.close();
    }


}