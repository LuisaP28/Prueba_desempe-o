package model;

import database.CRUD;
import database.ConfigDB;
import entity.Producto;
import entity.Tienda;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductoModel implements CRUD {
    @Override
    public Object insert(Object obj) {
        Connection objConnection = ConfigDB.openConnection();

        Producto objProducto = (Producto) obj;

        try {
            String sql = "INSERT INTO producto (nombre, precio, id_tienda) VALUES (?,?,?);";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            objPrepare.setString(1, objProducto.getNombre());
            objPrepare.setDouble(2, objProducto.getPrecio());
            objPrepare.setInt(3, objProducto.getId_tienda());

            objPrepare.execute();

            ResultSet objResult = objPrepare.getGeneratedKeys();

            while (objResult.next()){
                objProducto.setId(objResult.getInt(1));
            }

            JOptionPane.showMessageDialog(null, "Producto insertado correctamente");
        }catch (SQLException e){
            System.out.println("ERROR >" + e.getMessage());
        }
        ConfigDB.closeConnection();
        return objProducto;
    }

    @Override
    public List<Object> findAll() {
        List<Object> listProductos = new ArrayList<>();
        Connection objConnection = ConfigDB.openConnection();

        try {
            String sql = "SELECT * FROM producto \n" +
                    "INNER JOIN tienda ON tienda.id = producto.id_tienda";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            ResultSet objResult = objPrepare.executeQuery();

            while (objResult.next()){
                Producto objProducto = new Producto();
                Tienda objTienda = new Tienda();

                objProducto.setNombre(objResult.getString("producto.nombre"));
                objProducto.setPrecio(objResult.getDouble("producto.precio"));
                objProducto.setId_tienda(objResult.getInt("producto.id_tienda"));

                objTienda.setId(objResult.getInt("tienda.id"));
                objTienda.setNombre(objResult.getString("tienda.nombre"));
                objTienda.setUbicacion(objResult.getString("tienda.ubicacion"));

                objProducto.setObjTienda(objTienda);
                listProductos.add(objProducto);
            }

        }catch(SQLException e){
            System.out.println("ERROR >" + e.getMessage());
        }
        return listProductos;
    }

    @Override
    public boolean update(Object obj) {
        Connection objConnection = ConfigDB.openConnection();
        Producto objProducto = (Producto) obj;
        boolean isUpdated = false;

        try {
            String sql = "UPDATE producto SET nombre=?, precio=?, id_tienda=?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setString(1, objProducto.getNombre());
            objPrepare.setDouble(2, objProducto.getPrecio());
            objPrepare.setInt(3, objProducto.getId_tienda());

            int totalRowAffected = objPrepare.executeUpdate();

            if (totalRowAffected >0){
                isUpdated = true;
                JOptionPane.showInputDialog(null, "Producto actualizado con éxito");
            }

        }catch(SQLException e){
            System.out.println("ERROR >" + e.getMessage());
        }

        ConfigDB.closeConnection();
        return isUpdated;
    }

    @Override
    public boolean delete(Object obj) {
        Connection objConnection = ConfigDB.openConnection();

        Producto objProducto =(Producto) obj;
        boolean isDeleted = false;

        try {
            String sql = "DELETE FROM producto WHERE id = ?";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setInt(1, objProducto.getId());

            int totalRowAffected = objPrepare.executeUpdate();

            if (totalRowAffected >0){
                isDeleted = true;
                JOptionPane.showInputDialog(null, "Producto eliminado con éxito");
            }

        }catch(SQLException e){
            System.out.println("ERROR >" + e.getMessage());
        }
        ConfigDB.closeConnection();
        return isDeleted;
    }

    public List<Producto> encontrarFiltro(String filter, String value) {

        String sql;

        List<Producto> productoList = new ArrayList<>();

        try {

            if (Object.equals(filter, "ID")) {
                sql = "SELECT * FROM productos WHERE id = ?;";

                productoList = encontrarId(sql, value);

            }
            if (Object.equals(filter, "Nombre")) {

                sql = "SELECT * FROM productos WHERE nombre LIKE ?;";

                productoList = findByName(sql, value);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error aplicando filtro " + e.getMessage());
        }

        ConfigDB.closeConnection();
        return productoList;
    }

    public List<Producto> encontrarId(String sql, String value) {

        Connection connection = ConfigDB.openConnection();
        List<Producto> productoList = new ArrayList<>();

        try {
            PreparedStatement prepareCall = connection.prepareStatement(sql);
            prepareCall.setInt(1, Integer.parseInt(value));
            ResultSet result = prepareCall.executeQuery();

            while (result.next()) {

                Producto producto = new Producto();

                producto.setId(result.getInt("id"));
                producto.setNombre(result.getString("nombre"));
                producto.setPrecio(result.getDouble("precio"));
                producto.setId_tienda(result.getInt("id_tienda"));


                productoList.add(producto);
            }

            prepareCall.close();
            JOptionPane.showMessageDialog(null, "Lista de producto:\n" + productoList);


        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error mostrando filtros  " + e.getMessage());
        }

        return productoList;

    }

    private List<Producto> findByName(String sql, String value) {

        Connection connection = ConfigDB.openConnection();
        List<Producto> productoList = new ArrayList<>();

        try {
            PreparedStatement prepareCall = connection.prepareStatement(sql);
            prepareCall.setString(1, "%" + value + "%");
            ResultSet result = prepareCall.executeQuery();

            while (result.next()) {

                Producto producto = new Producto();

                producto.setId(result.getInt("id"));
                producto.setNombre(result.getString("nombre"));
                producto.setPrecio(result.getDouble("precio"));
                producto.setId_tienda(result.getInt("id_tienda"));

                productoList.add(producto);
            }

            prepareCall.close();
            JOptionPane.showMessageDialog(null, "Lista de productos:\n" + productoList);


        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error mostrando filtros " + e.getMessage());

        }

        return productoList;

    }
}
