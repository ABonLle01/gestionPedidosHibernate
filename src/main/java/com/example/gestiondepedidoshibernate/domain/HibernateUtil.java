package com.example.gestiondepedidoshibernate.domain;

import lombok.extern.java.Log;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

@Log
public class HibernateUtil {
    private static SessionFactory sf = null;

    static{

        try{
            //configurar la conexion de hibernate
            Configuration cfg = new Configuration();
            cfg.configure();
            //se crea una conexion con el sf
            //sf debe ser unico en tod0 el proyecto
            sf = cfg.buildSessionFactory();
            log.info("SessionFactory creada con exito!");
        } catch (Exception e) {
            log.severe("Error al crear SessionFactory()");
        }


    }

    public static SessionFactory getSessionFactory(){
        return sf;
    }
}
