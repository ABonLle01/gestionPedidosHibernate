package com.example.gestiondepedidoshibernate.domain.usuario;

import com.example.gestiondepedidoshibernate.domain.DAO;
import com.example.gestiondepedidoshibernate.domain.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;

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









    /*
    private static Connection connection;

    public UsuarioDAO() {
        this.connection = DBConnection.getConnection();
    }

    @Override
    public Usuario load(Long id) {
        try {
            String query = "SELECT * FROM usuarios WHERE id = ?";
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setLong(1, id);

            ResultSet resultSet = pst.executeQuery();

            if (resultSet.next()) {
                return new Usuario(
                        resultSet.getLong("id"),
                        resultSet.getString("nombre"),
                        resultSet.getString("email"),
                        resultSet.getString("password")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static Usuario login(String email, String password) {
        try {
            String query = "SELECT * FROM usuarios WHERE email = ? AND password = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return new Usuario(
                        resultSet.getLong("id"),
                        resultSet.getString("nombre"),
                        resultSet.getString("email"),
                        resultSet.getString("password")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

     */
}