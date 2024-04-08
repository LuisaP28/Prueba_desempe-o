package model;

import database.CRUD;
import database.ConfigDB;
import entity.Cliente;
import entity.Compra;
import entity.Producto;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CompraModel implements CRUD {

    @Override
    public Object insert(Object obj) {
        Connection objConnection = ConfigDB.openConnection();
        Compra objCompra = (Compra) obj;

        try {
            String sql = "INSERT compra(fecha_compra, cantidad, id_cliente, id_producto,) VALUES (?,?,?,?);";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            objPrepare.setString(1, objCompra.getFecha_compra());
            objPrepare.setDouble(2, objCompra.getCantidad());
            objPrepare.setInt(3, objCompra.getId_cliente());
            objPrepare.setInt(4, objCompra.getId_producto());


            objPrepare.execute();

            ResultSet objResult = objPrepare.executeQuery();

            while (objResult.next()){
                objCompra.setId(objResult.getInt(1));
            }
            JOptionPane.showInputDialog(null, "Compra insertada correctamente");

        }catch (SQLException e){
            System.out.println("ERROR >" + e.getMessage());
        }
        ConfigDB.closeConnection();
        return objCompra;
    }

    @Override
    public List<Object> findAll() {
        Connection objConnection = ConfigDB.openConnection();
        List<Object> listCompra = new ArrayList<>();

        try {
            String sql = "SELECT * FROM compra\n" +
                    "INNER JOIN cliente ON cliente.id = compra.id_cliente\n" +
                    "INNER JOIN ptoducto ON producto.id = compra.id_producto;";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            ResultSet objResult = objPrepare.executeQuery();

            while (objResult.next()){
                Compra objCompra = new Compra();
                Producto objProducto = new Producto();
                Cliente objCliente = new Cliente();

                objCompra.setId(objResult.getInt("compra.id"));
                objCompra.setFecha_compra(objResult.getString("compra.fecha_compra"));
                objCompra.setCantidad(objResult.getInt("compra.cantidad"));
                objCompra.setId_producto(objResult.getInt("compra.id_producto"));
                objCompra.setId_cliente(objResult.getInt("compra.id_cliente"));

                objProducto.setNombre(objResult.getString("producto.nombre"));
                objCliente.setNombre(objResult.getString("cliente.nombre"));
                objCompra.setObjProducto(objProducto);
                objCompra.setObjCliente(objCliente);

                listCompra.add(objCompra);
            }

        }catch (SQLException e){
            System.out.println("ERROR >"+ e.getMessage());
        }
        return listCompra;
    }

    @Override
    public boolean update(Object obj) {
        Connection objConnection = ConfigDB.openConnection();
        Compra objCompra = (Compra) obj;
        boolean isUpdated = false;

        try {
            String sql = "UPDATE compra SET fecha_compra=?, cantidad=?, id_cliente=?, id_producto=? WHERE id=?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setDate(1, Date.valueOf(objCompra.getFecha_compra()));
            objPrepare.setInt(2, objCompra.getCantidad());
            objPrepare.setInt(3, objCompra.getId_cliente());
            objPrepare.setInt(4, objCompra.getId_producto());
            objPrepare.setInt(6, objCompra.getId());

            int totalRowAffected = objPrepare.executeUpdate();

            if (totalRowAffected > 0){
                isUpdated = true;
                JOptionPane.showMessageDialog(null, "Registro modificado con éxito");
            }

        }catch (SQLException e){
            System.out.println("ERROR >" + e.getMessage());
        }
        ConfigDB.closeConnection();
        return isUpdated;
    }

    @Override
    public boolean delete(Object obj) {
        Connection objConnection = ConfigDB.openConnection();
        Compra objCompra = (Compra) obj;
        boolean isDeleted = false;

        try {
            String sql = "DELETE FROM compra WHERE id= ?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setInt(1, objCompra.getId());

            int totalRowAffected = objPrepare.executeUpdate();

            if (totalRowAffected > 0){
                isDeleted = true;
                JOptionPane.showMessageDialog(null, "Registro eliminado con éxito");
            }

        }catch (SQLException e){
            System.out.println("ERROR >" + e.getMessage());
        }
        ConfigDB.closeConnection();
        return isDeleted;
    }
    public List<Compra> encontrarFiltro(String filter, String value) {

        String sql;

        List<Compra> listCompra = new ArrayList<>();

        try {

            if (Object.equals(filter, "id")) {
                sql = "SELECT * FROM compra WHERE id = ?;";

                listCompra = findById(sql, value);

            }
            if (Object.equals(filter, "Producto")) {

                sql = "SELECT * FROM compras c JOIN productos p ON c.id_producto = p.id WHERE p.id = ?;";

                listCompra = encontrarProducto(sql, value);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error aplicando filtro " + e.getMessage());
        }

        ConfigDB.closeConnection();
        return listCompra;
    }

    public List<Compra> findById(String sql, String value) {

        Connection connection = ConfigDB.openConnection();
        List<Compra> compraList = new ArrayList<>();

        try {
            PreparedStatement prepareCall = connection.prepareStatement(sql);
            prepareCall.setInt(1, Integer.parseInt(value));
            ResultSet result = prepareCall.executeQuery();

            while (result.next()) {

                Compra compra = new Compra();

                compra.setId(result.getInt("id"));
                compra.setId_cliente(result.getInt("id_cliente"));
                compra.setId_producto(result.getInt("id_producto"));
                compra.setFecha_compra(String.valueOf(result.getDate("fecha_compra")));
                compra.setCantidad(result.getInt("cantidad"));


                compraList.add(compra);
            }

            prepareCall.close();
            JOptionPane.showMessageDialog(null, compraList);


        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error mostrando filtros  " + e.getMessage());
        }

        return compraList;

    }

    public List<Compra> encontrarProducto(String sql, String value) {

        Connection connection = ConfigDB.openConnection();
        List<Compra> listCompra = new ArrayList<>();

        try {
            PreparedStatement prepareCall = connection.prepareStatement(sql);
            prepareCall.setInt(1, Integer.parseInt(value));
            ResultSet result = prepareCall.executeQuery();

            while (result.next()) {

                Compra compra = new Compra();

                compra.setId(result.getInt("id"));
                compra.setId_cliente(result.getInt("id_cliente"));
                compra.setId_producto(result.getInt("id_producto"));
                compra.setFecha_compra(String.valueOf(result.getDate("fecha_compra")));
                compra.setCantidad(result.getInt("cantidad"));


                listCompra.add(compra);
            }

            prepareCall.close();

            JOptionPane.showMessageDialog(null, listCompra);


        } catch (SQLException e) {
            System.out.println("Error>" + e.getMessage());
        }
        ConfigDB.closeConnection();
        return listCompra;

    }

}
