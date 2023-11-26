package com.example.gestiondepedidoshibernate.domain.pedido;

import com.example.gestiondepedidoshibernate.domain.DAO;
import com.example.gestiondepedidoshibernate.domain.HibernateUtil;
import com.example.gestiondepedidoshibernate.domain.usuario.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class OrderDAO implements DAO<Order> {



    @Override
    public ArrayList<Order> getAll() {
        ArrayList<Order> orders = new ArrayList<>();

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Order> query = session.createQuery("FROM Order", Order.class);
            orders.addAll(query.list());
        } catch (HibernateException e) {
            e.printStackTrace();
        }

        return orders;
    }

    @Override
    public Order get(Long id) {
        var salida = new Order();

        try(Session s = HibernateUtil.getSessionFactory().openSession()){
            salida = s.get(Order.class, id);
        }

        return salida;
    }

    public List<Order> getAllByUserId(Long userId) {
        List<Order> orders;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Order> query = session.createQuery("FROM Order o WHERE o.usuario.id = :userId", Order.class);
            query.setParameter("userId", userId);
            orders = query.getResultList();
        }

        return orders;
    }

    public List<Integer> getOrderText(Long userId) {
        List<Integer> results = new ArrayList<>();

        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            Query<Integer> q = s.createQuery("select distinct(o.codigo) from Order o where o.usuario.id = :userId", Integer.class);
            q.setParameter("userId", userId);
            results = q.getResultList();
        } catch (HibernateException e) {
            e.printStackTrace();
        }

        return results;
    }

    public List<Integer> getAllOrderText() {
        List<Integer> orderCodes = new ArrayList<>();

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Integer> query = session.createQuery("select distinct(o.codigo) from Order o", Integer.class);
            orderCodes = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return orderCodes;
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
