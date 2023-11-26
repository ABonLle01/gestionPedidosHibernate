package com.example.gestiondepedidoshibernate.domain.producto;

import com.example.gestiondepedidoshibernate.domain.DAO;
import com.example.gestiondepedidoshibernate.domain.HibernateUtil;
import com.example.gestiondepedidoshibernate.domain.pedido.Order;
import lombok.extern.java.Log;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

@Log
public class ProductDAO implements DAO<Product> {

    @Override
    public ArrayList<Product> getAll() {
        return null;
    }

    public ArrayList<String> getAllNames(){
        ArrayList<String> orders;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<String> query = session.createQuery("SELECT DISTINCT p.nombre from Product p", String.class);
            orders = (ArrayList<String>) query.getResultList();
        }

        return orders;

    }

    @Override
    public List<Product> getAllByUserId(Long userId) {
        List<Product> orders = new ArrayList<>();

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Product> query = session.createQuery("SELECT p FROM Product p JOIN User u WHERE u.id = :userId", Product.class);
            query.setParameter("userId", userId);
            orders = query.getResultList();
        }

        return orders;
    }

    public List<Product> getProducts(Long orderId, Long userId) {
        List<Product> products = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT DISTINCT p FROM Product p " +
                    "JOIN Item i  ON p.id=i.producto.id " +
                    "JOIN Order o ON i.codigo.codigo = o.codigo " +
                    "WHERE o.usuario.id = :userId AND o.id = :orderId";

            Query<Product> query = session.createQuery(hql, Product.class);
            query.setParameter("orderId", orderId);
            query.setParameter("userId",userId);

            products = query.getResultList();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return products;
    }

    @Override
    public Product get(Long id) {
        return null;
    }

    @Override
    public Product save(Product data) {
        Product salida = null;

        try(org.hibernate.Session s = HibernateUtil.getSessionFactory().openSession()){
            Transaction t = s.beginTransaction();
            s.persist(data);
            t.commit();
            System.out.println(data);
            salida=data;
        }catch (Exception ex){
            log.severe("Error al guardar el producto "+data.toString());
        }

        return salida;
    }

    @Override
    public void update(Product data) {
        try(org.hibernate.Session s = HibernateUtil.getSessionFactory().openSession()){
            Transaction t = s.beginTransaction();

            Product p = s.get(Product.class, data.getId());
            Product.merge(data, p);

            t.commit();
        }
    }

    @Override
    public void delete(Product data) {
        HibernateUtil.getSessionFactory().inTransaction( (session) -> {
            Product p = session.get(Product.class,data.getId());
            session.remove(p);

        } );
    }
}
