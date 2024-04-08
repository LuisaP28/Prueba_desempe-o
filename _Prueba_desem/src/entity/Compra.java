package entity;

public class Compra {
    private int id;
    private String fecha_compra;
    private int cantidad;
    private int id_cliente;
    private int id_producto;
    private Producto objProducto;
    private Cliente objCliente;
    private Double factura;

    public Compra() {
    }

    public Compra(int id, String fecha_compra, int cantidad, int id_cliente, int id_producto, Producto objProducto, Cliente objCliente) {
        this.id = id;
        this.fecha_compra = fecha_compra;
        this.cantidad = cantidad;
        this.id_cliente = id_cliente;
        this.id_producto = id_producto;
        this.objProducto = objProducto;
        this.objCliente = objCliente;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFecha_compra() {
        return fecha_compra;
    }

    public void setFecha_compra(String fecha_compra) {
        this.fecha_compra = fecha_compra;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public Producto getObjProducto() {
        return objProducto;
    }

    public void setObjProducto(Producto objProducto) {
        this.objProducto = objProducto;
    }

    public Cliente getObjCliente() {
        return objCliente;
    }

    public void setObjCliente(Cliente objCliente) {
        this.objCliente = objCliente;
    }

    @Override
    public String toString() {
        return "Compra{" +
                "id=" + id +
                ", fecha_compra='" + fecha_compra + '\'' +
                ", cantidad=" + cantidad +
                ", id_cliente=" + id_cliente +
                ", id_producto=" + id_producto +
                ", objProducto=" + objProducto +
                ", objCliente=" + objCliente +
                '}';
    }
}
