package model;

import database.CRUD;
import database.ConfigDB;
import entity.Tienda;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TiendaModel implements CRUD {
    @Override
    public Object insert(Object obj) {
        return null;
    }

    public List<Object> findAll() {

        List<Object> listTienda = new ArrayList<>();
        Connection objConnection = ConfigDB.openConnection();

        try {
            String sql = "SELECT * FROM tienda;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            ResultSet objResult = objPrepare.executeQuery();
            while (objResult.next()) {
                Tienda objTienda = new Tienda();

                objTienda.setId(objResult.getInt("id"));
                objTienda.setNombre(objResult.getString("nombre"));
                objTienda.setUbicacion(objResult.getString("ubicacion"));

                listTienda.add(objTienda);
            }
        } catch (SQLException e) {
            System.out.println("ERROR >" + e.getMessage());
        }

        ConfigDB.closeConnection();
        ;
        return listTienda;
    }

    @Override
    public boolean update(Object obj) {
        return false;
    }

    @Override
    public boolean delete(Object obj) {
        return false;
    }
}