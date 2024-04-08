package model;

import com.mysql.cj.xdevapi.Client;
import database.CRUD;
import database.ConfigDB;
import entity.Cliente;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteModel implements CRUD {

    @Override
    public Object insert(Object obj) {
        Connection objConnection = ConfigDB.openConnection();
        Cliente objCliente = (Cliente) obj;

        try {
            String sql = "INSERT INTO cliente (nombre, apellido, email) VALUES (?,?,?);";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            objPrepare.setString(1, objCliente.getNombre());
            objPrepare.setString(2, objCliente.getApellido());
            objPrepare.setString(3, objCliente.getEmail());


            objPrepare.execute();

            ResultSet objResult = objPrepare.getGeneratedKeys();

            while (objResult.next()) {
                objCliente.setId(objResult.getInt(1));
            }
            JOptionPane.showInputDialog(null, "Registro exitoso");
        } catch (SQLException e) {
            System.out.println("ERROR >" + e.getMessage());
        }
        ConfigDB.closeConnection();
        return objCliente;
    }

    @Override
    public List<Object> findAll() {
        Connection objConnection = ConfigDB.openConnection();
        List<Object> listCliente = new ArrayList<>();

        try {
            String sql = "SELECT * FROM cliente;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            ResultSet objResult = objPrepare.executeQuery();

            while (objResult.next()) {
                Cliente objCliente = new Cliente();
                objCliente.setId(objResult.getInt("id"));
                objCliente.setNombre(objResult.getString("nombre"));
                objCliente.setApellido(objResult.getString("apellido"));
                objCliente.setEmail(objResult.getString("email"));


                listCliente.add(objCliente);
            }
        } catch (SQLException e) {
            System.out.println("ERROR >" + e.getMessage());
        }
        ConfigDB.closeConnection();
        return listCliente;
    }

    @Override
    public boolean update(Object obj) {
        Connection objConnection = ConfigDB.openConnection();
        Cliente objCliente = (Cliente) obj;
        boolean isUpdated = false;

        try {
            String sql = "UPDATE cliente SET nombre=?, apellido=?, email=? WHERE id=?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setString(1, objCliente.getNombre());
            objPrepare.setString(2, objCliente.getApellido());
            objPrepare.setString(3, objCliente.getEmail());
            objPrepare.setInt(4, objCliente.getId());

            int totalRowAffected = objPrepare.executeUpdate();

            if (totalRowAffected > 0) {
                isUpdated = true;
                JOptionPane.showMessageDialog(null, "Registro actualizado con éxito");

            }

        } catch (SQLException e) {
            System.out.println("ERROR >" + e.getMessage());
        }
        ConfigDB.closeConnection();
        return isUpdated;
    }

    @Override
    public boolean delete(Object obj) {
        Connection objConnection = ConfigDB.openConnection();
        Cliente objCliente = (Cliente) obj;
        boolean isDeleted = false;

        try {
            String sql = "Delete FROM cliente WHERE id=?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setInt(1, objCliente.getId());

            int totalRowAffected = objPrepare.executeUpdate();

            if (totalRowAffected > 0) {
                isDeleted = true;
                JOptionPane.showMessageDialog(null, "Registro eliminado con éxito");
            }

        } catch (SQLException e) {
            System.out.println("ERROR >" + e.getMessage());
        }
        ConfigDB.closeConnection();
        return isDeleted;
    }

    public List<Cliente> findByFilter(String filter, String value) {
        Connection connection = ConfigDB.openConnection();

        String sql;

        List<Cliente> listaClientes = new ArrayList<>();

        try {

            if (Object.equals(filter, "ID")) {
                sql = "SELECT * FROM clientes WHERE id = ?;";

                listaClientes = encontrarFiltro(sql, value);

            }
            if (Object.equals(filter, "Nombre")) {

                sql = "SELECT * FROM clientes WHERE nombre LIKE ?;";

                listaClientes = encontrarNombre(sql, value);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error aplicando filtro " + e.getMessage());
        }

        ConfigDB.closeConnection();
        return listaClientes;
    }

    public List<Cliente> encontrarFiltro(String sql, String value) {

        Connection connection = ConfigDB.openConnection();
        List<Cliente> listaClientes = new ArrayList<>();

        try {
            PreparedStatement prepareCall = connection.prepareStatement(sql);
            prepareCall.setInt(1, Integer.parseInt(value));
            ResultSet result = prepareCall.executeQuery();

            while (result.next()) {

                Cliente cliente = new Cliente();

                cliente.setId(result.getInt("id"));
                cliente.setNombre(result.getString("nombre"));
                cliente.setApellido(result.getString("apellido"));
                cliente.setEmail(result.getString("email"));


                listaClientes.add(cliente);
            }

            prepareCall.close();
            JOptionPane.showMessageDialog(null, "Lista de clientes:\n" + listaClientes);


        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error mostrando filtros  " + e.getMessage());
        }

        return listaClientes;

    }

    private List<Cliente> encontrarNombre(String sql, String value) {

        Connection connection = ConfigDB.openConnection();
        List<Cliente> listaClientes = new ArrayList<>();

        try {
            PreparedStatement prepareCall = connection.prepareStatement(sql);
            prepareCall.setString(1, "%" + value + "%");
            ResultSet result = prepareCall.executeQuery();

            while (result.next()) {

                Cliente cliente = new Cliente();

                cliente.setId(result.getInt("id"));
                cliente.setNombre(result.getString("nombre"));
                cliente.setApellido(result.getString("apellido"));
                cliente.setEmail(result.getString("email"));


                listaClientes.add(cliente);
            }

            prepareCall.close();
            JOptionPane.showMessageDialog(null, "Lista de clientes:\n" + listaClientes);


        } catch (SQLException e) {
            System.out.println("Error > " + e.getMessage());

        }
        ConfigDB.closeConnection();
        return listaClientes;

    }
}
