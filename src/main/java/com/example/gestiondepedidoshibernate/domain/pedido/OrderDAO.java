package com.example.gestiondepedidoshibernate.domain.pedido;

import com.example.gestiondepedidoshibernate.domain.DAO;
import com.example.gestiondepedidoshibernate.domain.HibernateUtil;
import com.example.gestiondepedidoshibernate.domain.usuario.User;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class OrderDAO implements DAO<Order> {



    @Override
    public ArrayList<Order> getAll() {
        return null;
    }

    @Override
    public Order get(Long id) {
        var salida = new Order();

        try(Session s = HibernateUtil.getSessionFactory().openSession()){
            salida = s.get(Order.class, id);
        }

        return salida;
    }

    @Override
    public Order save(Order data) {
        return null;
    }

    @Override
    public void update(Order data) {

    }

    @Override
    public void delete(Order data) {

    }



    /*public List<Order> getDistinctFromAtribute(String attr) {

        ArrayList<String> results = new ArrayList<>(0);

        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            Query<Order> q = s.createQuery("from Order where id=:a", Order.class);
            q.setParameter("a",o.getId());
            results = (ArrayList<Order>) q.getResultList();
        }

        return results;
    }*/


}
