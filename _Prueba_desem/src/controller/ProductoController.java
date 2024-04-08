package controller;

import database.ConfigDB;
import entity.Producto;
import entity.Tienda;
import model.ProductoModel;
import utils.Utils;

import javax.swing.*;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class ProductoController {

    public static void getAll(){
        String list = getAll(instanceModel().findAll());
        JOptionPane.showInputDialog(null, list);
    }
    public static String getAll(List<Object> list){
        String listString = "Lista de registros \n:";

        for (Object temp : list){
            Producto objProducto = (Producto) temp;
            listString+= objProducto.toString() + "\n";
        }
        return listString;

    }

    public static void delete (){
        Object[] options = Utils.listToArray(instanceModel().findAll());
        Producto objProducto = (Producto) JOptionPane.showInputDialog(
                null,
                "Seleccione el producto a eliminar",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]
        );

        instanceModel().delete(objProducto);
    }
    public static void update(){
        Object[] options = Utils.listToArray(instanceModel().findAll());
        Producto objProducto = (Producto) JOptionPane.showInputDialog(
                null,
                "Seleccione el producto a editar",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]
        );

        String nombre = JOptionPane.showInputDialog(null, "Ingrese el nombre del producto", objProducto.getNombre());
        String precio = JOptionPane.showInputDialog("Ingrese el precio del producto", objProducto.getPrecio());

        Tienda objTienda = (Tienda) JOptionPane.showInputDialog(
                null,
                "Seleccione una tienda",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]
        );

        instanceModel().update(new Producto(nombre, precio, objTienda.getId(), objTienda));
    }

    public static void insert() {
        String nombre = JOptionPane.showInputDialog("Ingrese el nombre del producto:");
        String precio = JOptionPane.showInputDialog("Ingrese el precio del producto:");

        Object[] optionsTiendas = Utils.listToArray(TiendaController.instanceModel().findAll());
        Tienda objTienda = (Tienda) JOptionPane.showInputDialog(
                null,
                "Seleccione una Tienda",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                optionsTiendas,
                optionsTiendas[0]
        );
        instanceModel().insert(new Producto(nombre, precio, objTienda.getId(), objTienda));
    }

    public static ProductoModel instanceModel() {

        return  new ProductoModel();
    }
}
