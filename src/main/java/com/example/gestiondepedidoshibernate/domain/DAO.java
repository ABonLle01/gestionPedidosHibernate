package com.example.gestiondepedidoshibernate.domain;

import com.example.gestiondepedidoshibernate.domain.pedido.Order;

import java.util.ArrayList;
import java.util.List;

public interface DAO<T> {

    public ArrayList<T> getAll();

    public List<T> getAllByUserId(Long userId);

    public T get(Long id);

    public T save(T data);

    public void update(T data);

    public void delete(T data);
}
