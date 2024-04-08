import controller.ClienteController;
import controller.CompraController;
import controller.ProductoController;
import controller.TiendaController;
import database.ConfigDB;

import javax.swing.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        int option = 0, option2 = 0;

        do {
            option = Integer.parseInt(JOptionPane.showInputDialog("""
                    1. Administrar Tienda
                    2. Administrar Producto
                    3. Administrar Clientes
                    4. Administrar Compra
                    5. Salir
                                        
                    Ingrese una opción:
                    """));

            switch (option) {
                case 1:
                    do {
                        option2 = Integer.parseInt(JOptionPane.showInputDialog("""
                                1. Listar Tienda
                                2. Salir
                                  
                                Ingrese una opción:
                                """));

                        switch (option2) {
                            case 1:
                                TiendaController.getAll();
                                break;
                        }
                    } while (option2 != 2);
                    break;
                case 2:
                    do {
                        option2 = Integer.parseInt(JOptionPane.showInputDialog("""
                                1. Listar Productos
                                2. Crear Producto
                                3. Eliminar Producto
                                4. Actualizar Producto
                                5. Salir
                                  
                                Ingrese una opción:
                                """));
                        switch (option2) {
                            case 1:
                                ProductoController.getAll();
                                break;
                            case 2:
                                ProductoController.insert();
                                break;
                            case 3:
                                ProductoController.delete();
                                break;
                            case 4:
                                ProductoController.update();
                                break;
                        }
                    } while (option2 != 5);
                    break;
                case 3:
                    do {
                        option2 = Integer.parseInt(JOptionPane.showInputDialog("""
                                1. Listar Clientes
                                2. Crear Cliente
                                3. Eliminar Cliente
                                4. Actualizar Cliente
                                5. Salir
                                  
                                Ingrese una opción:
                                """));
                        switch (option2) {
                            case 1:
                                ClienteController.getAll();
                                break;
                            case 2:
                                ClienteController.insert();
                                break;
                            case 3:
                                ClienteController.delete();
                                break;
                            case 4:
                                ClienteController.update();
                                break;
                        }
                    } while (option2 != 5);
                case 4:
                    option2 = Integer.parseInt(JOptionPane.showInputDialog("""
                                1. Listar Compras
                                2. Crear Compra
                                3. Eliminar Compra
                                4. Actualizar Compra
                                5. Salir
                                  
                                Ingrese una opción:
                                """));

                    switch (option2){
                        case 1:
                            CompraController.getAll();
                            break;
                        case 2:
                            CompraController.insert();
                            break;
                        case 3:
                            CompraController.delete();
                            break;
                        case 4:
                            CompraController.update();
                            break;
                    }
                    break;
            }
        }while (option != 5);
    }
}