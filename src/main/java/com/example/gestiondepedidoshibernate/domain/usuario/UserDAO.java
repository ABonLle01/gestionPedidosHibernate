package com.example.gestiondepedidoshibernate.domain.usuario;

import com.example.gestiondepedidoshibernate.domain.DAO;
import com.example.gestiondepedidoshibernate.domain.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class UserDAO implements DAO<User> {

    @Override
    public ArrayList<User> getAll() {
        var salida = new ArrayList<User>(0);

        try(Session s = HibernateUtil.getSessionFactory().openSession()){
            Query<User> q = s.createQuery("from User", User.class);
            salida = (ArrayList<User>) q.getResultList();
        }

        return salida;
    }

    @Override
    public List<User> getAllByUserId(Long userId) {
        return null;
    }

    @Override
    public User get(Long id) {
        var salida = new User();

        try(Session s = HibernateUtil.getSessionFactory().openSession()){
            salida = s.get(User.class, id);
        }

        return salida;
    }

    @Override
    public User save(User data) {
        return null;
    }

    @Override
    public void update(User data) {

    }

    @Override
    public void delete(User data) {

    }

    public User validateUser(String email, String password){
        User result=null;

        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            Query<User> q = session.createQuery("from User where email=:e and password=:p", User.class);
            q.setParameter("e",email);
            q.setParameter("p",password);

            try{
                result = q.getSingleResult();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        }
        return result;

    }


}