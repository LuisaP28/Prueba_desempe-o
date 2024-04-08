package controller;

import database.ConfigDB;
import entity.Cliente;
import entity.Compra;
import entity.Producto;
import entity.Tienda;
import model.CompraModel;
import utils.Utils;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;


public class CompraController {
    public static void insert() {
        String fecha_compra = JOptionPane.showInputDialog("Ingresa la fecha de la compra: YYYY-MM-dd");
        String cantidad = JOptionPane.showInputDialog("Ingresa la cantidad: ");


        Object[] optionsCliente = Utils.listToArray(ClienteController.instanceModel().findAll());
        Object[] optionProducto = Utils.listToArray(ProductoController.instanceModel().findAll());

        Cliente clienteSelected = (Cliente) JOptionPane.showInputDialog(null, "Seleccione el cliente", "", JOptionPane.QUESTION_MESSAGE, null, optionsCliente, optionsCliente[0]);
        Producto productoSelected = (Producto) JOptionPane.showInputDialog(null, "Seleccione el producto", "", JOptionPane.QUESTION_MESSAGE, null, optionProducto, optionProducto[0]);

        instanceModel().insert(new Compra());
    }

    public static void update(){
        Object[] options = Utils.listToArray(instanceModel().findAll());
        Compra compraSelected = (Compra) JOptionPane.showInputDialog(null, "Seleccione la compra a editar", "", JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

        compraSelected.setFecha_compra(JOptionPane.showInputDialog(null, "Ingresa la fecha de la compra: YYYY-MM-dd", compraSelected.getFecha_compra()));
        compraSelected.setCantidad(Integer.parseInt(JOptionPane.showInputDialog(null, "Ingresa la hora de la cantidad:", compraSelected.getCantidad())));

        Object[] optionsCliente = Utils.listToArray(ClienteController.instanceModel().findAll());
        Object[] optionProducto = Utils.listToArray(ProductoController.instanceModel().findAll());

        compraSelected.setObjCliente ((Cliente) JOptionPane.showInputDialog(null, "Seleccione el cliente", "", JOptionPane.QUESTION_MESSAGE, null, optionsCliente, optionsCliente[0]));

        compraSelected.setId_cliente(compraSelected.getObjCliente().getId());

        compraSelected.setObjProducto ((Producto) JOptionPane.showInputDialog(null, "Seleccione el producto", "", JOptionPane.QUESTION_MESSAGE, null, optionProducto, optionProducto[0]));

        compraSelected.setId_producto(compraSelected.getId_producto());
        instanceModel().update(compraSelected);
    }

    public static void delete(){
        Object[] options = Utils.listToArray(instanceModel().findAll());
        Compra compraSelected = (Compra) JOptionPane.showInputDialog(null, "Seleccione la compra a eliminar", "", JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

        instanceModel().delete(compraSelected);

    }

    public static void getAll(){
        String listString = getAll(instanceModel().findAll());
        JOptionPane.showMessageDialog(null, listString);
    }

    public static String getAll(List<Object> list){
        String listString = "Lista de compras \n";

        for (Object temp : list){
            Compra obj =(Compra) temp;
            listString += obj.toString() + "\n";
        }
        return listString;
    }

    public static CompraModel instanceModel(){

        return  new CompraModel();
    }

}
