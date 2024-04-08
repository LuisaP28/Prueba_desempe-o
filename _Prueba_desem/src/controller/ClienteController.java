package controller;

import entity.Cliente;
import model.ClienteModel;
import utils.Utils;

import javax.swing.*;
import java.util.List;

public class ClienteController {
    public static void insert() {
        String nombre = JOptionPane.showInputDialog("Ingresa el nombre del cliente");
        String apellido = JOptionPane.showInputDialog("Ingresa los apellidos del cliente");
        String email = JOptionPane.showInputDialog("Ingresa el email del cliente");

    }
    public static void getAll(){
        String list = getAll(instanceModel().findAll());
        JOptionPane.showMessageDialog(null, list);

    }

    public static String  getAll(List<Object> list){
        String listString = "Lista de registros";

        for (Object temp: list){
            Cliente objCliente = (Cliente) temp;
            listString += objCliente.toString() +"\n";
        }
        return listString;
    }

    public static void update(){
        Object[] options = Utils.listToArray(instanceModel().findAll());
        Cliente clienteSelected = (Cliente) JOptionPane.showInputDialog(
                null,
                "Seleccione el cliente a eliminar",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]
        );
        clienteSelected.setNombre(JOptionPane.showInputDialog(null, "Ingrese el nuevo nombre", clienteSelected.getNombre()));
        clienteSelected.setApellido(JOptionPane.showInputDialog(null, "Ingrese el nuevo apellido", clienteSelected.getApellido()));
        clienteSelected.setEmail(JOptionPane.showInputDialog(null, "Ingrese el nuevo email", clienteSelected.getEmail()));


        instanceModel().update(clienteSelected);
    }


    public static void delete(){
        Object[] options = Utils.listToArray(instanceModel().findAll());
        Cliente clienteSelected = (Cliente) JOptionPane.showInputDialog(
                null,
                "Seleccione el cliente a eliminar",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]
        );

        instanceModel().delete(clienteSelected);

    }

    public static ClienteModel instanceModel(){
        return new ClienteModel();

    }
}
