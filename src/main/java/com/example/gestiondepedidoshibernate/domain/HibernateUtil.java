package com.example.gestiondepedidoshibernate.domain;

import lombok.extern.java.Log;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

@Log
public class HibernateUtil {
    private static SessionFactory sf = null;

    static{

        try{
            Configuration cfg = new Configuration();
            cfg.configure();

            sf = cfg.buildSessionFactory();
            log.info("SessionFactory creada con exito!");
        } catch (Exception e) {
            log.severe("Error al crear SessionFactory");
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

    }

    public static SessionFactory getSessionFactory(){
        return sf;
    }
}
