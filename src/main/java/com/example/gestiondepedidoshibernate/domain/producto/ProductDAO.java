package com.example.gestiondepedidoshibernate.domain.producto;

import com.example.gestiondepedidoshibernate.domain.DAO;
import com.example.gestiondepedidoshibernate.domain.HibernateUtil;
import lombok.extern.java.Log;
import org.hibernate.Transaction;

import java.util.ArrayList;

@Log
public class ProductDAO implements DAO<Product> {

    @Override
    public ArrayList<Product> getAll() {
        return null;
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

            Product g = s.get(Product.class, data.getId());
            Product.merge(data, g);

            t.commit();
        }
    }

    @Override
    public void delete(Product data) {

    }
}
